package org.example.ecommerce.service;

import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.dto.OrderDetailsDTO;
import org.example.ecommerce.exception.BusinessRuleException;
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

import java.time.LocalDate;
import java.util.List;
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

        Customer customer = verifyCustomer(orderDTO.getCustomer());

        Order order = new Order();
        order.setDateorder(LocalDate.now());
        order.setTotal(orderDTO.getTotal());
        order.setCustomer(customer);

        List<OrderDetails> orderDetails = verifyItems(order,orderDTO.getItems());
        orderRepository.save(order);
        orderDetailsRepository.saveAll(orderDetails);
        order.setItems(orderDetails);
        return order;
    }

    private Customer verifyCustomer(Integer customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow( ()-> new BusinessRuleException("Código do cliente inválido"));
    }

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
    }

}
