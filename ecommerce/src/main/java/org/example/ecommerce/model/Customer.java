package org.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    public Customer() {
    }

    public Customer(String name, String cpf, Integer age) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "{customer.name-empty}")
    private String name;

    @NotEmpty(message = "{customer.cpf-null}")
    @CPF(message = "CPF Inválido")
    private String cpf;

    private Integer age;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
   // @NotEmptyList
    private Set<Order> order;

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Name: " + name + " - Age: " + age + " - ID = " + id;

    }
}
