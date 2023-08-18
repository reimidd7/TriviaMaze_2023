package model;

import java.io.Serializable;

/**
 * The multiple choice question class for the multiple choice
 * question type and extends the abstract question class.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class MCQuestion extends Question implements Serializable {

    /**
     * Correct answer string.
     */
    private final String myCorrectAnswer;

    /**
     * Constructor to create multiple choice question.
     *
     * @param theQuestionID id of the question in database.
     * @param theQuestion the actual question in string form.
     * @param theCorrectAnswer the correct answer for the current question.
     */
    public MCQuestion(final int theQuestionID, final String theQuestion,
                      final String theCorrectAnswer) {

        super(theQuestionID, "MC", theQuestion);
        this.myCorrectAnswer = theCorrectAnswer;
    }

    /**
     * Retrieve the correct answer for multiple choice question.
     *
     * @return correct answer for multiple choice question.
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

}