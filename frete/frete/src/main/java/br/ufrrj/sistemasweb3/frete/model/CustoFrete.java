package br.ufrrj.sistemasweb3.frete.model;

/**
 * Representa a estrutura de dados para a resposta do cálculo de frete.
 */
public record CustoFrete(
        String veiculoUtilizado,
        double distanciaKm,
        double custoPorKm,
        double custoTotalKm,
        double taxaFixa,
        double custoFinalFrete
) {}