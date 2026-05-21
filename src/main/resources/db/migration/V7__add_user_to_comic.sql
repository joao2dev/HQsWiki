-- V7__add_user_to_comic.sql

ALTER TABLE usuario ADD PRIMARY KEY (id);

ALTER TABLE comic ADD COLUMN usuario_id BIGINT;

ALTER TABLE comic
    ADD CONSTRAINT fk_comic_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario(id);