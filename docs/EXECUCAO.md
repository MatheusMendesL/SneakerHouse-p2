# SneakerHouse — Guia de Execução

## Pré-requisitos

| Ferramenta | Versão mínima |
|------------|---------------|
| Java JDK   | 21            |
| Maven      | 3.9+          |
| Node.js    | 18+           |
| npm        | 9+            |
| MySQL      | 8.0+          |

---

## 1. Banco de dados MySQL

```bash
mysql -u root -p < sql/init.sql
```

Ou execute manualmente:

```sql
CREATE DATABASE sneakerhouse;
```

Ajuste usuário e senha em `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    username: root
    password: root
```

---

## 2. Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

- API: http://localhost:8080/api
- Swagger: http://localhost:8080/swagger-ui.html

Na primeira execução, o sistema cria as tabelas e insere **20 tênis** automaticamente.

---

## 3. Frontend (Angular)

```bash
cd frontend
npm install
npm start
```

- Aplicação: http://localhost:4200

---

## 4. Fluxo de teste ponta a ponta

1. Abra http://localhost:4200 — **Vitrine** com produtos
2. Use a busca na navbar — **Busca**
3. Clique em **Ver Detalhes** — **Detalhe do produto**
4. **Adicionar ao Carrinho**
5. Acesse **Carrinho** — altere quantidade, remova itens
6. **Cadastre-se** em `/cadastro`
7. **Login** em `/login`
8. **Finalizar Pedido** (requer login + JWT)
9. Modal de sucesso e redirecionamento à Home
10. **Esqueci senha** em `/esqueci-senha`

---

## Endpoints principais

| Método | URL | Auth |
|--------|-----|------|
| POST | /api/clientes | Não |
| POST | /api/clientes/login | Não |
| POST | /api/clientes/redefinir-senha | Não |
| GET | /api/produtos/vitrine | Não |
| GET | /api/produtos/busca?nome= | Não |
| GET | /api/produtos/{id} | Não |
| POST | /api/pedidos | Sim (JWT) |

---

## IntelliJ IDEA

1. **File → Open** → pasta `backend`
2. Aguarde o Maven importar dependências
3. Execute `SneakerHouseApplication`

## VS Code

1. Abra a pasta raiz `SneakerHouse`
2. Terminal 1: `cd backend && mvn spring-boot:run`
3. Terminal 2: `cd frontend && npm start`
