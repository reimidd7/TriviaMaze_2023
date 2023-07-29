package model;

/**
 * The SAnsQuestion question class for the short answer
 * question type and extends the abstract question class.
 */
public class SAnsQuestion extends Question {
    /**
     * correct answer string
     */
    private String myCorrectAnswer;

    /**
     * Constructor to create short answer question.
     *
     * @param theQuestionID
     * @param theQuestion
     * @param theCorrectAnswer
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

    /**
     * set the correct answer for the short answer question.
     *
     * @param theCorrectAnswer
     */
    public void setCorrectAnswer(final String theCorrectAnswer) {
        this.myCorrectAnswer = theCorrectAnswer;
    }
}