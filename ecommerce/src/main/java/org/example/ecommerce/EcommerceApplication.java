package org.example.ecommerce;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.model.ClientOrder;
import org.example.ecommerce.repository.ClientOrderRepository;
import org.example.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    /*
    CommandLineRunner executa as instruções assim que o programa é executado,
    existe uma injeção de dependência de ClientRepository dentro do método execute.
    */

    @Bean
    public CommandLineRunner execute(
            @Autowired ClientRepository clientRepository,
            @Autowired ClientOrderRepository orderRepository
            ) {
        return args -> {

            //CRUD JPARepository da classe ClientRepository
            //Salva Clientes no banco de dados em memória H2 usando JPARepository
            clientRepository.save(new Client("Charles Amorim", 15));
            clientRepository.save(new Client("Bruce Wayne Jr", 16));
            clientRepository.save(new Client("Bilbo Bolseiro", 17));

            System.out.println();
            System.out.println("Exibindo Clientes");
            List<Client> all = clientRepository.findAll();
            all.forEach(System.out::println);

            System.out.println();
            System.out.println("Buscando por nome com query HQL");
            all = clientRepository.encontrarPorNomeHQL("Way");
            all.forEach(System.out::println);

            System.out.println();
            System.out.println("Buscando por nome com query SQL Nativo");
            all = clientRepository.encontrarPorNomeSQLNATIVO("Way");
            all.forEach(System.out::println);

            //Gerando dois pedidos e adicionando ao client Bruce Wayne
            ClientOrder order = new ClientOrder();
            ClientOrder order2 = new ClientOrder();
            order.setClient(clientRepository.getReferenceById(2));
            order.setDateorder(LocalDate.now());
            order.setTotal(BigDecimal.valueOf(100));
            orderRepository.save(order);

            order2.setClient(clientRepository.getReferenceById(2));
            order2.setDateorder(LocalDate.now());
            order2.setTotal(BigDecimal.valueOf(200));
            orderRepository.save(order2);

            System.out.println("Pedido vinculado ao cliente");
            Client client = clientRepository.findClientFetchOrder(1);
            System.out.println(client);
            System.out.println(client.getOrder());

            client = clientRepository.findClientFetchOrder(2);
            System.out.println(client);
            System.out.println(client.getOrder());

            orderRepository.findByClient(clientRepository.getReferenceById(2)).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
