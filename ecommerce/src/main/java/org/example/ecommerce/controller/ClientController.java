package org.example.ecommerce.controller;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientRepository repository;

    @GetMapping("{id}")
    public Client getById(@PathVariable Integer id) {
        return repository.findById(id)
                               .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Localizado"));
    }

    @GetMapping
    public List<Client> getAllByFilter(Client filter) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withIgnoreCase()
                                               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client register(@RequestBody Client client) {
        return repository.save(client);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Client update) {
        repository.findById(id)
                .map(client -> {
                    update.setId(client.getId());
                    repository.save(update);
                    return client;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Localizado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(client -> { repository.delete(client);
                                        return client;
                                     }).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Localizado"));
    }
}
