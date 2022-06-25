package org.example.ecommerce.controller;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity getAll(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Client> clients = clientRepository.findAll(example);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody Client client) {
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Client client) {
        return clientRepository.findById(id).map(clientExists -> {
            client.setId(clientExists.getId());
            clientRepository.save(client);
            return ResponseEntity.noContent().build();
        }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
