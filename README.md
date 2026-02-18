# Sistema de Gerenciamento de Estoque (Java + Spring Boot)


## Objetivo deste projeto

Minha primeira aplicação backend usando Java + SpringBoot. Quero por em prática meus conhecimentos teóricos desenvolvendo um sistema simples para fins de estudo.


## Sobre

O projeto consistirá em um sistema de **gerenciamento de estoque**, permitindo o usuário criar e manipular um estoque genérico de produtos de sua preferência.


 ## Ferramentas Utilizadas

- Linguagem Java 21

- Framework Spring Boot 3.5.4

- Gerenciador de dependências Maven

- Persistência de dados com Spring Data JPA

- Banco de dados MySQL (via Docker Compose) para simular perfil de produção

- Banco de dados H2 em memória para simular perfil de desenvolvimento

- Swagger UI para documentação da API

- Versionamento de código com Git

- Docker para imagem/containerização


## Entidades

→ Produto

→ Categoria

→ MovimentacaoEstoque (para modificações no estoque e controlar o histórico)


 ## Arquitetura

Arquitetura baseada em camadas com as seguintes separações:

→ entities (classes representando as entidades do banco de dados)

→ dto (RequestDTO/ResponseDTO para cada entidade) 

→ utils (possuirá arquivos utilitários, como mappers, enums, etc)

→ mappers (conterá os mappers que converterão DTOs → entidade e vice-versa)

→ controllers (mapeamento de endpoints, chamadas de serviços e retorno de respostas HTTP)

→ services (regras de negócio e chamadas de repositórios)

→ repositories (camada de persistência, acesso direto ao banco de dados)

→ exception (tratamento centralizado de exceções)

→ docs (documentação da API através de interfaces implementadas nos respectivos controllers)

## Endpoints REST

Para todas as entidades do projeto estão previstas 2 a 4 operações distintas de CRUD

## Como rodar a aplicação

**Requisitos:**

- Java e Maven
- Docker (opcional)

**Passos:**

1. Clone o repositório com Git ou baixe o arquivo ZIP e extraia
2. Navegue até a pasta do projeto no terminal
3. Se não quiser/puder utilizar Docker, rode o comando `mvn spring-boot:run` ou execute via IDE (ativará perfil padrão *dev* com banco H2)
4. Caso queira executar via Docker, rode o comando `docker compose up` (ativará perfil *prod* com banco MySQL)

**Extra:**

→ Após iniciar a aplicação, você pode acessar o Swagger para visualização dos endpoints da aplicação e executar requisições:

*Swagger UI*: `http://localhost:8080/swagger-ui/index.html`

## OBSERVAÇÕES ⚠️

*Este projeto faz parte de meus estudos em backend com Java e Spring Boot. Embora seja uma aplicação simples e com escopo genérico, seu objetivo principal é consolidar conhecimentos em arquitetura de APIs REST, persistência com Spring Data JPA e boas práticas de desenvolvimento. Futuramente, pretendo expandir suas funcionalidades e aplicar conceitos mais avançados conforme evoluo tecnicamente.*
