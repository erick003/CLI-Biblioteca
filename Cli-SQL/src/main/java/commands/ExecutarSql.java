package commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ExecutarSql {
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
