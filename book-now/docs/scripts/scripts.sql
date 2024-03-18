CREATE DATABASE IF NOT EXISTS book_now_db;

CREATE TABLE IF NOT EXISTS bedrooms
(
    id            BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hotel_id      BIGINT NOT NULL,
    numero_quarto INT,
    andar         INT,
    tipo          VARCHAR(255),
    capacidade    INT,
    preco         DECIMAL,
    descricao     TEXT,
    FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);

CREATE TABLE IF NOT EXISTS hotels
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    localizacao VARCHAR(255) NOT NULL UNIQUE,
    contato     VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    site        VARCHAR(255) NOT NULL,
    descricao   TEXT
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);