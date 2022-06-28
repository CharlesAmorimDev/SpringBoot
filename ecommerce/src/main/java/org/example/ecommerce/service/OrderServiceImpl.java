package org.example.ecommerce.service;

import org.example.ecommerce.dto.*;
import org.example.ecommerce.enums.OrderStatus;
import org.example.ecommerce.exception.BusinessRuleException;
import org.example.ecommerce.exception.OrderNotFoundException;
import org.example.ecommerce.model.Customer;
import org.example.ecommerce.model.Order;
import org.example.ecommerce.model.OrderDetails;
import org.example.ecommerce.model.Product;
import org.example.ecommerce.repository.CustomerRepository;
import org.example.ecommerce.repository.OrderDetailsRepository;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    @Override
    @Transactional
    public Order generateOrder(OrderDTO orderDTO) {
        Order order = new Order();      //Criando um pedido vazio
        Customer customer = verifyCustomer(orderDTO.getCustomer());     //Receber o cliente do pedido
        List<OrderDetails> orderDetails = verifyItems(order,orderDTO.getItems());   //Recebendo a lista de itens

        orderDetailsRepository.saveAll(orderDetails);   // Salvando a lista de itens

        order.setDateorder(LocalDate.now());    //adicionando ao pedido data no pedido
        order.setTotal(orderDTO.getTotal());    //adicionando ao pedido preço total
        order.setCustomer(customer);            //adicionando ao pedido o cliente obtido acima
        order.setItems(orderDetails);           //adicionando ao pedido a lista de itens obtidos acima
        order.setStatus(OrderStatus.GENERATED);

        orderRepository.save(order);            //Salvando o pedido
        return order;                           //retornar o pedido salvo para o controller

    }       //Gera o pedido completo e devolve o id dele

    @Override
    public Optional<OrderInformationsDTO> orderInformations(Integer orderID) {
        Order order = orderRepository.findByIdFetchItems(orderID)
                .orElseThrow( () -> new BusinessRuleException("Pedido não encontrado"));

        OrderInformationsDTO orderInformations = new OrderInformationsDTO();
        orderInformations.setNumber(order.getId());
        orderInformations.setNameCustomer(order.getCustomer().getName());
        orderInformations.setTotal(order.getTotal());
        orderInformations.setDateOrder(order.getDateorder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        orderInformations.setItems(itemsInformations(order.getItems()));
        orderInformations.setStatus(order.getStatus().name());
        return Optional.of(orderInformations);
    }

    @Override
    public void updateStatus(Integer orderID, String newStatus) {
        orderRepository.findById(orderID)
                .map(order -> {
                    order.setStatus(OrderStatus.valueOf(newStatus));
                    return orderRepository.save(order);
                }).orElseThrow( () -> new OrderNotFoundException());
    }








    private List<OrderDetailsInformationsDTO> itemsInformations(List<OrderDetails> orderDetails) {
        if(CollectionUtils.isEmpty(orderDetails)) {
            return Collections.emptyList();
        }
        return orderDetails.stream().map(
                items -> {
                    OrderDetailsInformationsDTO itemsInformations = new OrderDetailsInformationsDTO();
                    itemsInformations.setName(items.getProduct().getName());
                    itemsInformations.setPrice(items.getProduct().getPrice());
                    itemsInformations.setAmount(items.getAmount());
                    return itemsInformations;
                }).collect(Collectors.toList());
    }
    private Customer verifyCustomer(Integer customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow( ()-> new BusinessRuleException("Código do cliente inválido"));
    }       //Verifica se o cliente existe na base de dados
    private List<OrderDetails> verifyItems(Order order, List<OrderDetailsDTO> itemsDTO) {
        if(itemsDTO.isEmpty()){
            throw new BusinessRuleException("A sacola está vazia");
        }
        return itemsDTO
                .stream()
                .map(item -> {
                    Integer itemID = item.getProduct();
                    Product product = productRepository.findById(itemID)
                            .orElseThrow( ()-> new BusinessRuleException("Código do produto inválido"));

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProduct(product);
            orderDetails.setAmount(item.getAmount());
            orderDetails.setOrder(order);
            return orderDetails;
        }).collect(Collectors.toList());
    }   //Verifica se os itens existem na base de dados

}
