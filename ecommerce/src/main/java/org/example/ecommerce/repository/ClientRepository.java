package org.example.ecommerce.repository;

import org.example.ecommerce.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
Usando @JdbcTemplate para fazer o CRUD com banco de dados H2.

Métodos CRUD:
save() - salve um cliente no banco de dados
update() - Atualiza um cliente no banco de dados
delete() - Delete um cliente do banco de datos
findAll() - Retorna uma lista com todos os cliente registrados no banco de dados
findByName() - Retorna uma lista de clientes que contém o nome informado
findAllMapper() - Esse método foi criado para reutilizar o RowMapper<Client>.
o RowMapper mapeia os dados recebidos pelo banco com a classe Client.

String estáticas que foram criadas para reutilização e contém comando SQL:
private static final String INSERT
private static final String SELECT_ALL
private static final String UPDATE
private static final String DELETE
 */

@Repository
public class ClientRepository {

    private static final String INSERT = "INSERT INTO CLIENT (name, age) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM CLIENT";
    private static final String UPDATE = "UPDATE CLIENT SET name = ?, age = ? WHERE id = ? ";
    private static  final String DELETE = "DELETE FROM CLIENT WHERE ID = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName(), client.getAge()});
        return client;
    }

    public List<Client> findAll() {
        return jdbcTemplate.query(SELECT_ALL, findAllMapper());
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(),client.getAge(), client.getId()});
        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});

    }

    public List<Client> findByName(String name) {
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE Name LIKE ?"), new Object[]{"%" + name + "%"}, findAllMapper());
    }

    private RowMapper<Client> findAllMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Client(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        };
    }

}
