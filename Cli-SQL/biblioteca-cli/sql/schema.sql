CREATE TABLE categoria(
                          id SERIAL PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          descricao TEXT
);

CREATE TABLE editora(
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        endereco VARCHAR(100) NOT NULL
);

CREATE TABLE autor (
                       id SERIAL PRIMARY KEY,
                       nome varchar(50) NOT NULL,
                       data_nascimento DATE NOT NULL,
                       nacionalidades VARCHAR(50) NOT NULL
);



CREATE TABLE livro (
                       id SERIAL PRIMARY KEY,
                       autor_id INT REFERENCES autor(id),
                       editora_id INT REFERENCES editora(id),
                       categoria_id INT REFERENCES categoria(id),
                       nome VARCHAR(50) NOT NULL,
                       ano_publicacao DATE NOT NULL,
                       genero VARCHAR(50) NOT NULL
);

CREATE TABLE usuario(
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(50) NOT NULL,
                        email VARCHAR(254) NOT NULL,
                        data_registro DATE NOT NULL
);

CREATE TABLE emprestimos(
                            livro_id INT REFERENCES livro(id),
                            usuario_id INT REFERENCES usuario(id),
                            id SERIAL PRIMARY KEY,
                            data_emprestimo DATE NOT NULL,
                            data_devolucao DATE
);

CREATE TABLE reserva(
                        livro_id INT REFERENCES livro(id),
                        usuario_id INT REFERENCES usuario(id),
                        id SERIAL PRIMARY KEY,
                        data_reserva date,
                        status VARCHAR(50)
);

CREATE TABLE multa(
                      usuario_id INT REFERENCES usuario(id),
                      id SERIAL PRIMARY KEY,
                      valor DECIMAL(12,2) NOT NULL,
                      data_aplicacao DATE NOT NULL,
                      data_pagamento DATE
);