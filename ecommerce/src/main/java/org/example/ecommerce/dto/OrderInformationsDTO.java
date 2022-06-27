package org.example.ecommerce.dto;

import org.example.ecommerce.model.OrderDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class OrderInformationsDTO {

    public OrderInformationsDTO() {
    }

    private Integer number;
    private String nameCustomer;
    private BigDecimal total;
    private String dateOrder;
    private String status;
    private List<OrderDetailsInformationsDTO> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<OrderDetailsInformationsDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailsInformationsDTO> items) {
        this.items = items;
    }
}
