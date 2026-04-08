--- LIMPEZA PRÉVIA (Opcional, mas ajuda a não duplicar dados ao testar) ---
DELETE
FROM multa;
DELETE
FROM reserva;
DELETE
FROM emprestimos;
DELETE
FROM livro;
DELETE
FROM usuario;
DELETE
FROM autor;
DELETE
FROM editora;
DELETE
FROM categoria;

--- 1. CATEGORIAS ---
INSERT INTO categoria (nome, descricao)
VALUES ('Ficção Científica', 'Livros que exploram tecnologia futurista e viagens espaciais.'),
       ('Romance', 'Obras focadas em relacionamentos interpessoais e amorosos.'),
       ('Terror', 'Histórias destinadas a causar medo e suspense.'),
       ('Biografia', 'Relatos da vida de personalidades reais.'),
       ('História', 'Análises e registros de eventos passados da humanidade.');

--- 2. EDITORAS ---
INSERT INTO editora (nome, endereco)
VALUES ('Companhia das Letras', 'Rua Bandeira Paulista, 702 - São Paulo'),
       ('Editora Rocco', 'Avenida Presidente Wilson, 231 - Rio de Janeiro'),
       ('Grupo Editorial Record', 'Rua Argentina, 171 - Rio de Janeiro'),
       ('Intrínseca', 'Rua Marquês de São Vicente, 99 - Rio de Janeiro'),
       ('Sextante', 'Rua Voluntários da Pátria, 45 - Rio de Janeiro');

--- 3. AUTORES ---
INSERT INTO autor (nome, data_nascimento, nacionalidades)
VALUES ('Isaac Asimov', '1920-01-02', 'Russo-Americano'),
       ('Machado de Assis', '1839-06-21', 'Brasileiro'),
       ('Stephen King', '1947-09-21', 'Americano'),
       ('Agatha Christie', '1890-09-15', 'Britânica'),
       ('Clarice Lispector', '1920-12-10', 'Brasileira');

--- 4. LIVROS ---
INSERT INTO livro (autor_id, editora_id, categoria_id, nome, ano_publicacao, genero, status)
VALUES (1, 1, 1, 'Fundação', '1951-06-01', 'Ficção Científica', 'Disponivel'),
       (2, 2, 2, 'Dom Casmurro', '1899-12-31', 'Romance', 'Disponivel'),
       (3, 3, 3, 'O Iluminado', '1977-01-28', 'Terror', 'Emprestado'),
       (4, 4, 1, 'Assassinato no Expresso Oriente', '1934-01-01', 'Suspense', 'Perdido'),
       (5, 5, 2, 'A Hora da Estrela', '1977-10-26', 'Drama', 'Perdido'),
       (5, 5, 1, 'Harry Potter', '1977-10-26', 'Fantasia', 'Em Manutenção'),
       (1, 1, 1, 'Eu, Robô', '1950-12-02', 'Ficção Científica', 'Disponivel'),
       (3, 3, 3, 'It: A Coisa', '1986-09-15', 'Terror', 'Disponivel'),
       (4, 4, 1, 'Morte no Nilo', '1937-11-01', 'Suspense', 'Disponivel'),
       (2, 2, 2, 'Memórias Póstumas de Brás Cubas', '1881-01-01', 'Romance', 'Disponivel'),
       (3, 3, 3, 'Carrie', '1974-04-05', 'Terror', 'Emprestado');

--- 5. USUÁRIOS ---
INSERT INTO usuario (nome, email, data_registro)
VALUES ('João Silva', 'joao.silva@email.com', '2024-01-10'),
       ('Maria Oliveira', 'maria.oliveira@email.com', '2024-02-15'),
       ('Carlos Souza', 'carlos.souza@email.com', '2024-03-20'),
       ('Ana Costa', 'ana.costa@email.com', '2024-04-05'),
       ('Beatriz Santos', 'beatriz.santos@email.com', '2024-05-12');

--- 6. EMPRÉSTIMOS ---
INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao)
VALUES (1, 1, '2024-06-01', '2024-06-15'),
       (2, 2, '2024-06-05', NULL),
       (3, 3, '2024-06-10', '2024-06-20'),
       (7, 1, '2026-03-01', NULL),
       (8, 2, '2025-05-10', '2025-05-25'),
       (9, 3, '2025-08-15', '2025-08-30'),
       (10, 4, '2026-01-20', '2026-02-05');


--- 7. RESERVAS ---
INSERT INTO reserva (livro_id, usuario_id, data_reserva, status)
VALUES (2, 1, '2026-02-10', 'Pendente'),
       (2, 4, '2026-03-15', 'Pendente'),
       (2, 5, '2026-04-01', 'Pendente'),
       (3, 4, '2024-07-02', 'Confirmada'),
       (7, 3, '2026-03-20', 'Confirmada'),
       (10, 2, '2026-04-05', 'Ativa');

--- 8. MULTAS ---
INSERT INTO multa (usuario_id, valor, data_aplicacao, data_pagamento)
VALUES (1, 15.50, '2024-06-16', '2024-06-18'),
       (2, 10.00, '2026-04-01', NULL), -- Multa recente em aberto
       (5, 25.00, '2024-07-01', NULL);