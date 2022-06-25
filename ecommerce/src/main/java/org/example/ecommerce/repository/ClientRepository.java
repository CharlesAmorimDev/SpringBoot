package org.example.ecommerce.repository;

import org.example.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    @Query(value = " select c from Client c where c.name like :name") //Query HQL
    List<Client> encontrarPorNomeHQL(@Param("name") String name);

    @Query(value = " select * from client c where c.name like '%:name%'", nativeQuery = true) //Query SQL Nativo
    List<Client> encontrarPorNomeSQLNATIVO(@Param("name") String name);

    @Query(value = " delete from Client c where c.name = :name")
    @Modifying
    void deleteByName(String name);

    @Query(" select c from Client c left join fetch c.order where c.id = :id")
    Client findClientFetchOrder(@Param("id") Integer id);
}
