# 🛠️ Backend - CRUD de Categorias e Produtos (Spring Boot)

Este é o backend do projeto **CRUD de Categorias e Produtos**, desenvolvido em **Spring Boot** com autenticação via **JWT** e banco de dados **PostgreSQL**.

---

## 🚀 Tecnologias Utilizadas
- **Java 8** (ou superior)
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger (OpenAPI)**
- **Maven**

---

## 📌 1️⃣ Configuração do Banco de Dados (PostgreSQL)
1. Certifique-se de ter o **PostgreSQL** instalado.
2. Crie um banco de dados chamado **`testeJoin`**.
3. Atualize as configurações no arquivo **`application.properties`**:

📄 **`src/main/resources/application.properties`**
```
properties
spring.application.name=teste_dev

# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/testeJoin
spring.datasource.username=postgres
spring.datasource.password=senha
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração do Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Configuração do CORS
spring.web.cors.allowed-origins=http://localhost:4200
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*

# Porta do Servidor
server.port=8080
```

---

📌 2️⃣ Instalar Dependências
No terminal, dentro da pasta testeDev, execute:
```
mvn clean install
```

---

📌 3️⃣ Iniciar o Servidor
```
mvn spring-boot:run
```
✅ O backend estará disponível em http://localhost:8080.

---

📌 4️⃣ Testar API no Swagger

Após subir o backend, acesse: 👉 Swagger UI
```
http://localhost:8080/swagger-ui/
```
Você pode testar as APIs diretamente pelo Swagger.

🔑 Autenticação JWT
Endpoint de Login: /auth/login
Exemplo de Requisição (POST):
```
{
  "username": "admin",
  "password": "password"
}
```
Resposta (Token JWT):
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

Todas as requisições autenticadas devem incluir o token JWT no Header:
```
Authorization: Bearer <token_aqui>
```

---

📌 Endpoints Principais

| Método | Rota                      | Descrição                              |
|--------|---------------------------|----------------------------------------|
| POST   | `/auth/login`             | Autenticação do usuário (JWT).         |
| GET    | `/categorias`             | Lista todas as categorias.             |
| POST   | `/categorias`             | Cadastra uma nova categoria.           |
| DELETE | `/categorias/{id}`        | Deleta uma categoria específica pelo ID.|
| GET    | `/produtos`               | Lista todos os produtos.               |
| POST   | `/produtos/categoria/{id}`| Cadastra um produto em uma categoria específica pelo ID. |

---
📌 Testes Unitários
Para rodar os testes unitários:
```
mvn test
```
---
📌 Estrutura do Projeto
```
/testeDev
│── /src/main/java/com/josehumberto/testeDev
│   ├── Config/        # Configurações do projeto (CORS, JWT, Security)
│   ├── Controller/    # Endpoints da API
│   ├── Dto/           # Objetos de transferência de dados (DTOs)
│   ├── Exception/     # Exception customizadas
│   ├── Filter/        # Filtros customizados
│   ├── Model/         # Entidades do banco de dados
│   ├── Repository/    # Interfaces para acesso ao banco (JPA)
│   ├── Service/       # Regras de negócio
│   ├── Util/          # Utilitários da aplicação
│── /src/main/resources
│   ├── application.properties   # Configurações do banco e servidor
│── /src/test/java/com/josehumberto/testeDev
│   ├── Controller/    # Testes dos Endpoints da API
│── pom.xml         # Dependências do projeto
│── README.md       # Documentação do backend
```
