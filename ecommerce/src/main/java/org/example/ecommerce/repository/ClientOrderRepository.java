package org.example.ecommerce.repository;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {

    List<ClientOrder> findByClient(Client client);
}
