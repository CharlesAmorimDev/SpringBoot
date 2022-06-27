package org.example.ecommerce.service;

import org.example.ecommerce.dto.OrderDTO;
import org.example.ecommerce.dto.OrderInformationsDTO;
import org.example.ecommerce.model.Order;

import java.util.Optional;

public interface OrderService {

    Order generateOrder(OrderDTO orderDTO);
    Optional<OrderInformationsDTO> orderInformations(Integer orderID);


}
