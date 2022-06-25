package org.example.ecommerce.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    public Client() { }

    public Client(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ClientOrder> order;

    public Set<ClientOrder> getOrder() {
        return order;
    }

    public void setOrder(Set<ClientOrder> order) {
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

    @Override
    public String toString() {
        return "Name: " + name + " - Age: " + age + " - ID = " + id;

    }
}
