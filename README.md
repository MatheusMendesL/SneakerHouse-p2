# SneakerHouse

Loja virtual full stack de tênis — **Angular 17** + **Spring Boot 3** + **MySQL**.

![Stack](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green) ![Angular](https://img.shields.io/badge/Angular-17-red) ![MySQL](https://img.shields.io/badge/MySQL-8-blue)

## Estrutura do projeto

```text
SneakerHouse/
├── backend/          # API REST (Java 21, Spring Boot 3, JWT)
├── frontend/         # SPA Angular 17 + Bootstrap 5
├── sql/              # Scripts MySQL
└── docs/             # Documentação de execução
```

## Funcionalidades

### Frontend
- Vitrine com banner e cards de produtos
- Busca por nome
- Detalhe do produto com quantidade
- Carrinho (LocalStorage)
- Login / Cadastro / Esqueci senha
- Finalização de pedido com modal Bootstrap

### Backend
- CRUD de clientes com BCrypt
- Autenticação JWT
- Catálogo de produtos (vitrine, busca, detalhe)
- Pedidos com validação e atualização de estoque
- Swagger OpenAPI
- Tratamento global de exceções
- Seed automático de 20 tênis (Nike, Adidas, Puma, Mizuno, Asics, New Balance)

## Início rápido

```bash
# 1. MySQL
mysql -u root -p < sql/init.sql

# 2. Backend (porta 8080)
cd backend && mvn spring-boot:run

# 3. Frontend (porta 4200)
cd frontend && npm install && npm start
```

Documentação completa: [docs/EXECUCAO.md](docs/EXECUCAO.md)

## URLs

| Serviço | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| API | http://localhost:8080/api |
| Swagger | http://localhost:8080/swagger-ui.html |

## Design

Paleta **preto**, **branco** e **vermelho** — visual moderno inspirado em marcas esportivas, totalmente responsivo.

## Licença

Projeto acadêmico / portfólio.
