package org.example.ecommerce.controller;

import org.example.ecommerce.dto.NewStatusDTO;
import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.dto.OrderInformationsDTO;
import org.example.ecommerce.model.Order;
import org.example.ecommerce.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl service;

    @GetMapping("{id}")
    public Optional<OrderInformationsDTO> orderInformations(@PathVariable Integer id) {
        return service.orderInformations(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer register(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = service.generateOrder(orderDTO);
        return order.getId();
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Integer id, @RequestBody NewStatusDTO newStatus) {
        String update = newStatus.getNewStatus();
        service.updateStatus(id, update);

    }

}
