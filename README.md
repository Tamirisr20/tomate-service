# Projeto Mashup com Serviços REST e GraphQL

Este projeto consiste em três serviços: `tomate-service`, `frete-service`, e `mashup-service`. Abaixo estão as instruções para executar cada um deles.

## Pré-requisitos

- Java 21 ou superior
- Maven
- Node.js e npm

## 1. Executando o `tomate-service`

O `tomate-service` é um serviço RESTful construído com Spring Boot.

1.  Abra um terminal e navegue até o diretório `tomate-service`:
    ```bash
    cd tomate-service
    ```

2.  Compile e empacote a aplicação usando o Maven Wrapper:
    ```bash
    ./mvnw.cmd package
    ```

3.  Execute o arquivo JAR gerado:
    ```bash
    java -jar target/tomate-service-0.0.1-SNAPSHOT.jar
    ```

O serviço estará disponível em `http://localhost:8080/swagger-ui/index.html#/Serviço%20de%20Preço%20de%20Tomate/calcularPreco`.

## 2. Executando o `frete-service`

O `frete-service` é um serviço GraphQL também construído com Spring Boot.

1.  Abra um novo terminal e navegue até o diretório `frete/frete`:
    ```bash
    cd frete/frete
    ```

2.  Execute a aplicação usando o Maven Wrapper:
    ```bash
    ./mvnw.cmd spring-boot:run
    ```

O serviço estará disponível em `http://localhost:8081/graphql`.

## 3. Executando o `mashup-service`

O `mashup-service` é um serviço GraphQL que consome os outros dois serviços.

1.  Abra um terceiro terminal e navegue até o diretório `mashup-service`:
    ```bash
    cd mashup-service
    ```

2.  Instale as dependências do Node.js:
    ```bash
    npm install
    ```

3.  Inicie o serviço em modo de desenvolvimento:
    ```bash
    npm run dev
    ```

O serviço estará disponível em `http://localhost:4000`.

## Verificando a Integração

Com os três serviços em execução, você pode fazer uma requisição para o `mashup-service` para testar a integração. Use um cliente GraphQL de sua preferência para enviar a seguinte query para `http://localhost:4000`:

```graphql
query {
  calcularVenda(quantidadeCaixas: 10, distanciaKm: 100) {
    precoTomate
    custoFrete
    lucro
    desconto
    impostos
    valorFinal
  }
}