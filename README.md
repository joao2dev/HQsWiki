# HQsWiki

Sistema backend completo para gerenciamento de Histórias em Quadrinhos (HQs), desenvolvido com Spring Boot seguindo arquitetura em camadas e boas práticas de desenvolvimento profissional.

A aplicação fornece uma API REST segura com autenticação baseada em JWT, permitindo o gerenciamento completo de HQs, autores e personagens.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot  
- Spring Security  
- PostgreSQL  
- Spring Data JPA  
- Hibernate  
- MapStruct  
- Maven  
- Thymeleaf  
- JUnit  
- Git / GitHub  

---

## 🔐 Autenticação e Segurança

- Autenticação com JWT (JSON Web Token)
- Geração de token no login
- Proteção de rotas com Spring Security  
- Controle de acesso baseado em autenticação
- Sessão stateless

---

## ⚙️ Funcionalidades

- CRUD completo de HQs  
- Cadastro de múltiplos autores e personagens  
- Busca por ID e título  
- Validação de dados  
- Tratamento de erros com respostas padronizadas  
- Uso de DTOs (RequestDTO e ResponseDTO)  
- Conversão automática com MapStruct  
- Interface web com Thymeleaf  

---

## 📡 Endpoints REST

### Autenticação

POST /auth/login

### HQs

POST   /comics  
GET    /comics  
GET    /comics/{id}  
PUT    /comics/{id}  
DELETE /comics/{id}  

---

## 🔄 Exemplo de Autenticação

1. Faça login:

POST /auth/login

2. Receba o token JWT

3. Use nas requisições:

Authorization: Bearer SEU_TOKEN_AQUI

---

## 🧪 Testes

- Testes automatizados com JUnit  
- Cobertura de regras de negócio  
- Validação de comportamentos críticos da aplicação  

---

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

- Controller → Requisições HTTP  
- Service → Regras de negócio  
- Repository → Persistência  
- Model → Entidades  
- DTO → Transferência de dados  
- Mapper → Conversão  

---

## ⚙️ Executando o Projeto

1. Configure as variáveis de ambiente (.env)

Exemplo:

DB_URL=jdbc:postgresql://localhost:5432/hqswiki  
DB_USERNAME=seu_usuario  
DB_PASSWORD=sua_senha  

2. Execute:

mvn spring-boot:run

3. Acesse:

http://localhost:8080

---

## 📌 Diferenciais do Projeto

- Implementação completa de autenticação JWT  
- Estrutura profissional em camadas  
- Código limpo e organizado  
- Uso de boas práticas de backend  
- Integração com PostgreSQL  

---

## 👨‍💻 Autor

João Guilherme  
Backend Developer (Java)

---

Projeto desenvolvido para portfólio.
