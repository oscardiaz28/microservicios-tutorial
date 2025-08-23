package com.microservicos.usuario_service.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// anotacion en Spring que se usa para indicar que una clase es una clase de configuracion
// significa que Spring va a escanear la clase y a va a registrar dentro de su contenedor
// los objetos que se declaren con @Bean, lo que permite que esten disponibles en el contexto de la app

@Configuration //clase de configuracion de Spring

public class RestTemplateConfiguration {

    @Bean // metodo que produce un objeto administrado por Spring
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
