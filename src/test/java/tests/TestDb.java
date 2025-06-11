package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DatabaseUtils;
import utils.H2Util;

public class TestDb {

    public static void main(String[] args) {
        try {
            // Create table, clean talbe
            DatabaseUtils.criarTabelaLogins();
            DatabaseUtils.limparTabelaLogins();

            // Insert
            DatabaseUtils.inserirLogin("teste@teste.com", "senha123", "redirecionado para pagina inicial");
            DatabaseUtils.inserirLogin("teste2@teste.com", "senhaErrada", "mensagem de erro");

            // Open connection for search data and print by resultSet
            try (Connection conn = H2Util.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT email, senha, resultado_esperado FROM logins")) {

                System.out.println(" -Data found in the database: ");

                while (rs.next()) {
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String resultado = rs.getString("resultado_esperado");

                    System.out.println("Email: " + email + ", Senha: " + senha + ", Expected result: " + resultado);
                }
            }

            // Check connection
            try (Connection conn = H2Util.getConnection()) {
                if (conn.isValid(2)) {
                    System.out.println("- Connection to H2 database valid!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
