package org.example.ecommerce.model;

import org.example.ecommerce.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "date_order")
    private LocalDate dateorder;
    @Column(name = "total",precision = 20, scale = 2)
    private BigDecimal total;
    @OneToMany(mappedBy = "order")
   // @NotEmptyList
    private List<OrderDetails> items;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<OrderDetails> getItems() {
        return items;
    }

    public void setItems(List<OrderDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order id: " + id + ", Date order: " + dateorder + ", Total: R$ " + total;
    }
}
