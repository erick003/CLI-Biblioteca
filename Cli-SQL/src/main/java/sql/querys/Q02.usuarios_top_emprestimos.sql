-- Ranking de usuários que mais pegaram livros emprestados
SELECT
    u.nome,
    u.email,
    COUNT(emp.id_emprestimo) AS total_emprestimos
FROM usuarios u
         JOIN emprestimos emp ON u.id_usuario = emp.id_usuario
GROUP BY u.id_usuario, u.nome, u.email
ORDER BY total_emprestimos DESC
    LIMIT 10;