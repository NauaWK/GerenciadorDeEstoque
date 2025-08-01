# Sistema de Gerenciamento de Estoque – Spring Boot


## Objetivo deste projeto

Minha primeira aplicação backend usando Java + SpringBoot. Quero por em prática meus conhecimentos teóricos desenvolvendo um sisteminha simples para fins de estudo.


## Sobre

A *princípio*, o projeto consistirá em um sistema de **gerenciamento de estoque**, permitindo o usuário/cliente manipular e ter o controle total do seu estoque personalizado.


## Endpoints REST

POST /adicionarProduto - Adicionar um novo produto ✅️ 

GET /listarProdutos - Consultar os produtos do estoque ⌛

PUT /atualizarProduto/{id} - Atualizar um produto existente ⌛

DELETE /deletarProduto/{id} - Deletar um produto existente ⌛


## Models

→ ProdutoModel (@Entity)

*Atributos ProdutoModel:*

→ Id

→ nome

→ preco

→ quantidade

→ dataAdicao


 ## Arquitetura

Arquitetura em camadas com:

→ Model/Entity

→ RequestDTO (**requisição** do usuário não conterá todos os atributos referentes ao Model - apenas nome, preco e quantidade - porém a **resposta** ao usuário conterá todos os campos, incluindo Id e dataAdicao)

→ Controller

→ Service

→ Repository


 ## Ferramentas Utilizadas

Linguagem Java 21;

Framework SpringBoot 3.5.4 (Spring Initializr);

Gerenciador de dependências Maven;

Banco de dados local H2 para testes;

Versionamento de código com Git;

IDE NetBeans 26;


## OBSERVAÇÕES ⚠️

*Sou um estudade e este é meu primeiro projeto usando Java com um framework voltado ao desenvolvimento de APIs. Logo, o projeto pode parecer ser muito simples e genérico, mas é justamente por ser minha primeira aplicação backend, além de que, como dito no tópico "Objetivos deste projeto", consiste principalmente em fins de estudo. Todavia, pretendo incrementá-lo com novas funcionalidades ao longo do tempo (por exemplo, criar mais entidades no banco).*

