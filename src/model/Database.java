package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class Database {
    /**
     * URL to the database file.
     */
    private static final String DB_URL = "jdbc:sqlite:questions.db";

    private Database() { }

    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}