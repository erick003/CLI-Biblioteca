

import commands.EmprestimoComandos;
import commands.LivroComandos;
import commands.ReservaComandos;
import commands.UsuarioComandos;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;

import static commands.EmprestimoComandos.registrarDevolucao;
import static commands.EmprestimoComandos.registrarEmprestimo;
import static commands.LivroComandos.adicionarLivro;
import static commands.ExecutarSql.executarConsulta;
import static commands.ReservaComandos.registrarReserva;
import static commands.UsuarioComandos.cadastrarUsuario;
import static commands.UsuarioComandos.consultaDeUsuarioPorId;

public class BibliotecaCLI {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String URL = dotenv.get("URL_DB");
        String USUARIO = dotenv.get("USER_DB");
        String SENHA = dotenv.get("PASS_DB");
        String caminhoBase = "Cli-SQL/src/main/java/sql/";
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
                System.out.println("1. setup:create (via schema.sql)");
                System.out.println("2. setup:seed (via seed.sql)");
                System.out.println("3. setup:reset (via reset.sql)");
                System.out.println("============Comandos da biblioteca===========");
                System.out.println("4. livro:list (listar todos os livros)");
                System.out.println("5. livro:disponiveis (listar livros disponiveis)");
                System.out.println("6. livro:add (add livro)");
                System.out.println("7. livro:sem-emprestimo");
                System.out.println("8. livro:mais-reservados");
                System.out.println("9. livro:por-categoria (lista livros por categoria)");
                System.out.println("==============================================");
                System.out.println("10. usuario:add (add Usuário)");
                System.out.println("11. usuario:list (listar Usuários)");
                System.out.println("12. usuario:top-emprestimos");
                System.out.println("13. usuario:reserva<id> (Lista reservas de um Usuário)");
                System.out.println("14. emprestimo:novo (Registrar emprestimo)");
                System.out.println("15. emprestimo:devolver<id> (Devolver livro)");
                System.out.println("16. reserva:nova (Reserva livro)");
                System.out.println("17. reserva:disponiveis (Livros)");
                System.out.println("18. multas:pendentes");
                System.out.println("19. relatorio:indisponiveis (livros indisponiveis para emprestimo)");
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
                        executarConsulta(connection, caminhoBase + "querys/Q01_todos_livros.sql");
                        break;
                    case 5:
                        executarConsulta(connection, caminhoBase + "querys/Q02_livros_disponiveis.sql");
                        break;
                    case 6:
                        adicionarLivro(connection, scanner);
                        break;
                    case 7:
                        executarConsulta(connection, caminhoBase + "querys/Q03_livros_nao_emprestados_6meses.sql");
                        break;
                    case 8:
                        executarConsulta(connection, caminhoBase + "querys/Q04_top_livros_emprestados.sql");
                        break;
                    case 9:
                        executarConsulta(connection, caminhoBase + "querys/Q06_livros_agrupados_por_categoria.sql");
                    case 10:
                        cadastrarUsuario(connection, scanner);
                        break;
                    case 11:
                        executarConsulta(connection, caminhoBase + "querys/Q05_todos_usuario.sql");
                        break;
                    case 12:
                        executarConsulta(connection, caminhoBase + "querys/Q07_usuarios_top_emprestimos.sql");
                        break;
                    case 13:
                        consultaDeUsuarioPorId(connection, scanner);
                        break;
                    case 14:
                        registrarEmprestimo(connection, scanner);
                        break;
                    case 15:
                        registrarDevolucao(connection, scanner);
                        break;
                    case 16:
                        registrarReserva(connection, scanner);
                        break;
                    case 17:
                        executarConsulta(connection, caminhoBase + "querys/Q08_livros_disponiveis_para_reserva.sql");
                        break;
                    case 18:
                        executarConsulta(connection, caminhoBase + "querys/Q09_listar_multas_pendentes.sql");
                        break;
                    case 19:
                        executarConsulta(connection, caminhoBase+"querys/Q10_livros_indisponiveis_para_emprestimo.sql");
                        break;
                    case 0:
                        System.out.println("Sistema encerrado");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;

                }
            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

