package commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class LivroComandos {
    public static void adicionarLivro(Connection connection, Scanner scanner) {
        scanner.nextLine();

        try {
            System.out.println("\n--- Cadastrar Novo Livro ---");

            System.out.print("ID do Autor: ");
            int autorId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID da Editora: ");
            int editoraId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID da Categoria: ");
            int categoriaId = Integer.parseInt(scanner.nextLine());

            System.out.print("Nome do Livro: ");
            String nome = scanner.nextLine();

            System.out.print("Data de Publicação (AAAA-MM-DD): ");
            String data = scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();


            try {
                String sql = "INSERT INTO livro (autor_id, editora_id, categoria_id, nome, ano_publicacao, genero, status) VALUES (?,?,?,?,?,?,?)";

                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, autorId);
                pstmt.setInt(2, editoraId);
                pstmt.setInt(3, categoriaId);
                pstmt.setString(4, nome);
                pstmt.setDate(5, Date.valueOf(data));
                pstmt.setString(6, genero);
                pstmt.setString(7, "Disponivel");

                pstmt.executeUpdate();
                System.out.println("Livro cadastrado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

}
