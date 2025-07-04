package com.trabalhoweb3.tomate_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Requisição para cálculo de preço de tomates")
public class PrecoTomateRequest {

    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    @Schema(description = "Quantidade de caixas de tomate", example = "15", minimum = "1")
    private Integer quantidade;

    public PrecoTomateRequest() {}

    public PrecoTomateRequest(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}