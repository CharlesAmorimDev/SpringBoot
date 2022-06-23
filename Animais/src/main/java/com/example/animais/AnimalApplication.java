package com.example.animais;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@SpringBootApplication
 */
@SpringBootApplication
@RestController
public class AnimalApplication {

	/* @Value está chamando por interpolação o application.name que é uma configuração do arquivo
	*  application.properties que retorna o nome do perfil ativo (Development ou Production)
	*/
	@Value("${application.name}")
	private String applicationName;

	/*
	@GetMapping é um annotation filha da @RestController, nela foi definido o caminho de acesso pelo navegador /hello
	O caminho padrao do spring é: localhost:8080 ao adicionar /hello no final, ficando localhots:8080/hello
	será executado o método hello() que está dentro do @GetMapping("/hello") que retorna applicationName
	variável que está recebendo application.name atravez de interpolação na annotation @Value
	 */

	@GetMapping("/hello")
	public String hello(){
		return applicationName;
	}

	@Cachorro
	private Animal animal;

	@Bean
	public CommandLineRunner executar() {
		return args -> {
			animal.fazerBarulho();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AnimalApplication.class, args);
	}

}
