# 🎨 Frontend - CRUD de Categorias e Produtos (Angular)

Este é o frontend do projeto **CRUD de Categorias e Produtos**, desenvolvido em **Angular** e integrado com um backend em **Spring Boot**.

---

## 🚀 Tecnologias Utilizadas
- **Angular 19+**
- **TypeScript**
- **Bootstrap**
- **RxJS**
- **Angular Router**
- **Angular Forms**
- **HttpClient**

---

## 📌 1️⃣ Instalar Dependências
No terminal, dentro da pasta **`frontCrud`**, execute:

npm install

---

## 📌 2️⃣ Iniciar o Servidor Angular
Para rodar o frontend:

ng serve --open

✅ O frontend estará disponível em http://localhost:4200.

---

## 📌 3️⃣  🔑 Login no Sistema
Usuário: admin
Senha: password
O login gera um token JWT, que é salvo no localStorage e enviado nas requisições autenticadas.

---

## 📌 4️⃣  Endpoints Utilizados
- **POST** `/auth/login`
  - **Descrição**: Autenticação do usuário (JWT).

### Categorias

- **GET** `/categorias`
  - **Descrição**: Lista todas as categorias.

- **POST** `/categorias`
  - **Descrição**: Cadastra uma nova categoria.

- **DELETE** `/categorias/{id}`
  - **Descrição**: Deleta uma categoria específica pelo ID.

### Produtos

- **GET** `/produtos`
  - **Descrição**: Lista todos os produtos.

- **POST** `/produtos/categoria/{id}`
  - **Descrição**: Cadastra um produto em uma categoria específica pelo ID.

---

## 📌 5️⃣  Estrutura do Projeto
```
/frontCrud
│── /src
│   ├── /app
│   │   ├── /components        # Componentes do projeto (Login, Listagem, etc)
│   │   ├── /services          # Serviços HTTP para comunicação com backend
│   │   ├── app.routes.ts      # Configuração de Rotas
│   │   ├── app.config.ts      # Configurações Globais
│   ├── index.html              # Página principal
│── angular.json                # Configuração do Angular CLI
│── package.json                # Dependências do projeto
│── README.md                   # Documentação do frontend
```
