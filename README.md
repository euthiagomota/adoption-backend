# Sistema de Adoção de Animais - Back-End

##  Objetivo do Documento
Este documento descreve os requisitos funcionais e técnicos do sistema **Adoção de Animais**, um projeto *full stack* que permite:

- Usuários visualizarem animais disponíveis para adoção.
- Administradores gerenciarem e cadastrarem novos animais.
- Autenticação de usuários com segurança via JWT.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 17+
- Spring Boot
- Spring Security (com JWT)
- Spring Data JPA
- PostgreSQL
- Swagger (via springdoc-openapi)

### 📦 Infraestrutura
- Docker (opcional, para containerização)
- Git e GitHub (controle de versão)

---

## 🗂️ Estrutura de Pastas

```bash
com.seuprojeto.adocaoanimais
├── config         # Configurações gerais (Swagger, segurança, etc)
├── controller     # Controladores REST
├── dto            # Objetos de transferência de dados (Request/Response)
├── entities       # Entidades JPA (mapeamento das tabelas)
├── repositories   # Interfaces de acesso ao banco de dados
├── security       # JWT, filtros, UserDetailsService, etc
└── service        # Regras de negócio da aplicação
```

---

## Como Rodar a Aplicação

### Pré-requisitos

Antes de iniciar, verifique se os seguintes requisitos estão instalados e configurados corretamente na sua máquina:

- [ ] **Java JDK 17** ou superior  
- [ ] **Java** devidamente configurado na variável de ambiente `PATH`
- [ ] **Maven 3.8+**
- [ ]  **Docker e Docker compose**

Você pode verificar com os comandos abaixo:

```bash
java -version
mvn -version
docker --version
docker-compose --version
```

> ⚠️ Se o terminal retornar erro ou comando não reconhecido, certifique-se de que o Java/Maven está instalado e que o `JAVA_HOME` e o `PATH` estejam configurados corretamente.

---

### 📦 Clonando o Projeto

```bash
git clone https://github.com/euthiagomota/adoption-backend.git
cd adoption-backend
```


### 🐋 Iniciando banco de dados com docker-compose

```bash
docker compose up
```

### ▶️ Executando a Aplicação

```bash
mvn spring-boot:run
```

A aplicação será iniciada e estará disponível em:  
[http://localhost:8080](http://localhost:8080)



## 📫 Endpoints Principais

---

### 🔑 Login

**Endpoint:**  
`POST /auth/login`

**Descrição:**  
Autentica o usuário e retorna o token JWT.

**Request Body:**

```json
{
  "email": "usuario@email.com",
  "password": "senha123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 📝 Registro (Cadastro)

**Endpoint:**  
`POST /auth/register`

**Descrição:**  
Cria um novo usuário no sistema.

**Request Body:**

```json
{
  "name": "João da Silva",
  "email": "joao@email.com",
  "password": "senha123",
  "confirmPassword": "senha123",
  "cpf": "808.607.974-11",
  "phone": "(24) 0006-0725"
}
```

**Response:**

```json
{
  "id": 1,
  "name": "João da Silva",
  "email": "joao@email.com",
  "cpf": "808.607.974-11",
  "phone": "(24) 0006-0725",
  "createdAt": "2025-05-12T00:51:02.849Z"
}
```

---

### 👤 Buscar Usuário Autenticado

**Endpoint:**  
`GET /users/me`

**Descrição:**  
Retorna os dados do usuário autenticado com base no token JWT enviado no header.

**Headers:**

```
Authorization: Bearer <seu_token_jwt>
```

**Response:**

```json
{
  "id": 1,
  "name": "João da Silva",
  "email": "joao@email.com",
  "cpf": "808.607.974-11",
  "phone": "(24) 0006-0725",
  "createdAt": "2025-05-12T00:51:02.849Z"
}
```

---

## 🧪 Documentação da API

A documentação interativa (Swagger UI) está disponível em:  
👉 http://localhost:8080/swagger-ui.html

---

## ✅ Considerações Finais

- Todos os dados sensíveis são protegidos via autenticação JWT.
- O projeto foi estruturado com foco em legibilidade, boas práticas e separação de responsabilidades.
- A API está pronta para integração com o frontend React ou qualquer cliente HTTP.
