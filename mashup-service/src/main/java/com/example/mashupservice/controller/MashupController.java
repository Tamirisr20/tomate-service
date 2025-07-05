package com.example.mashupservice.controller;

import com.example.mashupservice.model.VendaResponse;
import com.example.mashupservice.service.MashupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class MashupController {

    @Autowired
    private MashupService mashupService;

    @QueryMapping
    public Mono<VendaResponse> calcularVenda(@Argument int quantidadeCaixas, @Argument double distanciaKm) {
        return mashupService.calcularVenda(quantidadeCaixas, distanciaKm);
    }
}