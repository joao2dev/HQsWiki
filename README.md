# HQsWiki

Sistema de gerenciamento de Histórias em Quadrinhos com biblioteca pessoal por usuário, desenvolvido com Spring Boot com interface web via Thymeleaf e autenticação JWT.

## Vídeo demonstração
[![Assistir no YouTube](https://img.shields.io/badge/YouTube-Assistir%20Demo-red?logo=youtube)](https://youtu.be/Fr5Gl7e5l10)

---

## Docker

Imagem disponível no Docker Hub:

```bash
docker pull joao2dev/hqswiki
```

[![Docker Hub](https://img.shields.io/docker/pulls/joao2dev/hqswiki?logo=docker)](https://hub.docker.com/r/joao2dev/hqswiki)

### Rodando com Docker Compose

Crie um arquivo `.env` na raiz do projeto:

```env
DATABASE_URL=jdbc:postgresql://db:5432/projetohq
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
KEY=sua_chave_secreta
```

Crie o `docker-compose.yml`:

```yaml
services:
  db:
    image: postgres:16
    container_name: hqswiki-db
    environment:
      POSTGRES_DB: projetohq
      POSTGRES_USER: seu_usuario
      POSTGRES_PASSWORD: sua_senha
    ports:
      - "5432:5432"

  app:
    image: joao2dev/hqswiki
    container_name: hqswiki-app
    ports:
      - "8080:8080"
    env_file:
      - .env
    depends_on:
      - db
```

Suba os containers:

```bash
docker compose up
```

Acesse: [http://localhost:8080/auth/ui/login](http://localhost:8080/auth/ui/login)

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
- Docker

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

## Executando sem Docker

1. Configure as variáveis de ambiente:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/projetohq
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
KEY=sua_chave_secreta
```

2. Execute:

```bash
mvn spring-boot:run
```

3. Acesse:

```
http://localhost:8080/auth/ui/login
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
