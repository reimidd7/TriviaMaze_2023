package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionData {
    /**
     * ADD JAVADOC!!
     */
    private static final String DB_URL = "jdbc:sqlite:questions.db";
    /**
     * ADD JAVADOC!!
     */
    private final Connection myDBConnection;

    /**
     * ADD JAVADOC!!
     */
    public QuestionData() {
        this.myDBConnection = connect();
    }


    /**
     * ADD JAVADOC!!
     * @return
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

                switch (questionType) {
                    case "MC" -> {
                        final String correctAnswerMC = getCorrectAnswerForQuestion(questionID);
                        question = new MCQuestion(questionID, questionText, correctAnswerMC);
                    }
                    case "TF" -> {
                        final String correctAnswerTF = getCorrectAnswerForQuestion(questionID);
                        question = new TFQuestion(questionID, questionText, correctAnswerTF);
                    }
                    case "SAns" -> {
                        final String correctAnswerSAns =
                                getCorrectAnswerForQuestion(questionID);
                        question = new SAnsQuestion(questionID,
                                questionText, correctAnswerSAns);
                    }
                    default -> {
                    }
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return question;
    }

    //ADD A QUICK COMMENT AS TO WHY WE NEED THIS METHOD
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

    //ADD A QUICK COMMENT AS TO WHY WE NEED THIS METHOD
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
