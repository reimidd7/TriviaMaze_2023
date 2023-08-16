package model;

/**
 * The TF question class for the TF question type and
 * extends the abstract question class.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class TFQuestion extends Question {
    /**
     * Correct answer string.
     */
    private String myCorrectAnswer;

    /**
     * Constructor to create True/False question.
     *
     * @param theQuestionID id of the question in database.
     * @param theQuestion the actual question in string form.
     * @param theCorrectAnswer the correct answer for the current question.
     */
    public TFQuestion(final int theQuestionID, final String theQuestion,
                      final String theCorrectAnswer) {

        super(theQuestionID, "TF", theQuestion);
        this.myCorrectAnswer = theCorrectAnswer;
    }

    /**
     * Retrieve the correct answer for TF question.
     *
     * @return correct answer either True or False.
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

//    /**
//     * set the correct answer for the TF question.
//     *
//     * @param theCorrectAnswer
//     */
//    public void setCorrectAnswer(final String theCorrectAnswer) {
//        this.myCorrectAnswer = theCorrectAnswer;
//    }
}