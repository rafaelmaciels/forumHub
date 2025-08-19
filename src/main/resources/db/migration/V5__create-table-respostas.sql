CREATE TABLE respostas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    solucao BOOLEAN NOT NULL DEFAULT FALSE,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(topico_id) REFERENCES topicos(id),
    FOREIGN KEY(autor_id) REFERENCES usuarios(id)
);