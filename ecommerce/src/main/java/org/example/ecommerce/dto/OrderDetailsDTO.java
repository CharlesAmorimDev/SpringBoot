package org.example.ecommerce.dto;

import java.math.BigDecimal;

public class OrderDetailsDTO {

    private Integer product;
    private Integer amount;

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
