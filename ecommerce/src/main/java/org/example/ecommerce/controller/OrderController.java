package org.example.ecommerce.controller;

import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.model.Customer;
import org.example.ecommerce.model.Order;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl service;
    @Autowired
    OrderRepository repository;

    @GetMapping
    public List<Customer> getAllByFilter(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer register(@RequestBody OrderDTO orderDTO) {
        Order order = service.generateOrder(orderDTO);
        return order.getId();
    }

}
