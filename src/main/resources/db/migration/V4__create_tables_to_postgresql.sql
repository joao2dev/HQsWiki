-- V1__create_tables.sql

-- Tabela principal de comics
CREATE TABLE comic (
    id BIGSERIAL PRIMARY KEY,
    titulo_hq VARCHAR(255),
    anolancamento INTEGER,
    edicao INTEGER NOT NULL,
    genero VARCHAR(255),
    sinopse TEXT,
    registrocriacao VARCHAR(255),
    img_url VARCHAR(255),
    nome_editora VARCHAR(255)
);



-- Tabela de autores
CREATE TABLE tb_autores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    biografia TEXT
);

-- Tabela de personagens
CREATE TABLE tb_characters (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT
);

-- Tabela de editoras
CREATE TABLE tb_publishers (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT
);


