package com.example.animais;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Classe do tipo @Configuration
Essa classe será escaneada pelo spring ao inicar a aplicação,
as configurações feitas aqui farão parte do Container IOC do Spring,
as @Beans estarão disponíveis para serem chamadas em qualquer classe do projeto.
 */
@Configuration
public class AnimalConfiguration {

    @Bean(name = "cachorro")
    public Animal cachorro(){
        return new Animal() {
            @Override
            public void fazerBarulho() {
                System.out.printf("Au, Au");
            }
        };
    }

    @Bean(name = "gato")
    public Animal gato(){
        return new Animal() {
            @Override
            public void fazerBarulho() {
                System.out.printf("Miau");
            }
        };
    }
}
