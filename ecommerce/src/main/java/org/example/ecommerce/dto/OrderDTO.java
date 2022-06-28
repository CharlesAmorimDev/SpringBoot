package org.example.ecommerce.dto;

import org.example.ecommerce.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    @NotNull(message = "É obrigatório informar o cliente")
    private Integer customer;
    @NotEmptyList(message = "O carrinho de comprar está vazio")
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
