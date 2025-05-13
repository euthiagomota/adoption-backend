#  Relatório de Testes - Projeto Adoption

##  Objetivo do Documento

Este documento tem como objetivo apresentar um relatório consolidado da execução dos testes automatizados no projeto **Adoption**, evidenciando o comportamento da aplicação nos principais fluxos testados. O foco é validar a estabilidade, integridade e funcionalidade dos componentes centrais do sistema, como autenticação, cadastro e serviços relacionados a usuários. Além disso, o relatório serve como base para avaliação da cobertura de testes e identificação de eventuais pontos de melhoria.

##  Resumo Geral

| Métrica             | Valor   |
|---------------------|---------|
| Total de Testes     | 7       |
| Falhas              | 0       |
| Erros               | 0       |
| Ignorados/Skips     | 0       |
| Tempo Total         | 20.38 s |
| Resultado           | ✅ **BUILD SUCCESS** |

---

##  Detalhes dos Testes Executados

### 1. `AdoptionApplicationTests`
- **Status:** ✅ Sucesso
- **Objetivo:** Verifica se a aplicação carrega corretamente o contexto Spring sem falhas.
- **Duração:** ~12.62 s
- **Banco:** H2 (in-memory)
- **Observações:**
    - Nenhuma falha.
    - Inicialização da aplicação concluída com sucesso .
    - HikariPool configurado corretamente.
    - Spring Security configurado via `AuthenticationProvider`.

---

### 2. `AuthControllerTest`
- **Status:** ✅ Sucesso
- **Objetivo:** Testa o endpoint `/auth/register` nos seguintes cenários:
    - Validação inválida → `400 Bad Request`
    - Registro com sucesso → `201 Created`
- **Duração:** ~1.79 s
- **Testes Executados:** 2
- **Cenários testados:**
    - 🔴 Requisição POST `/auth/register` com dados inválidos:
        - **Status:** `400 Bad Request`
        - **Mensagens de erro:**
            - `"name": "O nome é obrigatório"`
            - `"email": "E-mail inválido"`
            - `"password": "A senha deve ter pelo menos 6 caracteres"`
            - `"phone": "Telefone inválido"`
    - 🟢 Requisição POST `/auth/register` com dados válidos:
        - **Status:** `201 Created`
        - **Resposta:**
          ```json
          {
            "id": 1,
            "name": "João da Silva",
            "email": "joao@email.com",
            "cpf": "808.607.974-11",
            "phone": "(24) 0006-0725",
            "createdAt": "2025-05-13T01:59:02.984+00:00"
          }
          ```

---

### 3. `FindUserServiceTest`
- **Status:** ✅ Sucesso
- **Objetivo:** Verifica a busca de usuário pelo acess token gerado ao logar na aplicação.
- **Testes Executados:** 3
- **Duração:** ~0.36 s

---

### 4. `LoginServiceTest`
- **Status:** ✅ Sucesso
- **Objetivo:** Testa o login do usuário com credenciais válidas e geração de token JWT.
- **Testes Executados:** 1
- **Duração:** ~0.09 s

---

## ℹ️ Observações

- `spring.jpa.open-in-view` está habilitado por padrão. Considere desativá-lo em produção.
- Senha padrão gerada para desenvolvimento:
    - `40561f2d-6055-43bd-a3c4-06d32b1dbb6f` (não deve ser usada em produção).
- Nenhuma exceção não tratada foi detectada durante os testes.

---

🟢 **Todos os testes passaram com sucesso. O sistema está funcional para os casos testados!**
