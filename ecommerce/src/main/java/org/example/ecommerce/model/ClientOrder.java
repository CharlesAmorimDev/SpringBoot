package org.example.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientOrder {

    private Long id;
    private Client client;
    private LocalDate dateorder;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateorder() {
        return dateorder;
    }

    public void setDateorder(LocalDate dateorder) {
        this.dateorder = dateorder;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
