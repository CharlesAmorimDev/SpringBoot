package org.example.ecommerce.controller;

import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.dto.OrderInformationsDTO;
import org.example.ecommerce.model.Order;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl service;
    @Autowired
    OrderRepository repository;

    @GetMapping("{orderID}")
    public Optional<OrderInformationsDTO> orderInformations(@PathVariable Integer orderID) {
        return service.orderInformations(orderID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer register(@RequestBody OrderDTO orderDTO) {
        Order order = service.generateOrder(orderDTO);
        return order.getId();
    }

}
