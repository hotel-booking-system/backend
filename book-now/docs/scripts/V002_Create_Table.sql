-- Cria a tabela "bedrooms"
CREATE TABLE bedrooms
(
    id          BIGINT                                        NOT NULL AUTO_INCREMENT,
    number      VARCHAR(10)                                   NOT NULL,
    room_type   ENUM ('SINGLE', 'DOUBLE', 'FAMILY', 'LUXURY') NOT NULL,
    capacity    INT                                           NOT NULL CHECK (capacity >= 1 AND capacity <= 10),
    daily_rate  DECIMAL(10, 2)                                NOT NULL CHECK (daily_rate >= 0.01),
    description VARCHAR(255)                                  NOT NULL,
    available   BOOLEAN                                       NOT NULL,
    hotel_id    BIGINT                                        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);

-- Cria a tabela "hotels"
CREATE TABLE hotels
(
    id           BIGINT                                                       NOT NULL AUTO_INCREMENT,
    hotel_name   VARCHAR(255)                                                 NOT NULL UNIQUE,
    location     VARCHAR(255)                                                 NOT NULL,
    phone_number VARCHAR(15)                                                  NOT NULL,
    email        VARCHAR(255)                                                 NOT NULL UNIQUE,
    hotel_type   ENUM ('HOTEL', 'RESORT', 'HOSTEL', 'GUESTHOUSE', 'FARMSTAY') NOT NULL,
    cnpj_number  VARCHAR(14)                                                  NOT NULL UNIQUE,
    description  VARCHAR(255)                                                 NOT NULL,
    room_count   INT                                                          NOT NULL,
    created_at   DATETIME                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);