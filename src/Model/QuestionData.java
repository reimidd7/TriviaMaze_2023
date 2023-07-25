package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class QuestionData {
    private static final String DB_URL = "jdbc:sqlite:questions.db";
    private Connection dbConnection;

    public QuestionData() {
        this.dbConnection = connect();
    }

    private Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    Question retrieveQuestion() {
        Question question = null;
        String query = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT 1";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionText = rs.getString("Question");
                String questionType = rs.getString("QuestionType");

                switch (questionType) {
                    case "MC":
                        String correctAnswerMC = getCorrectAnswerForQuestion(questionID);
                        question = new MCQuestion(questionID, questionText, correctAnswerMC);
                        break;
                    case "TF":
                        String correctAnswerTF = getCorrectAnswerForQuestion(questionID);
                        question = new TFQuestion(questionID, questionText, correctAnswerTF);
                        break;
                    case "SAns":
                        String correctAnswerSAns = getCorrectAnswerForQuestion(questionID);
                        question = new SAnsQuestion(questionID, questionText, correctAnswerSAns);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return question;
    }

    private String getCorrectAnswerForQuestion(int questionID) throws SQLException {
        String correctAnswer = null;
        String query = "SELECT Answers FROM Answers WHERE QuestionID = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, questionID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                correctAnswer = rs.getString("Answers");
            }
        }

        return correctAnswer;
    }
}
