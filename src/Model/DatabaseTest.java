package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        // Test the database connection
        try (Connection connection = Database.connect()) {
            if (connection != null) {
                System.out.println("Database connection successful!");

                // Show the information from the Questions table
                System.out.println("Questions table:");
                displayQuestions(connection);

                // Show the information from the Answers table
                System.out.println("Answers table:");
                displayAnswers(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display the data from the Questions table
    private static void displayQuestions(Connection connection) throws SQLException {
        String query = "SELECT * FROM Questions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionType = rs.getString("QuestionType");
                String question = rs.getString("Question");

                System.out.println("QuestionID: " + questionID);
                System.out.println("QuestionType: " + questionType);
                System.out.println("Question: " + question);
                System.out.println();
            }
        }
    }

    // Method to display the data from the Answers table
    private static void displayAnswers(Connection connection) throws SQLException {
        String query = "SELECT * FROM Answers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String answer = rs.getString("Answers");

                System.out.println("QuestionID: " + questionID);
                System.out.println("Answer: " + answer);
                System.out.println();
            }
        }
    }
}
