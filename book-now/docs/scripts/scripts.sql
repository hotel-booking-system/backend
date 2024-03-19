CREATE DATABASE IF NOT EXISTS book_now_db;

USE book_now_db;
CREATE TABLE hotels
(
    id           BIGINT(20)                                    NOT NULL AUTO_INCREMENT,
    hotel_name   VARCHAR(255)                                  NOT NULL UNIQUE,
    location     VARCHAR(255)                                  NOT NULL,
    phone_number VARCHAR(20)                                   NOT NULL,
    email        VARCHAR(255)                                  NOT NULL UNIQUE,
    hotel_type   ENUM ('HOTEL', 'RESORT', 'HOSTEL', 'POUSADA') NOT NULL,
    cnpj_number  VARCHAR(14)                                   NOT NULL UNIQUE,
    description  TEXT                                          NOT NULL,
    room_count   INT                                           NOT NULL CHECK (room_count >= 1),
    created_at   DATETIME                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


USE book_now_db;
CREATE TABLE IF NOT EXISTS bedrooms
(
    id          BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hotel_id    BIGINT         NOT NULL,
    room_number INT            NOT NULL,
    andar       INT,
    room_type   VARCHAR(255)   NOT NULL,
    capacidade  INT,
    daily_rate  DECIMAL(10, 2) NOT NULL,
    descricao   TEXT,
    FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);



CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);