package model;

import java.io.Serializable;

/**
 * The abstract class question that will be use in MCQuestion, SAnsQuestion, and TFQuestion.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public abstract class Question implements Serializable {

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
     * Constructor to initialize the questions.
     *
     * @param theQuestionID id of the question in database.
     * @param theQuestion the actual question in string form.
     * @param theQuestionType the type of question for the current question.
     */
    public Question(final int theQuestionID, final String theQuestionType,
                    final String theQuestion) {
        this.myQuestionID = theQuestionID;
        this.myQuestionType = theQuestionType;
        this.myQuestion = theQuestion;
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
    public void setQuestion(final String theQuestion) {
        this.myQuestion = theQuestion;
    }

    /**
     * Getter to get the question type.
     */
    public String getQuestionTypeType() {
        return myQuestionType;
    }

    /**
     * Abstract method to be implemented by subclass to get correct answer.
     */
    public abstract String getCorrectAnswer();
}
