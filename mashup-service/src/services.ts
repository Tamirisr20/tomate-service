import axios from 'axios';
import { GraphQLClient, gql } from 'graphql-request';

const TOMATE_SERVICE_URL = 'http://localhost:8080/api/tomate';
const FRETE_SERVICE_URL = 'http://localhost:8081/graphql';

// Service to interact with the tomate-service REST API
export const tomateService = {
  getPrecoTomate: async (quantidade: number) => {
    const response = await axios.post(`${TOMATE_SERVICE_URL}/calcular`, { quantidade });
    return response.data;
  },
};

// Service to interact with the frete-service GraphQL API
const freteClient = new GraphQLClient(FRETE_SERVICE_URL);

interface FreteResponse {
  calcularFrete: {
    custoFinalFrete: number;
  };
}

const GET_FRETE_QUERY = gql`
  query CalcularFrete($quantidadeCaixas: Int!, $distanciaKm: Float!) {
    calcularFrete(quantidadeCaixas: $quantidadeCaixas, distanciaKm: $distanciaKm) {
      custoFinalFrete
    }
  }
`;

export const freteService = {
  getFrete: async (quantidadeCaixas: number, distanciaKm: number) => {
    const response = await freteClient.request<FreteResponse>(GET_FRETE_QUERY, {
      quantidadeCaixas,
      distanciaKm,
    });
    return response.calcularFrete;
  },
};