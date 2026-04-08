SELECT l.nome , l.status, l.ano_publicacao FROM livro l
WHERE l.id  NOT IN (
    SELECT e.livro_id
    FROM emprestimos e
    WHERE e.data_emprestimo BETWEEN NOW() - INTERVAL '6 months' AND NOW()
);