SELECT l.nome,
       COUNT(r.id) AS total_reservas
FROM reserva r
         JOIN livro l ON r.livro_id = l.id
GROUP BY l.id, l.nome
ORDER BY total_reservas DESC;