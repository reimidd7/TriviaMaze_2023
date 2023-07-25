package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:questions.db"; // URL to the database file

    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}