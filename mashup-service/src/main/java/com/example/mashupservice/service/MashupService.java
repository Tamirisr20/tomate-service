package com.example.mashupservice.service;

import com.example.mashupservice.model.DetalhesCalculo;
import com.example.mashupservice.model.VendaResponse;
import com.example.mashupservice.dto.PrecoTomateResponse; // Importe o novo DTO
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MashupService {

    private final WebClient webClient;
    private final HttpGraphQlClient graphQlClient;
    private static final Logger logger = LoggerFactory.getLogger(MashupService.class);

    public MashupService(WebClient tomateWebClient, HttpGraphQlClient freteGraphQlClient) {
        this.webClient = tomateWebClient;
        this.graphQlClient = freteGraphQlClient;
    }

    public Mono<VendaResponse> calcularVenda(int quantidadeCaixas, double distanciaKm) {
        logger.info("Iniciando cálculo de venda para {} caixas e {} km", quantidadeCaixas, distanciaKm);

        Mono<Double> precoTomateMono = getPrecoTomate(quantidadeCaixas);
        Mono<Double> custoFreteMono = getCustoFrete(quantidadeCaixas, distanciaKm);

        return Mono.zip(precoTomateMono, custoFreteMono)
                .map(tuple -> {
                    double precoTomate = tuple.getT1();
                    double custoFrete = tuple.getT2();
                    logger.info("Preço do tomate recebido: {}", precoTomate);
                    logger.info("Custo do frete recebido: {}", custoFrete);

                    // Regras de negócio
                    double subtotal = precoTomate + custoFrete;
                    double lucro = subtotal * 0.55;
                    double valorComLucro = subtotal + lucro;

                    double descontoPercentual = 0;
                    if (quantidadeCaixas > 300) {
                        descontoPercentual = 0.12;
                    } else if (quantidadeCaixas > 50) {
                        descontoPercentual = 0.075;
                    }
                    double desconto = valorComLucro * descontoPercentual;
                    double valorComLucroEDesconto = valorComLucro - desconto;

                    double impostos = valorComLucroEDesconto * 0.27;
                    double valorFinal = valorComLucroEDesconto + impostos;

                    logger.info("Cálculo finalizado. Valor final: {}", valorFinal);

                    DetalhesCalculo detalhes = new DetalhesCalculo(precoTomate, custoFrete, subtotal, lucro, desconto, valorComLucroEDesconto, impostos);
                    return new VendaResponse(valorFinal, detalhes);
                });
    }

    private Mono<Double> getPrecoTomate(int quantidade) {
        logger.info("Buscando preço do tomate para {} caixas", quantidade);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/calcular").queryParam("quantidade", quantidade).build())
                .retrieve()
                .bodyToMono(PrecoTomateResponse.class) // Usando o DTO para mais segurança
                .map(PrecoTomateResponse::getTotalComDesconto)
                .doOnSuccess(preco -> logger.info("Sucesso ao buscar preço do tomate: {}", preco))
                .doOnError(error -> logger.error("Erro ao buscar preço do tomate: {}", error.getMessage()));
    }

    private Mono<Double> getCustoFrete(int quantidadeCaixas, double distanciaKm) {
        logger.info("Buscando custo de frete para {} caixas e {} km", quantidadeCaixas, distanciaKm);
        String query = """
                query {
                  calcularFrete(quantidadeCaixas: %d, distanciaKm: %f) {
                    custoFinalFrete
                  }
                }
                """.formatted(quantidadeCaixas, distanciaKm);

        return graphQlClient.document(query)
                .retrieve("calcularFrete.custoFinalFrete")
                .toEntity(Double.class)
                .doOnSuccess(custo -> logger.info("Sucesso ao buscar custo do frete: {}", custo))
                .doOnError(error -> logger.error("Erro ao buscar custo do frete: {}", error.getMessage()));
    }
}