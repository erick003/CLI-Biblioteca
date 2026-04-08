

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BibliotecaCLI {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/biblioteca";
        String USUARIO = "postgres";
        String SENHA = "postgres";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pasta:" + System.getProperty("user.dir"));

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. iniciar Tabelas (via schema.sql)");
            System.out.println("2. Inserir dados SQL (via seed.sql)");
            System.out.println("3. Apagar banco de dados (via reset.sql)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                String sql = Files.readString(Path.of("Cli-SQL/src/main/java/sql/schema.sql"));

                try (Statement stmt = connection.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Tabelas criadas com sucesso.");
                }
            }

            if (opcao == 2){
                String sql = Files.readString(Path.of("Cli-SQL/src/main/java/sql/seed.sql"));
                try(Statement stmt = connection.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Dados inseridos com sucesso!");
                }
            }

            if (opcao == 3){
                String sql = Files.readString(Path.of("Cli-SQL/src/main/java/sql/reset.sql"));
                try(Statement stmt = connection.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Banco excluido com sucesso!");
                }
            }

//            if (connection.isValid(5)){
//                System.out.println("Deu certo");
//            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

