import { tomateService, freteService } from './services';

export const resolvers = {
  Query: {
    calcularVenda: async (_: any, { quantidadeCaixas, distanciaKm }: { quantidadeCaixas: number, distanciaKm: number }) => {
      const precoTomateResponse = await tomateService.getPrecoTomate(quantidadeCaixas);
      const freteResponse = await freteService.getFrete(quantidadeCaixas, distanciaKm);

      const precoTomate = precoTomateResponse.totalComDesconto;
      const custoFrete = freteResponse.custoFinalFrete;

      const custoTotal = precoTomate + custoFrete;

      // Regras de negócio
      const lucro = custoTotal * 0.55;
      let desconto = 0;
      if (quantidadeCaixas > 300) {
        desconto = (custoTotal + lucro) * 0.12;
      } else if (quantidadeCaixas > 50) {
        desconto = (custoTotal + lucro) * 0.075;
      }

      const valorComLucroEDesconto = custoTotal + lucro - desconto;
      const impostos = valorComLucroEDesconto * 0.27;
      const valorFinal = valorComLucroEDesconto + impostos;

      return {
        precoTomate: parseFloat(precoTomate.toFixed(2)),
        custoFrete: parseFloat(custoFrete.toFixed(2)),
        lucro: parseFloat(lucro.toFixed(2)),
        desconto: parseFloat(desconto.toFixed(2)),
        impostos: parseFloat(impostos.toFixed(2)),
        valorFinal: parseFloat(valorFinal.toFixed(2)),
      };
    },
  },
};