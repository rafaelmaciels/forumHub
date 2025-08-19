CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(autor_id) REFERENCES usuarios(id)
);

ALTER TABLE topicos ADD CONSTRAINT unq_titulo_mensagem UNIQUE(titulo, mensagem(255));