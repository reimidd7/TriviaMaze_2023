package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database for the question and answers.
 * Also hold the get random question and the get answer from database.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class QuestionData {
    /**
     * URL to the database file.
     */
    private static final String DB_URL = "jdbc:sqlite:questions.db";
    /**
     * Database connection used to interact with the question database.
     */
    private final Connection myDBConnection;

    /**
     * contractor to establishes a database connection.
     */
    public QuestionData() {
        this.myDBConnection = connect();
    }


    /**
     * retrieves a random question from database either TF, SAns or Multiply choice.
     * @return question
     */
    public Question retrieveQuestion() {
        Question question = null;
        final String query = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT 1";

        try (PreparedStatement stmt = myDBConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                final int questionID = rs.getInt("QuestionID");
                final String questionText = rs.getString("Question");
                final String questionType = rs.getString("QuestionType");
                final String correctAnswer = getCorrectAnswerForQuestion(questionID);

                switch (questionType) {
                    case "MC" -> {
                        question = new Question(questionID, Question.QuestionType.MC, questionText, correctAnswer);
                    }
                    case "TF" -> {
                        question = new Question(questionID, Question.QuestionType.TF, questionText, correctAnswer);
                    }
                    case "SAns" -> {
                        question = new Question(questionID, Question.QuestionType.SAns, questionText, correctAnswer);
                    }
                    default -> {
                        // Handle unexpected question type if needed
                    }
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return question;
    }


    /**
     * Establishes connection to the question database.
     * @return connection
     */
    private Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (final ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Retrieve the correct answer for the question.
     * @param theQuestionID id of the question in database.
     * @return CorrectAnswer the correct answer for the question.
     * @throws SQLException for if the database cannot be found.
     */
    private String getCorrectAnswerForQuestion(final int theQuestionID) throws SQLException {
        String correctAnswer = null;
        final String query = "SELECT Answers FROM Answers WHERE QuestionID = ?";

        try (PreparedStatement stmt = myDBConnection.prepareStatement(query)) {
            stmt.setInt(1, theQuestionID);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                correctAnswer = rs.getString("Answers");
            }
        }

        return correctAnswer;
    }
}
