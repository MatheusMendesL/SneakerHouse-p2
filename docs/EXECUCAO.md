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

Na primeira execução, o sistema cria as tabelas e insere **3 tênis**.

---

## 3. Frontend (Angular)

```bash
cd frontend
npm install
npm start
```

- Aplicação: http://localhost:4200
