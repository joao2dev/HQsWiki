-- V2__refatoração_comics.sql

DROP TABLE IF EXISTS comic_autores CASCADE;
DROP TABLE IF EXISTS comic_personagens CASCADE;

CREATE TABLE comic_autores (
    comic_id BIGINT NOT NULL,
    autor_nome VARCHAR(255) NOT NULL
);

CREATE TABLE comic_personagens (
    comic_id BIGINT NOT NULL,
    personagem_nome VARCHAR(255) NOT NULL
);