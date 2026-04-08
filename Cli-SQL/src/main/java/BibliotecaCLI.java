

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Scanner;

public class BibliotecaCLI {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/biblioteca";
        String USUARIO = "postgres";
        String SENHA = "postgres";
        String caminhoBase = "Cli-SQL/src/main/java/sql/";
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. setup:create (via schema.sql)");
            System.out.println("2. setup:seed (via seed.sql)");
            System.out.println("3. setup:reset (via reset.sql)");
            System.out.println("============Comandos da biblioteca===========");
            System.out.println("4. livro:list (listar todos os livros)");
            System.out.println("5. livro:disponiveis (listar livros disponiveis)");
            System.out.println("6. livro:add (add livro)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    executarConsulta(connection, caminhoBase + "schema.sql");
                    break;
                case 2:
                    executarConsulta(connection, caminhoBase + "seed.sql");
                    break;
                case 3:
                    executarConsulta(connection, caminhoBase + "reset.sql");
                    break;
                case 4:
                    executarConsulta(connection, caminhoBase+"querys/Q01_todos_livros.sql");
                    break;
                case 5:
                    executarConsulta(connection, caminhoBase+"querys/Q02_livros_disponiveis.sql");
                    break;
                case 6:
                    adicionarLivro(connection, scanner);
                    break;

            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void executarConsulta(Connection connection, String caminho) throws Exception {
        String sql = Files.readString(Path.of(caminho));

        try(Statement stmt = connection.createStatement()) {
            boolean temResultado = stmt.execute(sql);

            if (temResultado){
                ResultSet resultado = stmt.getResultSet();
                ResultSetMetaData metaData = resultado.getMetaData();
                int colunas = metaData.getColumnCount();

                System.out.println("\n---Resultado---");
                while (resultado.next()){
                    for (int i = 1; i <= colunas; i++){
                        System.out.println(metaData.getColumnLabel(i)+ ": "+resultado.getString(i) + " | ");
                    }
                    System.out.println();
                }

            } else {
                System.out.println("Comando executado com sucesso mas sem retorno.");
            }
        }
    }
}

