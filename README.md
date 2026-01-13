# Sistema de Gerenciamento de Estoque (Java + Spring Boot)


## Objetivo deste projeto

Minha primeira aplicação backend usando Java + SpringBoot. Quero por em prática meus conhecimentos teóricos desenvolvendo um sistema simples para fins de estudo.


## Sobre

O projeto consistirá em um sistema de **gerenciamento de estoque**, permitindo o usuário criar e manipular um estoque genérico de produtos de sua preferência.


 ## Ferramentas Utilizadas

Linguagem Java 21;

Framework Spring Boot 3.5.4;

Banco de dados local H2 para testes;

Persistência de dados com Spring Data JPA;

Swagger UI para facilitar testes e visualização dos endpoints;

Gerenciador de dependências Maven;

Versionamento de código com Git;

Criação de imagem da aplicação com Docker;


## Entidades

→ Produto

→ Categoria

→ MovimentacaoEstoque (em desenvolvimento)


 ## Arquitetura

Arquitetura baseada em camadas com as seguintes separações:

→ Model (aonde estarão as classes marcadas com @Entity)

→ Utils (possuirá folders/arquivos utilitários, como mappers, enums etc)

→ DTO (RequestDTO/ResponseDTO para cada Model) 

→ Mappers (conterá os mappers que converterão DTOs → Model e vice-versa)

→ Controller

→ Service

→ Repository

→ Exception (tratamento centralizado de exceções)


## Endpoints REST

Para a maioria das entidades do projeto estão previstas 2 a 4 operações distintas de CRUD.

## Como rodar a aplicação

**Requisitos:**

-Java 21+

-Maven

**Passos:**

1. Clone o repositório com Git ou baixe o arquivo ZIP e extraia
2. Navegue até a pasta do projeto no terminal
3. Execute o comando `mvn spring-boot:run` (caso esteja utilizando uma IDE, basta importar o projeto como Maven e executar a classe principal com a anotação @SpringBootApplication)
4. Caso queira rodar a aplicação via Docker, execute o comando `docker run -p 8080:8080 nauanwk/gerenciador-estoque-springboot` no terminal.

**Extra:**

→ Após iniciar a aplicação, você pode acessar o Swagger para visualização dos endpoints da aplicação e executar requisições:

*Swagger UI*: `http://localhost:8080/swagger-ui/index.html`

## OBSERVAÇÕES ⚠️

*Este projeto **ainda está em desenvolvimento** e faz parte de meus estudos em backend com Java e Spring Boot. Embora seja uma aplicação simples e com escopo genérico, seu objetivo principal é consolidar conhecimentos em arquitetura de APIs REST, persistência com Spring Data JPA e boas práticas de desenvolvimento. Futuramente, pretendo expandir suas funcionalidades e aplicar conceitos mais avançados conforme evoluo tecnicamente.*
