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

→ ProdutoModel (Entidade)

**Atributos ProdutoModel:**

→ Id

→ nome

→ preco

→ quantidade

→ dataAdicao

→ dataModificacao


 ## Arquitetura

Arquitetura em **camadas** com:

→ Produto (Model/Entity)

→ ProdutoRequestDTO (**requisição** do usuário não conterá todos os atributos referentes ao Model - apenas nome, preco e quantidade - porém a **resposta** ao usuário conterá todos os campos, incluindo Id, dataAdicao e dataModificacao)

→ ProdutoResponseDTO

→ ProdutoController

→ ProdutoService

→ ProdutoRepository


## Endpoints REST

POST /adicionarProduto - Adicionar um novo produto ✅️ 

GET /listarProdutos - Consultar os produtos do estoque ✅️

GET /listarProdutos/{id} - Consultar um produto específico pelo ID ✅️

PUT /atualizarProduto/{id} - Atualizar um produto existente ✅️

DELETE /deletarProduto/{id} - Deletar um produto existente ✅️


## OBSERVAÇÕES ⚠️

*Este projeto foi desenvolvido como parte de meus estudos em backend com Java e Spring Boot. Embora seja uma aplicação simples e com escopo genérico, seu objetivo principal é consolidar conhecimentos em arquitetura de APIs REST, persistência com Spring Data JPA e boas práticas de desenvolvimento. Futuramente, pretendo expandir suas funcionalidades e aplicar conceitos mais avançados conforme evoluo tecnicamente.*
