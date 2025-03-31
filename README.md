# SmartCityInfo

## Descrição

O **SmartCityInfo** é um projeto desenvolvido para gerenciar informações sobre cidades, avaliações, e usuários de uma plataforma. A aplicação permite criar, ler, atualizar e excluir dados de cidades, avaliações e usuários através de uma API RESTful, utilizando o **Spring Boot** e **JPA** para persistência de dados.

## Tecnologias Utilizadas

- **Spring Boot** - Framework para desenvolvimento de aplicações Java baseadas em microserviços.
- **Spring Data JPA** - Framework de persistência para integração com bancos de dados relacionais.
- **JUnit 5** - Framework de testes para Java, utilizado para escrever testes unitários.
- **MockMvc** - Utilizado para realizar testes de integração HTTP em controladores REST.
- **H2 Database** - Banco de dados em memória utilizado para testes e desenvolvimento.
- **Spring Security** (se aplicável) - Para a gestão de segurança na aplicação.

## Funcionalidades

- **Gerenciamento de Cidades**: CRUD (Criar, Ler, Atualizar e Excluir) para cidades.
- **Avaliação das Cidades**: CRUD para avaliações feitas em diferentes cidades.
- **Gerenciamento de Usuários**: CRUD para usuários da plataforma.
- **Exceções Personalizadas**: Exceções lançadas em casos de erros como não encontrar um usuário ou cidade.
- **Validação de Dados**: Uso de validações de campos com as anotações `@NotBlank` e `@Size` para garantir integridade dos dados.

## Endpoints da API

A aplicação fornece os seguintes endpoints para interação com os dados:

### **Cidades**

- **GET** `/cities` - Retorna uma lista de todas as cidades.
- **GET** `/cities/{code}` - Retorna os dados de uma cidade específica pelo código.
- **POST** `/cities` - Cria uma nova cidade.
- **PUT** `/cities/{code}` - Atualiza os dados de uma cidade existente.
- **DELETE** `/cities/{code}` - Deleta uma cidade pelo código.

### **Avaliações**

- **GET** `/avaliations` - Retorna uma lista de todas as avaliações.
- **GET** `/avaliations/{code}` - Retorna uma avaliação específica pelo código.
- **POST** `/avaliations` - Cria uma nova avaliação.
- **PUT** `/avaliations/{code}` - Atualiza uma avaliação existente.
- **DELETE** `/avaliations/{code}` - Deleta uma avaliação pelo código.

### **Usuários**

- **GET** `/users` - Retorna uma lista de todos os usuários.
- **GET** `/users/{code}` - Retorna os dados de um usuário específico pelo código.
- **POST** `/users` - Cria um novo usuário.
- **PUT** `/users/{code}` - Atualiza os dados de um usuário existente.
- **DELETE** `/users/{code}` - Deleta um usuário pelo código.

## Como Rodar o Projeto

### Pré-requisitos

Antes de rodar o projeto, certifique-se de ter o **Java 17+** e o **Maven** instalados no seu ambiente.

### Passos para rodar:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/smartcityinfo.git
   cd smartcityinfo
2. **Rode o Projeto:**
  ```bash
   mvn spring-boot:run
   mvn test
   
