package com.trabalhoweb3.tomate_service.Service;


import com.trabalhoweb3.tomate_service.dto.PrecoTomateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TomateService {

    @Value("${tomate.preco.base:50.00}")
    private double precoBase;

    public PrecoTomateResponse calcularPreco(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0");
        }

        double totalSemDesconto = quantidade * precoBase;
        double percentualDesconto = calcularPercentualDesconto(quantidade);
        double valorDesconto = totalSemDesconto * (percentualDesconto / 100);
        double totalComDesconto = totalSemDesconto - valorDesconto;

        // Arredondar para 2 casas decimais
        BigDecimal totalArredondado = BigDecimal.valueOf(totalComDesconto).setScale(2, RoundingMode.HALF_UP);
        BigDecimal descontoArredondado = BigDecimal.valueOf(valorDesconto).setScale(2, RoundingMode.HALF_UP);

        return PrecoTomateResponse.builder()
                .quantidade(quantidade)
                .precoBase(precoBase)
                .totalSemDesconto(totalSemDesconto)
                .percentualDesconto(percentualDesconto)
                .valorDesconto(descontoArredondado.doubleValue())
                .totalComDesconto(totalArredondado.doubleValue())
                .build();
    }

    private double calcularPercentualDesconto(int quantidade) {
        if (quantidade <= 9) {
            return 0.0;
        } else if (quantidade <= 19) {
            return 5.0;
        } else if (quantidade <= 29) {
            return 11.0;
        } else {
            return 22.0;
        }
    }

    public double obterPrecoBase() {
        return precoBase;
    }

    public void atualizarPrecoBase(double novoPreco) {
        if (novoPreco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que 0");
        }
        this.precoBase = novoPreco;
    }
}