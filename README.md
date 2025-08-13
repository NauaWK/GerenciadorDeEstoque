# Sistema de Gerenciamento de Estoque (Java + Spring Boot)


## Objetivo deste projeto

Minha primeira aplicação backend usando Java + SpringBoot. Quero por em prática meus conhecimentos teóricos desenvolvendo um sisteminha simples para fins de estudo.


## Sobre

A princípio, o projeto consistirá em um sistema de **gerenciamento de estoque**, permitindo o usuário criar e manipular um estoque genérico de produtos de sua preferência.


 ## Ferramentas Utilizadas

Linguagem Java 21;

Framework Spring Boot 3.5.4;

Banco de dados local H2 para testes;

Persistência de dados com Spring Data JPA;

Swagger UI para facilitar testes e visualização dos endpoints;

Gerenciador de dependências Maven;

Versionamento de código com Git;

IDE NetBeans 26;


## Models

→ Produto

→ Categoria


 ## Arquitetura

Arquitetura em **camadas** com os seguintes folders:

→ Model (aonde estarão as classes marcadas com @Entity)

→ DTO (possuirá sub-folders representando os RequestDTO e ResponseDTO para cada Model) 

→ Mapper (conterá os mappers que converterão DTOs → Model e vice-versa)

→ Controller

→ Service

→ Repository


## Endpoints REST

**Produtos**

POST /adicionarProduto - Adicionar um novo produto ✅️ 

GET /listarProdutos - Consultar os produtos do estoque ✅️

GET /listarProdutos/{id} - Consultar um produto específico pelo ID ✅️

PUT /atualizarProduto/{id} - Atualizar um produto existente ✅️

DELETE /deletarProduto/{id} - Deletar um produto existente ✅️

## Como rodar a aplicação

**Requisitos:**

-Java 17+

-Maven

**Passos:**

1. Clone o repositório com Git ou baixe o arquivo ZIP e extraia
2. Navegue até a pasta do projeto
3. Execute o comando: mvn spring-boot:run
4. Caso esteja utilizando uma IDE como IntelliJ, Eclipse ou NetBeans, basta importar o projeto como Maven e executar a classe principal com a anotação @SpringBootApplication

**URLs principais:**

→ Após iniciar a aplicação, você pode acessar:

Swagger UI: http://localhost:8080/swagger-ui/index.html

Console do banco H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:EstoqueDeProdutos

Usuário: sa

Senha: (deixe em branco ou conforme configurado)


## OBSERVAÇÕES ⚠️

*Este projeto foi desenvolvido como parte de meus estudos em backend com Java e Spring Boot. Embora seja uma aplicação simples e com escopo genérico, seu objetivo principal é consolidar conhecimentos em arquitetura de APIs REST, persistência com Spring Data JPA e boas práticas de desenvolvimento. Futuramente, pretendo expandir suas funcionalidades e aplicar conceitos mais avançados conforme evoluo tecnicamente.*
