package commands;

import config.DataBaseConfig;
import java.sql.*;

public class LivrosCommand {
    public static void listarLivros() {
        String sql = """
            SELECT l.titulo, a.nome as autor, e.nome as editora, c.nome as categoria 
            FROM livros l
            JOIN autores a ON l.id_autor = a.id_autor
            JOIN editoras e ON l.id_editora = e.id_editora
            JOIN categorias c ON l.id_categoria = c.id_categoria
            """;

        try (Connection conn = DataBaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
            while (rs.next()) {
                System.out.printf("Livro: %-20s | Autor: %-15s | Editora: %-10s | Gênero: %s%n",
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getString("categoria"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
    }
}