package commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class EmprestimoComandos {

    public static void registrarEmprestimo(Connection connection, Scanner scanner){
        scanner.nextLine();

        try {
            System.out.println("\n--- Registrar Emprestimo ---");

            System.out.println("ID do Livro: ");
            int livroId = parseInt(scanner.nextLine());

            System.out.println("ID do Usuario");
            int usuarioId = parseInt(scanner.nextLine());

            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = null;

            try {
                String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";
                PreparedStatement psmtm = connection.prepareStatement(sql);
                psmtm.setInt(1, livroId);
                psmtm.setInt(2, usuarioId);
                psmtm.setDate(3, Date.valueOf(dataEmprestimo));
                psmtm.setDate(4, Date.valueOf(dataEmprestimo));

                psmtm.executeUpdate();

                System.out.println("Emprestimo realizado com sucesso!");
            }catch (Exception e){
                System.out.println("Erro ao registrar Empréstimo de livro: "+ e.getMessage());
            }


        }catch (Exception e){
            System.out.println("Erro ao registrar Empréstimo de livro: "+ e.getMessage());
        }
    }

    public static void registrarDevolucao(Connection connection, Scanner scanner) {
        scanner.nextLine();

        try {
            System.out.println("\n--- Registrar Devolução ---");

            System.out.print("ID do Empréstimo: ");
            int emprestimoId = parseInt(scanner.nextLine());


            LocalDate dataHoje = LocalDate.now();

            try {

                String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";

                PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setDate(1, Date.valueOf(dataHoje));
                psmt.setInt(2, emprestimoId);

                int update = psmt.executeUpdate();

                if (update > 0) {
                    System.out.println("Devolução registrada com sucesso!");
                } else {
                    System.out.println("Nenhum empréstimo encontrado com o ID informado.");
                }

            } catch (Exception e) {
                System.out.println("Erro ao acessar o banco: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro na entrada de dados: " + e.getMessage());
        }
    }
}
