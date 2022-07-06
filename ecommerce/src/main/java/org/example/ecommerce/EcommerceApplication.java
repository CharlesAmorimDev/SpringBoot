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
/*
    @Bean
    public CommandLineRunner execute(
            @Autowired CustomerRepository customerRepository) {
        return args -> {
            Customer customer = new Customer("charles@email.com", "123", "ADMIN", "Charles", "22/02/2022", "21538127040", "11 - 9 9999-9999", "Rua direita, 145");
            customerRepository.save(customer);
        };
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
