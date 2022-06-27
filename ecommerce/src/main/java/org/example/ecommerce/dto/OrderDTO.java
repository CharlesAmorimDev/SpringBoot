package org.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Integer customer;
    private List<OrderDetailsDTO> items;
    private BigDecimal total;

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public List<OrderDetailsDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailsDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
