package com.example.mashupservice.model;

public class VendaResponse {
    private double valorFinal;
    private DetalhesCalculo detalhes;

    public VendaResponse(double valorFinal, DetalhesCalculo detalhes) {
        this.valorFinal = valorFinal;
        this.detalhes = detalhes;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public DetalhesCalculo getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(DetalhesCalculo detalhes) {
        this.detalhes = detalhes;
    }
}