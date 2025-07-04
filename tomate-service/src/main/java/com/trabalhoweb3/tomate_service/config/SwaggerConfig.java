package com.trabalhoweb3.tomate_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Serviço de Preço de Tomate API")
                        .version("1.0")
                        .description("API REST para cálculo de preço de tomates com desconto baseado na quantidade")
                        .contact(new Contact()
                                .name("Tamiris")
                                .email("tamiris@example.com")));
    }
}