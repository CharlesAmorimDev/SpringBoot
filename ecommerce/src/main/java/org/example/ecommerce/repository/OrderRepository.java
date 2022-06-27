package org.example.ecommerce.repository;

import org.example.ecommerce.model.Customer;
import org.example.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomer(Customer customer);
    @Query(" SELECT o FROM Order o LEFT JOIN FETCH o.items where o.id = :id")
    Optional<Order> findByIdFetchItems(@Param("id") Integer id);


}
