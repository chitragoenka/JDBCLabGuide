import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.connect();
        main.selectAll();
    }
    public Connection connect() {
        String url = "jdbc:sqlite:C://sqlite/Bookstore.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.connect();
            String sql = "SELECT * FROM Books";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("BookID") + "\t" +
                        rs.getString("Author") + "\t" +
                        rs.getString("Price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection to SQLite has been closed. This confirms it!!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
