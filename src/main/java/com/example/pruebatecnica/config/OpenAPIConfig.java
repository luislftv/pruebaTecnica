package com.example.pruebatecnica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .openapi("3.1.0")
                .info(new Info()
                        .title("Torneo API")
                        .version("1.0")
                        .description("APIRest para la gestión de torneos y partidos")
                        .contact(new Contact()
                                .name("Luis Fernando Tristancho Vejarano")
                                .email("lftv960708@gmail.com")));

    }
}
