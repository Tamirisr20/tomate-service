package com.example.mashupservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient tomateWebClient(@Value("${tomate.service.url}") String tomateServiceUrl) {
        return WebClient.builder()
                .baseUrl(tomateServiceUrl)
                .build();
    }

    @Bean
    public HttpGraphQlClient freteGraphQlClient(@Value("${frete.service.url}") String freteServiceUrl) {
        return HttpGraphQlClient.builder()
                .url(freteServiceUrl)
                .build();
    }
}