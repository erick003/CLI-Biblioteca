SELECT l.nome,
       u.nome AS com_quem_esta,
       e.data_emprestimo
FROM livro l
         JOIN emprestimos e ON l.id = e.livro_id
         JOIN usuario u ON e.usuario_id = u.id
WHERE e.data_devolucao IS NULL;