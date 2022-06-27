package org.example.ecommerce.service;

import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.model.Order;

public interface OrderService {

    public Order generateOrder(OrderDTO orderDTO);


}
