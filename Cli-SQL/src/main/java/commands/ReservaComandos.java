package commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ReservaComandos {

    public static void registrarReserva(Connection connection, Scanner scanner) {
        scanner.nextLine();

        try {
            System.out.println("\n--- Registrar Nova Reserva ---");

            System.out.print("ID do Livro: ");
            int livroId = parseInt(scanner.nextLine());

            System.out.print("ID do Usuário: ");
            int usuarioId = parseInt(scanner.nextLine());

            LocalDate dataHoje = LocalDate.now();
            String statusInicial = "Pendente";

            String sql = "INSERT INTO reserva (livro_id, usuario_id, data_reserva, status) VALUES (?, ?, ?, ?)";

            try (PreparedStatement psmt = connection.prepareStatement(sql)) {
                psmt.setInt(1, livroId);
                psmt.setInt(2, usuarioId);
                psmt.setDate(3, Date.valueOf(dataHoje));
                psmt.setString(4, statusInicial);

                psmt.executeUpdate();
                System.out.println("Reserva registrada com sucesso! Status: " + statusInicial);

            } catch (Exception e) {
                System.out.println("Erro ao salvar reserva no banco: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro: IDs devem ser números inteiros.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar reserva: " + e.getMessage());
        }
    }
}
