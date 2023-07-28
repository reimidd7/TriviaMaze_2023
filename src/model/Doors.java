package model;

/**
 * The door class is for the doors used in trivial maze.
 * each door has status and holds a question.
 */
public class Doors {
    /**
     * Unlock status of the doors.
     * false mean locked.
     * true mean unlocked.
     */
    private final boolean myIsUnlocked;
    /**
     * The current question displayed on the door.
     */
    private Question myCurrQuestion;
    /**
     * Used to retrieve question for the doors.
     */
    private final QuestionData myQuestionData;

    /**
     * Constructor for doors class.
     * locked and no question by default.
     */
    public Doors() {
        this.myIsUnlocked = false;
        this.myCurrQuestion = null;
        this.myQuestionData = new QuestionData();
    }

    /**
     * Get the status of doors.
     * @return true if unlocked or false is locked.
     */
    public boolean getDoorStatus() {

        return myIsUnlocked;
    }

    /**
     * set question type by retrieving random question from database.
     * then it will become current question on the door.
     */
    public void setQuestionType() {
        // Retrieve a random question
        myCurrQuestion = myQuestionData.retrieveQuestion();
    }

    /**
     * Get the current question displayed on the doors.
     * @return current question.
     */
    public Question getCurrQuestion() {
        return myCurrQuestion;
    }
}
