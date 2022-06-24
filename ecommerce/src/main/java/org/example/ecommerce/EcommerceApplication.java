package org.example.ecommerce;

import org.example.ecommerce.model.Client;
import org.example.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    /*
    CommandLineRunner executa as instruções assim que o programa é executado,
    existe uma injeção de dependência de ClienteRepository dentro do método execute.
    */

    @Bean
    public CommandLineRunner execute(@Autowired ClientRepository repository) {
        return args -> {

            //Testando CRUD JdbcTemplate da classe ClientRepository
            //Salva Clientes no banco de dados em memória H2 usando JdbcTemplate
            repository.save(new Client("Charles Amorim", 15));
            repository.save(new Client("Bruce Wayne", 16));
            repository.save(new Client("Bilbo Bolseiro", 17));

            //Exibe todos os cliente registrados
            System.out.println();
            System.out.println("Exibindo Clientes");
            List<Client> all = repository.findAll();
                all.forEach(System.out::println);

            //Atualiza o ultimo nome dos cliente com 'Atualizado' e soma 10 anos em cada um
            all.forEach(client -> {
                client.setName(client.getName() + " Atualizado");
                client.setAge(client.getAge() + 10);
                repository.update(client);
            });

            //Exibe todos os cliente com dados atualizados
            System.out.println();
            System.out.println("Clientes Atualizados");
            all = repository.findAll();
                all.forEach(System.out::println);

            //Localiza o cliente por nome
            System.out.println();
            System.out.println("Buscar por Nome 'Wayne'");
            repository.findByName("Wayne").forEach(System.out::println);

            //Deleta um cliente por Id
            repository.delete(3);

            //Delete um cliente identificado pelo Id
            System.out.println();
            System.out.println("Cliente com id 3 Deletado");
            all = repository.findAll();
                all.forEach(System.out::println);

            //Exibe todos os cliente e demonstra que o cliente com id 3 foi deletado
            System.out.println();
            System.out.println("Exibindo Clientes");
            all = repository.findAll();
                all.forEach(System.out::println);

            //Deleta todos os clientes
            System.out.println();
            System.out.println("Todos Deletados");
            repository.findAll().forEach(client -> {
                repository.delete(client);
            });

            //Exibe todos os cliente e demonstra que foram deletados
            System.out.println();
            System.out.println("Exibindo Clientes");
            all = repository.findAll();
            all.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
