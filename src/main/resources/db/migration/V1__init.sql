CREATE TABLE tb_usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE tb_agendamento (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',

    CONSTRAINT fk_agendamento_usuario
        FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id),

    CONSTRAINT ck_intervalo
        CHECK (data_inicio < data_fim),

    CONSTRAINT ck_status
        CHECK (status IN ('AGENDADO', 'CANCELADO', 'CONCLUIDO'))
);