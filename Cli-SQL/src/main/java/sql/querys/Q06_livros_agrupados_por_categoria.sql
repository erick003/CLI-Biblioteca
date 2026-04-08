SELECT
    c.nome AS categoria,
    l.nome AS livro
FROM livro l
JOIN categoria c ON  l.categoria_id = c.id
ORDER BY c.nome, l.nome ASC;