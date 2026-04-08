
INSERT INTO categoria (nome, descricao) VALUES
                                            ('Ficção Científica', 'Livros que exploram tecnologia futurista e viagens espaciais.'),
                                            ('Romance', 'Obras focadas em relacionamentos interpessoais e amorosos.'),
                                            ('Terror', 'Histórias destinadas a causar medo e suspense.'),
                                            ('Biografia', 'Relatos da vida de personalidades reais.'),
                                            ('História', 'Análises e registros de eventos passados da humanidade.');


INSERT INTO editora (nome, endereco) VALUES
                                         ('Companhia das Letras', 'Rua Bandeira Paulista, 702 - São Paulo'),
                                         ('Editora Rocco', 'Avenida Presidente Wilson, 231 - Rio de Janeiro'),
                                         ('Grupo Editorial Record', 'Rua Argentina, 171 - Rio de Janeiro'),
                                         ('Intrínseca', 'Rua Marquês de São Vicente, 99 - Rio de Janeiro'),
                                         ('Sextante', 'Rua Voluntários da Pátria, 45 - Rio de Janeiro');


INSERT INTO autor (nome, data_nascimento, nacionalidades) VALUES
                                                              ('Isaac Asimov', '1920-01-02', 'Russo-Americano'),
                                                              ('Machado de Assis', '1839-06-21', 'Brasileiro'),
                                                              ('Stephen King', '1947-09-21', 'Americano'),
                                                              ('Agatha Christie', '1890-09-15', 'Britânica'),
                                                              ('Clarice Lispector', '1920-12-10', 'Brasileira');


INSERT INTO livro (autor_id, editora_id, categoria_id, nome, ano_publicacao, genero) VALUES
                                                                                         (1, 1, 1, 'Fundação', '1951-06-01', 'Ficção Científica'),
                                                                                         (2, 2, 2, 'Dom Casmurro', '1899-12-31', 'Romance'),
                                                                                         (3, 3, 3, 'O Iluminado', '1977-01-28', 'Terror'),
                                                                                         (4, 4, 1, 'Assassinato no Expresso Oriente', '1934-01-01', 'Suspense'),
                                                                                         (5, 5, 2, 'A Hora da Estrela', '1977-10-26', 'Drama');

-- 5. Inserindo Usuários
INSERT INTO usuario (nome, email, data_registro) VALUES
                                                     ('João Silva', 'joao.silva@email.com', '2024-01-10'),
                                                     ('Maria Oliveira', 'maria.oliveira@email.com', '2024-02-15'),
                                                     ('Carlos Souza', 'carlos.souza@email.com', '2024-03-20'),
                                                     ('Ana Costa', 'ana.costa@email.com', '2024-04-05'),
                                                     ('Beatriz Santos', 'beatriz.santos@email.com', '2024-05-12');

-- 6. Inserindo Empréstimos (Referenciando Livros e Usuários)
INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES
                                                                                    (1, 1, '2024-06-01', '2024-06-15'),
                                                                                    (2, 2, '2024-06-05', NULL), -- Livro ainda não devolvido
                                                                                    (3, 3, '2024-06-10', '2024-06-20'),
                                                                                    (4, 4, '2024-06-12', NULL), -- Livro ainda não devolvido
                                                                                    (5, 5, '2024-06-15', '2024-06-30');

-- 7. Inserindo Reservas
INSERT INTO reserva (livro_id, usuario_id, data_reserva, status) VALUES
                                                                     (1, 2, '2024-07-01', 'Pendente'),
                                                                     (3, 4, '2024-07-02', 'Confirmada'),
                                                                     (5, 1, '2024-07-03', 'Cancelada'),
                                                                     (2, 3, '2024-07-04', 'Pendente'),
                                                                     (4, 5, '2024-07-05', 'Finalizada');

-- 8. Inserindo Multas
INSERT INTO multa (usuario_id, valor, data_aplicacao, data_pagamento) VALUES
                                                                          (1, 15.50, '2024-06-16', '2024-06-18'),
                                                                          (2, 10.00, '2024-07-01', NULL), -- Multa em aberto
                                                                          (3, 5.00, '2024-06-21', '2024-06-22'),
                                                                          (5, 25.00, '2024-07-01', NULL), -- Multa em aberto
                                                                          (4, 12.75, '2024-07-10', NULL);