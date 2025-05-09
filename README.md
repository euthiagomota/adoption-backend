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

Você pode verificar com os comandos abaixo:

```bash
java -version
mvn -version
```

> ⚠️ Se o terminal retornar erro ou comando não reconhecido, certifique-se de que o Java/Maven está instalado e que o `JAVA_HOME` e o `PATH` estejam configurados corretamente.

---

### 📦 Clonando o Projeto

```bash
git clone https://github.com/euthiagomota/adoption-backend.git
cd adoption-backend
```

### ▶️ Executando a Aplicação

```bash
mvn spring-boot:run
```

A aplicação será iniciada e estará disponível em:  
[http://localhost:8080](http://localhost:8080)
