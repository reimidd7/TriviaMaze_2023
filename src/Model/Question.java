package Model;

import java.util.List;

/**
 * The abstract class question that will be use in MCQuestion, SAnsQuestion, and TFQuestion.
 */
public abstract class Question {
    /**
     * Question string.
     */
    protected String myQuestion;
    /**
     * Answer string.
     */
    protected String myQuestionType;
    protected int myQuestionID;

    public Question(int myQuestionID, String myQuestionType, String myQuestion) {
        this.myQuestionID = myQuestionID;
        this.myQuestionType = myQuestionType;
        this.myQuestion = myQuestion;
    }

    String getQuestion() {
        return myQuestion;
    }

    /**
     * Sets the question.
     *
     * @param question the question being asked.
     */
    private void setQuestion(String question) { this.myQuestion = question; }
    public String getQuestionTypeType() {
        return myQuestionType;
    }
    public int getQuestionID() {
        return myQuestionID;
    }
    public abstract String getCorrectAnswer();
}
