package model;

import java.io.Serializable;

/**
 * The SAnsQuestion question class for the short answer
 * question type and extends the abstract question class.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class SAnsQuestion extends Question implements Serializable {
    /**
     * Correct answer string.
     */
    private final String myCorrectAnswer;

    /**
     * Constructor to create short answer question.
     *
     * @param theQuestionID id of the question in database.
     * @param theQuestion the actual question in string form.
     * @param theCorrectAnswer the correct answer for the current question.
     */
    public SAnsQuestion(final int theQuestionID, final String theQuestion,
                        final String theCorrectAnswer) {

        super(theQuestionID, "SAns", theQuestion);
        this.myCorrectAnswer = theCorrectAnswer;
    }

    /**
     * Retrieve the correct answer for SAns question.
     *
     * @return correct answer for short answer question.
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

}