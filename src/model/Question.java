package model;

/**
 * The abstract class question that will be use in MCQuestion, SAnsQuestion, and TFQuestion.
 */
public class Question {
    /**
     * Question string.
     */
    protected String myQuestion;

    /**
     * Type of question.
     */
    public enum QuestionType {
        TF, SAns, MC
    }

    protected QuestionType myQuestionType;

    /**
     * QuestionID string.
     */
    protected int myQuestionID;

    /**
     * Correct answer string
     */
    protected String myCorrectAnswer;

    /**
     * Constructor to initialize the questions.
     *
     * @param myQuestionID
     * @param myQuestionType
     * @param myQuestion
     * @param myCorrectAnswer
     */
    public Question(int myQuestionID, QuestionType myQuestionType, String myQuestion, String myCorrectAnswer) {
        this.myQuestionID = myQuestionID;
        this.myQuestionType = myQuestionType;
        this.myQuestion = myQuestion;
        this.myCorrectAnswer = myCorrectAnswer;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public void setQuestion(String question) {
        this.myQuestion = question;
    }

    public QuestionType getQuestionType() {
        return myQuestionType;
    }

    public int getQuestionID() {
        return myQuestionID;
    }

    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.myCorrectAnswer = correctAnswer;
    }
}
