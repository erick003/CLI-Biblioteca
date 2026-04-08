package commands;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UsuarioComandos {

    public static void cadastrarUsuario(Connection connection, Scanner scanner){
        scanner.nextLine();
        try {
            System.out.println("\n--- Cadastrar Novo Usuario ---\"");

            System.out.println("Nome do Usuário");
            String nome = scanner.nextLine();

            System.out.println("Email do Usuário");
            String email = scanner.nextLine();

            LocalDate dataRegistro = LocalDate.now();

            try {
                String sql = "INSERT INTO usuario(nome, email, data_registro) VALUES (?,?,?)";

                PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, nome);
                psmt.setString(2, email);
                psmt.setDate(3, Date.valueOf(dataRegistro));
                psmt.executeUpdate();
                System.out.println("Usuario cadastrado com sucesso!");
            }catch (Exception e){
                System.out.println("Erro ao cadastrar Usuario: "+ e.getMessage());
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao cadastrar Usuario: "+ e.getMessage());;
        }
    }

    public static void consultaDeUsuarioPorId(Connection connection, Scanner scanner) {
        scanner.nextLine();

        try {
            System.out.println("\n--- Consultar Reservas de Um Usuário ---");
            System.out.print("ID do Usuário: ");
            int usuarioId = Integer.parseInt(scanner.nextLine());

            String sql = "SELECT l.nome, r.data_reserva, r.status " +
                    "FROM reserva r " +
                    "JOIN livro l ON r.livro_id = l.id " +
                    "WHERE r.usuario_id = ?";

            try (PreparedStatement psmt = connection.prepareStatement(sql)) {
                psmt.setInt(1, usuarioId);

                try (ResultSet rs = psmt.executeQuery()) {
                    System.out.println("\n--- Reservas Encontradas ---");
                    boolean temReserva = false;

                    while (rs.next()) {
                        temReserva = true;
                        String nomeLivro = rs.getString("nome");
                        Date data = rs.getDate("data_reserva");
                        String status = rs.getString("status");

                        System.out.println("Livro: " + nomeLivro + " | Data: " + data + " | Status: " + status);
                    }

                    if (!temReserva) {
                        System.out.println("Nenhuma reserva encontrada para este usuário.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: O ID deve ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("Erro ao buscar reserva: " + e.getMessage());
        }
    }
}
