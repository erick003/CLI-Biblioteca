-- Lista todos os livros com seus respectivos autores, editoras e categorias
SELECT
    l.id_livro,
    l.titulo,
    a.nome AS autor,
    e.nome AS editora,
    c.nome AS categoria,
    l.ano_publicacao
FROM livros l
         JOIN autores a ON l.id_autor = a.id_autor
         JOIN editoras e ON l.id_editora = e.id_editora
         JOIN categorias c ON l.id_categoria = c.id_categoria
ORDER BY l.titulo ASC;

SELECT * FROM categoria;
SELECT * FROM editora;
SELECT * FROM autor;
SELECT * FROM livro;
SELECT * FROM usuario;
SELECT * FROM emprestimos;
SELECT * FROM reserva;
SELECT * FROM multa;
