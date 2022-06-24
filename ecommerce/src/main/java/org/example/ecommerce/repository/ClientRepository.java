package org.example.ecommerce.repository;

import org.example.ecommerce.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;
/*
Usando @EntityManager para fazer o CRUD com banco de dados H2.
@Transactional é obrigatório para todos os métodos do entitymanager
O Mapeamento das entidades é feito diretamento na classe com @Entity e não usamos mais o arquivo data.sql

Métodos CRUD:
save() - salve um cliente no banco de dados
update() - Atualiza um cliente no banco de dados
delete() - Delete um cliente do banco de datos
findAll() - Retorna uma lista com todos os cliente registrados no banco de dados
findByName() - Retorna uma lista de clientes que contém o nome informado
 */

@Repository
public class ClientRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional                          //Transactional é obrigatório em todos os métodos Entitymanager
    public Client save(Client client) {
        entityManager.persist(client);      // persist() é o método da classe EntityManager que persiste
        return client;
    }

    @Transactional
    public Client update(Client client) {
        entityManager.merge(client);        // merge() é o método da classe EntityManager que atualiza
        return client;
    }

    @Transactional
    public void delete(Client client) {
        if(!entityManager.contains(client)){        // contains() Verifica se o cliente existe na base da dados
            client = entityManager.merge(client);   // Inclui o clinte na base, caso ele nao esteja em estado Transacional
        }
        entityManager.remove(client);               // remove() deleta o cliente da base de dados
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        delete(client);
    }

    @Transactional(readOnly = true)                     // readOnly indica que o método é somento leitura
    public List<Client> findByName(String name) {
        String jpql = " SELECT c FROM Client c WHERE c.name like :name "; // String que será incluida no método TypedQuery
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class); //TypedQuery define o tipo da consulta como client, createQuery() recebe o comando sql para retornar o client.class
        query.setParameter("name", "%" + name + "%"); // setParameter define que a query retorne os cliente que contém aquele nome
        return query.getResultList(); // retorna o resultado da lista
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return entityManager.createQuery("FROM Client",Client.class).getResultList(); // cria uma query que retonar todos os clientes
    }

}
