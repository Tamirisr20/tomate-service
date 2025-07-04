package br.ufrrj.sistemasweb3.frete.controller;

import br.ufrrj.sistemasweb3.frete.model.CustoFrete;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FreteController {

    @QueryMapping("calcularFrete")
    public CustoFrete calcularFrete(@Argument int quantidadeCaixas, @Argument double distanciaKm) {

        // Variáveis para armazenar os resultados do cálculo
        String veiculoUtilizado;
        double precoPorKm;
        double taxaFixa;

        // 1. Escolha automática do veículo com base na quantidade de caixas
        if (quantidadeCaixas <= 250) {
            veiculoUtilizado = "Caminhão";
            precoPorKm = 20.00; // Preço para caminhão [cite: 20]
            taxaFixa = 200.00;   // Taxa fixa para caminhão [cite: 21]
        } else {
            veiculoUtilizado = "Carreta";
            precoPorKm = 40.00; // Preço para carreta [cite: 22]
            taxaFixa = 400.00;   // Taxa fixa para carreta [cite: 23]
        }

        // 2. Cálculo do custo da distância, aplicando o desconto quando aplicável
        double custoTotalDistancia;
        if (distanciaKm <= 100) {
            // Sem desconto para distâncias até 100km
            custoTotalDistancia = distanciaKm * precoPorKm;
        } else {
            // Aplica desconto de 20% para os quilômetros que excedem 100km
            double distanciaComDesconto = distanciaKm - 100;
            double precoComDesconto = precoPorKm * 0.80; // Aplica 20% de desconto

            custoTotalDistancia = (100 * precoPorKm) + (distanciaComDesconto * precoComDesconto);
        }

        // 3. Cálculo do custo final do frete
        double custoFinalFrete = custoTotalDistancia + taxaFixa;

        // 4. Retorno do objeto com todos os dados calculados
        return new CustoFrete(
                veiculoUtilizado,
                distanciaKm,
                precoPorKm,           // <-- Ajustado aqui
                custoTotalDistancia,  // <-- Ajustado aqui
                taxaFixa,
                custoFinalFrete
        );
    }
}
