package model;

public class TFQuestion extends Question {
    /**
     * ADD JAVADOC!!
     */
    private String myCorrectAnswer;

    /**
     * ADD JAVADOC!!
     * @param theQuestionID
     * @param theQuestion
     * @param theCorrectAnswer
     */
    public TFQuestion(final int theQuestionID, final String theQuestion,
                      final String theCorrectAnswer) {

        super(theQuestionID, "TF", theQuestion);
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