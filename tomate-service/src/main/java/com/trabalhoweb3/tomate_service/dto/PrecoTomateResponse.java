package com.trabalhoweb3.tomate_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do cálculo de preço de tomates")
public class PrecoTomateResponse {

    @Schema(description = "Quantidade de caixas solicitadas", example = "15")
    private int quantidade;

    @Schema(description = "Preço base por caixa", example = "50.00")
    private double precoBase;

    @Schema(description = "Total sem desconto", example = "750.00")
    private double totalSemDesconto;

    @Schema(description = "Porcentagem de desconto aplicado", example = "5.0")
    private double percentualDesconto;

    @Schema(description = "Valor do desconto", example = "37.50")
    private double valorDesconto;

    @Schema(description = "Total com desconto aplicado", example = "712.50")
    private double totalComDesconto;

    // Construtor padrão
    public PrecoTomateResponse() {}

    // Construtor com Builder
    private PrecoTomateResponse(Builder builder) {
        this.quantidade = builder.quantidade;
        this.precoBase = builder.precoBase;
        this.totalSemDesconto = builder.totalSemDesconto;
        this.percentualDesconto = builder.percentualDesconto;
        this.valorDesconto = builder.valorDesconto;
        this.totalComDesconto = builder.totalComDesconto;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int quantidade;
        private double precoBase;
        private double totalSemDesconto;
        private double percentualDesconto;
        private double valorDesconto;
        private double totalComDesconto;

        public Builder quantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public Builder precoBase(double precoBase) {
            this.precoBase = precoBase;
            return this;
        }

        public Builder totalSemDesconto(double totalSemDesconto) {
            this.totalSemDesconto = totalSemDesconto;
            return this;
        }

        public Builder percentualDesconto(double percentualDesconto) {
            this.percentualDesconto = percentualDesconto;
            return this;
        }

        public Builder valorDesconto(double valorDesconto) {
            this.valorDesconto = valorDesconto;
            return this;
        }

        public Builder totalComDesconto(double totalComDesconto) {
            this.totalComDesconto = totalComDesconto;
            return this;
        }

        public PrecoTomateResponse build() {
            return new PrecoTomateResponse(this);
        }
    }

    // Getters e Setters
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getTotalSemDesconto() {
        return totalSemDesconto;
    }

    public void setTotalSemDesconto(double totalSemDesconto) {
        this.totalSemDesconto = totalSemDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getTotalComDesconto() {
        return totalComDesconto;
    }

    public void setTotalComDesconto(double totalComDesconto) {
        this.totalComDesconto = totalComDesconto;
    }
}