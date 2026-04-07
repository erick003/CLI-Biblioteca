import commands.LivrosCommand; // Importa as funções de livros

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BibliotecaCLI {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/biblioteca";
        String usuario = "postgres";
        String senha = "postgres";
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
        System.out.println("1. Listar Livros");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
//        opcao = scanner.nextInt();

        try (scanner) {
            Connection connection = DriverManager.getConnection(url, usuario, senha);

            if (connection.isValid(5)){
                System.out.println("Deu certo");
            }
        }
        catch (SQLException e){
            System.out.println(e);
            System.out.println("Falhou");
        }
    }
}

