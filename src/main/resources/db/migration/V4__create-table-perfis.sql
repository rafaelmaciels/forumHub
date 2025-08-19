CREATE TABLE perfis (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

-- Insere um perfil padrão para usuários
INSERT INTO perfis(nome) VALUES('ROLE_USER');