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

    public abstract String getCorrectAnswer();



}
