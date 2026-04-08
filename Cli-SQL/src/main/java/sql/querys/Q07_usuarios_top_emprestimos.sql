SELECT u.nome,
COUNT(e.id) AS total_emprestimos
FROM usuario u
JOIN emprestimos e ON u.id = e.usuario_id
GROUP BY u.id, u.nome
ORDER BY total_emprestimos DESC;