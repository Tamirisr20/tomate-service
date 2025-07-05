package com.example.mashupservice.model;

public class DetalhesCalculo {
    private double precoTomate;
    private double custoFrete;
    private double subtotal;
    private double lucro;
    private double desconto;
    private double valorComLucroEDesconto;
    private double impostos;

    public DetalhesCalculo(double precoTomate, double custoFrete, double subtotal, double lucro, double desconto, double valorComLucroEDesconto, double impostos) {
        this.precoTomate = precoTomate;
        this.custoFrete = custoFrete;
        this.subtotal = subtotal;
        this.lucro = lucro;
        this.desconto = desconto;
        this.valorComLucroEDesconto = valorComLucroEDesconto;
        this.impostos = impostos;
    }

    // Getters e Setters
    public double getPrecoTomate() {
        return precoTomate;
    }

    public void setPrecoTomate(double precoTomate) {
        this.precoTomate = precoTomate;
    }

    public double getCustoFrete() {
        return custoFrete;
    }

    public void setCustoFrete(double custoFrete) {
        this.custoFrete = custoFrete;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorComLucroEDesconto() {
        return valorComLucroEDesconto;
    }

    public void setValorComLucroEDesconto(double valorComLucroEDesconto) {
        this.valorComLucroEDesconto = valorComLucroEDesconto;
    }

    public double getImpostos() {
        return impostos;
    }

    public void setImpostos(double impostos) {
        this.impostos = impostos;
    }
}