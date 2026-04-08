SELECT l.nome, l.genero
FROM livro l
LEFT JOIN reserva r ON l.id = r.livro_id AND r.status = 'Pendente'
WHERE r.id IS NULL;