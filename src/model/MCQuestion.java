package model;

public class MCQuestion extends Question {
    /**
     * ADD JAVADOC!!!
     */
    private String myCorrectAnswer;

    public MCQuestion(final int theQuestionID, final String theQuestion,
                      final String theCorrectAnswer) {

        super(theQuestionID, "MC", theQuestion);
        this.myCorrectAnswer = theCorrectAnswer;
    }


    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    public void setCorrectAnswer(final String theCorrectAnswer) {
        this.myCorrectAnswer = theCorrectAnswer;
    }
}