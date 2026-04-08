SELECT u.nome AS usuario,
m.valor, m.data_aplicacao
FROM multa m
JOIN usuario u ON m.usuario_id = u.id
WHERE m.data_pagamento IS NULL
ORDER BY m.data_aplicacao ASC;