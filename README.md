# 🚀 Desafio CRUD de Clientes - API REST com Spring Boot

Este projeto foi desenvolvido como parte do módulo de Back End da Formação Desenvolvedor Moderno da [DevSuperior](https://devsuperior.com.br/), com foco na criação de uma API REST completa para gerenciamento de clientes.

## 🔧 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Web
- H2 Database
- Bean Validation
- Maven

## 🧠 Funcionalidades

A API permite realizar as operações básicas de um CRUD com a entidade `Client`, contendo os campos:

- `name`
- `cpf`
- `income`
- `birthDate`
- `children`

### Endpoints

- `GET /clients?page=0&size=6&sort=name`: Busca paginada
- `GET /clients/{id}`: Busca por ID
- `POST /clients`: Inserção de cliente
- `PUT /clients/{id}`: Atualização de cliente
- `DELETE /clients/{id}`: Remoção de cliente

## ✅ Validações e Tratamento de Erros

- Nome não pode ser vazio
- Data de nascimento não pode ser futura
- Erro 404 para ID inexistente (GET/PUT/DELETE)
- Erro 422 com mensagens customizadas para campos inválidos

## 🧪 Ambiente de Testes

- Banco de dados H2 em memória configurado
- Seed de 10 clientes com dados significativos

## 💻 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/ThiagoP11/Crud-Clientes.git
