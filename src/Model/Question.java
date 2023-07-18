package Model;

import java.util.List;

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
    protected String myAnswer;
    /**
     * Answer set.
     */
    protected List<String> answerSet;

    /**
     * get the question.
     * @return The question
     */
    private String getQuestion() {
        return myQuestion;
    }

    /**
     *  Sets the question.
     * @param question the question being asked.
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
     * @param answer the correct answer for the question.
     */
    private void setAnswer(String answer){
        this.myAnswer = myAnswer;
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
     * @param answerSet the set of answers for the question
     */
    private void setAnswerSet(List<String> answerSet) {
        this.answerSet = answerSet;
    }
}
