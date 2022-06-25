package org.example.ecommerce;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    @Bean
    public CommandLineRunner execute(
            @Autowired ClientRepository clientRepository) {
        return args -> {
            Client client = new Client("Charles", 22);
            clientRepository.save(client);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
