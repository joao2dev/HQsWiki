# HQsWiki

Sistema web completo para gerenciamento de Histórias em Quadrinhos (HQs), desenvolvido com Spring Boot seguindo arquitetura em camadas e boas práticas de desenvolvimento backend.

A aplicação permite cadastrar, consultar, atualizar e remover HQs, incluindo múltiplos autores e personagens, além de disponibilizar interface web com Thymeleaf.

---

## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database
* MapStruct
* Maven
* Thymeleaf
* Git / GitHub

---

## Funcionalidades

* Cadastro de HQ
* Listagem de HQs
* Busca por ID
* Busca por título
* Atualização de dados
* Remoção de HQ
* Cadastro de múltiplos autores
* Cadastro de múltiplos personagens
* Interface web com Thymeleaf
* Uso de DTOs (RequestDTO e ResponseDTO)
* Conversão automática com MapStruct

---

## Arquitetura

O projeto segue arquitetura em camadas visando separação de responsabilidades:

* Controller — Responsável pelas requisições HTTP
* Service — Regras de negócio
* Repository — Persistência de dados
* Model (Entity) — Representação das tabelas
* DTOs — Transferência de dados
* Mapper — Conversão entre entidades e DTOs

Essa organização facilita manutenção, escalabilidade e testes.

---

## Estrutura do Projeto

src/main/java
├── controller
├── service
├── repository
├── model
├── dto
└── mapper

src/main/resources
├── templates
├── static
└── application.properties

---

## Configuração de Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configuração do banco de dados.

Crie um arquivo `.env` na raiz do projeto:

DB_URL=jdbc:h2:mem:testdb
DB_USERNAME=sa
DB_PASSWORD=

No arquivo application.properties:

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

---

## Executando Localmente

1. Configure o arquivo `.env`
2. Execute:

mvn spring-boot:run

A aplicação ficará disponível em:

http://localhost:8080

Console H2:

http://localhost:8080/h2-console

---

## Endpoints REST

POST   /comics
GET    /comics
GET    /comics/{id}
PUT    /comics/{id}
DELETE /comics/{id}

---

## Melhorias Futuras

* Spring Security
* Autenticação e autorização
* Dockerização
* Integração com MySQL em produção
* Documentação com Swagger/OpenAPI
* Testes automatizados

---

## Autor

João Guilherme
Estudante de Desenvolvimento de Sistemas
Experiência com Java, Web e APIs REST

---

Projeto desenvolvido para fins educacionais e portfólio.
