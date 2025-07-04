package com.trabalhoweb3.tomate_service.controller;


import com.trabalhoweb3.tomate_service.dto.PrecoTomateRequest;
import com.trabalhoweb3.tomate_service.dto.PrecoTomateResponse;
import com.trabalhoweb3.tomate_service.Service.TomateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/tomate")
@Tag(name = "Serviço de Preço de Tomate", description = "API para cálculo de preço de tomates com desconto")
public class TomateController {

    @Autowired
    private TomateService tomateService;

    @Operation(summary = "Calcular preço total de tomates", 
               description = "Calcula o preço total com desconto aplicado baseado na quantidade de caixas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço calculado com sucesso",
                    content = @Content(schema = @Schema(implementation = PrecoTomateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Quantidade inválida")
    })
    @PostMapping("/calcular")
    public ResponseEntity<PrecoTomateResponse> calcularPreco(
            @Valid @RequestBody PrecoTomateRequest request) {
        
        PrecoTomateResponse response = tomateService.calcularPreco(request.getQuantidade());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Calcular preço por quantidade (GET)", 
               description = "Endpoint alternativo para calcular preço usando parâmetro de query")
    @GetMapping("/calcular")
    public ResponseEntity<PrecoTomateResponse> calcularPrecoPorQuery(
            @Parameter(description = "Quantidade de caixas de tomate", example = "15")
            @RequestParam @Min(value = 1, message = "A quantidade deve ser maior que 0") int quantidade) {
        
        PrecoTomateResponse response = tomateService.calcularPreco(quantidade);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obter preço base atual", 
               description = "Retorna o preço base por caixa de tomate")
    @GetMapping("/preco-base")
    public ResponseEntity<Double> obterPrecoBase() {
        return ResponseEntity.ok(tomateService.obterPrecoBase());
    }

    @Operation(summary = "Atualizar preço base", 
               description = "Atualiza o preço base por caixa de tomate")
    @PutMapping("/preco-base")
    public ResponseEntity<String> atualizarPrecoBase(
            @Parameter(description = "Novo preço base por caixa", example = "55.00")
            @RequestParam @Min(value = 0, message = "O preço deve ser maior que 0") double novoPreco) {
        
        tomateService.atualizarPrecoBase(novoPreco);
        return ResponseEntity.ok("Preço base atualizado para R$ " + novoPreco);
    }
}