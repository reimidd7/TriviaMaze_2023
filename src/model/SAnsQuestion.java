package model;

public class SAnsQuestion extends Question {
    /**
     * ADD JAVADOC!!!
     */
    private String myCorrectAnswer;

    /**
     * ADD JAVADOC!!!
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
     * ADD JAVADOC!!
     * @return
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * ADD JAVADOC!!
     * @param theCorrectAnswer
     */
    public void setCorrectAnswer(final String theCorrectAnswer) {
        this.myCorrectAnswer = theCorrectAnswer;
    }
}