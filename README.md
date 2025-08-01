# TaskPilot - Backend

**TaskPilot** é o backend de um sistema de gerenciamento de tarefas e projetos. Construído com **Spring Boot**, este projeto fornece uma **API segura** para gerenciar tarefas, usuários e suas interações, servindo como a espinha dorsal de uma plataforma colaborativa.

---

## Funcionalidades Principais

- **Gerenciamento de Projetos e Tarefas**  
  Crie, edite, exclua e visualize projetos e tarefas.

- **Autenticação e Autorização**  
  Sistema de autenticação seguro com Spring Security, inicialmente configurado para permitir todas as requisições e facilitar o desenvolvimento.

- **Integração com Banco de Dados**  
  Persistência de dados utilizando PostgreSQL.

- **API RESTful**  
  Endpoints claros e bem definidos para interação com o frontend.

---

## Tecnologias Utilizadas

- **Java 17**  
  Linguagem principal do projeto.

- **Spring Boot 3.5.3**  
  Framework principal do backend.

- **Maven**  
  Gerenciador de dependências.

- **Spring Data JPA**  
  Camada de persistência de dados.

- **Spring Security**  
  Segurança da aplicação.

- **PostgreSQL**  
  Banco de dados relacional.

- **Lombok**  
  Redução de código boilerplate.

- **Docker & Docker Compose**  
  Facilidade na execução e no gerenciamento do ambiente de desenvolvimento.

---

## Configuração e Execução

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```env
DB_NAME=taskpilot_db
DB_USERNAME=user
DB_PASSWORD=password
```

### Executando com Docker Compose
Certifique-se de que o Docker e o Docker Compose estão instalados e em execução.

Crie o arquivo .env conforme a seção anterior.

No terminal, na raiz do projeto, execute:

```
docker-compose up --build
```
A aplicação estará disponível em: http://localhost:8080
