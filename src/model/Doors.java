package model;

public class Doors {
    /**
     * ADD JAVADOC!!!
     */
    private final boolean myIsUnlocked;
    /**
     * ADD JAVADOC!!!
     */
    private Question myCurrQuestion;
    /**
     * ADD JAVADOC!!!
     */
    private final QuestionData myQuestionData;

    /**
     * ADD JAVADOC!!
     */
    public Doors() {
        this.myIsUnlocked = false;
        this.myCurrQuestion = null;
        this.myQuestionData = new QuestionData();
    }

    /**
     * ADD JAVADOC!!
     * @return
     */
    public boolean getDoorStatus() {

        return myIsUnlocked;
    }

    /**
     * ADD JAVADOC!!
     */
    public void setQuestionType() {
        // Retrieve a random question
        myCurrQuestion = myQuestionData.retrieveQuestion();
    }

    /**
     * ADD JAVADOC!!
     * @return
     */
    public Question getCurrQuestion() {
        return myCurrQuestion;
    }
}
