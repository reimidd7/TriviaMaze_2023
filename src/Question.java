/**
 * The abstract class question that will be use in MCQuestion, SAnsQuestion, and TFQuestion.
 */
public abstract class Question {
    /**
     * Question string.
     */
    protected Stirng Question;
    /**
     * Answer string.
     */
    protected String Answer;
    /**
     * Answer set.
     */
    protected List<String> answerSet;

    /**
     * get the question.
     * @return The question
     */
    private String getQuestion() {
    return getQuestion;
    }

    /**
     *  Sets the question.
     * @param the question
     */
    private void setQuestion(String question){
        this.myQuestion = question;
    }

    /**
     * get the answer.
     * @return the answer.
     */
    private String getAnswer(){
        return myAnswer;
    }

    /**
     * set the answer.
     * @param the answer.
     */
    private void setAnswer(String answer){
        this.Answer = Answer;
    }

    /**
     * Retrieves answer set.
     * @return the asnwer set.
     */

    private List<String> getAnswerSet() {
        return answerSet;
    }

    /**
     * Sets the answer set.
     * @param set the answerSet
     */
    private void setAnswerSet(List<String> answerSet) {
        this.answerSet = answerSet;
    }
}