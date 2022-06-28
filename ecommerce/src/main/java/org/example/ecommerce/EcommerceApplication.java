package org.example.ecommerce;

import org.example.ecommerce.model.Customer;
import org.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    @Bean
    public CommandLineRunner execute(
            @Autowired CustomerRepository customerRepository) {
        return args -> {
            Customer customer = new Customer("Charles", "80682038938" ,22);
            customerRepository.save(customer);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
