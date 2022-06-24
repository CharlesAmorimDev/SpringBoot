package org.example.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;


public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Client client;
    private LocalDate dateorder;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
