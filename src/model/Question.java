package model;

/**
 * The abstract class question that will be use in MCQuestion, SAnsQuestion, and TFQuestion.
 */
public abstract class Question {
    /**
     * Question string.
     */
    protected String myQuestion;
    /**
     * Type of question string.
     */
    protected String myQuestionType;
    /**
     * QuestionID string.
     */
    protected int myQuestionID;

    /**
     * Constructor to initialize the questions,
     * @param myQuestionID
     * @param myQuestionType
     * @param myQuestion
     */
    public Question(int myQuestionID, String myQuestionType, String myQuestion) {
        this.myQuestionID = myQuestionID;
        this.myQuestionType = myQuestionType;
        this.myQuestion = myQuestion;
    }

    /**
     * Getter to get the question.
     */
   public String getQuestion() {
        return myQuestion;
    }

    /**
     * Setter to set the question.
     */
    private void setQuestion(String question) { this.myQuestion = question; }

    /**
     * Getter to get the question type.
     */
    public String getQuestionTypeType() {
        return myQuestionType;
    }

    /**
     * Getter to get the questionID
     */
    public int getQuestionID() {
        return myQuestionID;
    }

    /**
     * Abstract method to be implemented by subclass to get correct answer.
     */
    public abstract String getCorrectAnswer();
}
