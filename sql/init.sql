-- SneakerHouse - Script de inicialização do banco de dados
-- Execute no MySQL antes de subir o backend (opcional se usar createDatabaseIfNotExist)

CREATE DATABASE IF NOT EXISTS sneakerhouse
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sneakerhouse;

-- As tabelas são criadas automaticamente pelo Hibernate (ddl-auto: update)
-- Os 20 produtos iniciais são inseridos pelo DataInitializer do Spring Boot
