package utils;

import java.sql.*;

public class DatabaseUtils {

    public static void criarTabelaLogins() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS logins (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "email VARCHAR(255)," +
                "senha VARCHAR(255)," +
                "resultado_esperado VARCHAR(255))";

        try (Connection conn = H2Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreate);
        }
    }

    public static void inserirLogin(String email, String senha, String resultadoEsperado) throws SQLException {
        String sqlInsert = "INSERT INTO logins (email, senha, resultado_esperado) VALUES (?, ?, ?)";

        try (Connection conn = H2Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            pstmt.setString(3, resultadoEsperado);
            pstmt.executeUpdate();
        }
    }

    public static ResultSet buscarLogins() throws SQLException {
        Connection conn = H2Util.getConnection();
        Statement stmt = conn.createStatement();
        //close conn and stmt after request 
        return stmt.executeQuery("SELECT email, senha, resultado_esperado FROM logins");
    }

    public static void limparTabelaLogins() throws SQLException {
        try (Connection conn = H2Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM logins");
        }
    }
}
