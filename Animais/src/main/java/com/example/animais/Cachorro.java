package com.example.animais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* Classe do tipo annotation
Configuração permite fazer injeção de dependência personalizada com nome de @Cachorro
O @Cachorro foi configurado conter o @Autowired e @Qualifier("cachorro")
---------------------------------------------------------------------------------------
Exemplo da mudança

Modo Tradicional:

@AutoWired
@Qualifier("cachorro")
private Animal animal;
-------------------------------
Usando @Cachorro:

@Cachorro
private Animal animal;
-------------------------------

*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("cachorro")
public @interface Cachorro {

}
