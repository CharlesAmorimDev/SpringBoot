package org.example.ecommerce.dto;

import java.math.BigDecimal;

public class OrderDetailsInformationsDTO {

    public OrderDetailsInformationsDTO() {
    }

    private String name;
    private BigDecimal price;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
