# HQsWiki

Sistema de gerenciamento de Histórias em Quadrinhos com biblioteca pessoal por usuário, desenvolvido com Spring Boot, interface web via Thymeleaf e autenticação JWT.

**Deploy em produção:** [https://hqswiki.onrender.com](https://hqswiki.onrender.com)

> Projeto em ambiente de demonstração. Ainda sem verificação de email — ao testar, use um email fictício.

---

## Tecnologias

- Java 17+
- Spring Boot
- Spring Security
- PostgreSQL
- Spring Data JPA / Hibernate
- Flyway
- MapStruct
- Thymeleaf
- JUnit
- Maven
- Git / GitHub

---

## Funcionalidades

- Cadastro e autenticação de usuários
- Autenticação com JWT armazenado em cookie HttpOnly
- Biblioteca pessoal — cada usuário gerencia suas próprias HQs
- CRUD completo de HQs
- Busca parcial por título
- Cadastro de múltiplos autores e personagens
- Validação de dados
- DTOs com conversão automática via MapStruct
- Versionamento de banco de dados com Flyway

---

## Segurança

- Autenticação com JWT via cookie HttpOnly
- Sessão stateless
- Rotas protegidas com Spring Security
- Usuário só acessa, edita e deleta suas próprias HQs
- Falhas de autenticação (credencial inválida) são tratadas na camada de aplicação e redirecionam para a tela de login com mensagem de erro, em vez de expor erro cru do framework

---

## Endpoints REST

### Autenticação

```
POST /auth/registrar
POST /auth/login
GET  /auth/logout
```

### HQs

```
GET    /comics
GET    /comics/{id}
POST   /comics
PUT    /comics/{id}
DELETE /comics/{id}
```

### Interface Web

```
GET  /
GET  /auth/ui/login
GET  /auth/ui/registrar
GET  /comics/ui/listar
GET  /comics/ui/adicionar
GET  /comics/ui/editar/{id}
GET  /comics/ui/detalhes/{id}
GET  /comics/ui/buscar?tituloHq=
GET  /comics/ui/deletar/{id}
```

---

## Executando o projeto localmente

1. Configure as variáveis de ambiente:

```
DATABASE_URL=jdbc:postgresql://localhost:5432/hqswiki
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
KEY=sua_chave_secreta_jwt
```

2. Execute:

```
mvn spring-boot:run
```

3. Acesse:

```
http://localhost:8080
```

---

## Arquitetura

```
Controller  →  requisições HTTP
Service     →  regras de negócio
Repository  →  persistência
Model       →  entidades JPA
DTO         →  transferência de dados
Mapper      →  conversão via MapStruct
```

---

## Autor

João Guilherme — Backend Developer (Java)

Projeto desenvolvido para portfólio.
