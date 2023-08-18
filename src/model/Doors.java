package model;

import java.io.Serializable;

/**
 * The door class is for the doors used in trivial maze.
 * each door has status and holds a question.
 *
 * @author Kevin Than
 * @author Reilly Middlebrooks, Danie Oum
 * @version Summer 2023
 */
public class Doors implements Serializable {
    /**
     * Unlock status of the doors.
     * false means locked.
     * true means unlocked.
     */
    private boolean myIsUnlocked;
    /**
     * The current question displayed on the door.
     */
    private Question myCurrQuestion;
    /**
     * Used to retrieve question data for the doors.
     */
    private final QuestionData myQuestionData = new QuestionData();

    /**
     * The specific door ID. This ID prevents duplication of doors to rooms.
     */
    private final int myDoorId;


    /**
     * Constructor for doors class. Unlocked and question by default.
     *
     * @param theDoorId the specific id to label the doors (prevents duplication).
     */
    public Doors(final int theDoorId) {
        //is the door unlocked?
        this.myIsUnlocked = true; //by default

        // adds the question data to the variable myCurrQuestion
        this.myCurrQuestion = myQuestionData.retrieveQuestion();

        this.myDoorId = theDoorId;
    }

    /**
     * Gets the specific Doors' ID.
     * @return the door ID.
     */
    public int getDoorId() {
        return myDoorId;
    }

    /**
     * Get the status of doors.
     * @return true if unlocked or false is locked.
     */
    public boolean getDoorStatus() {
        return myIsUnlocked;
    }

    /**
     * Sets if the door is locked or unlocked.
     *
     * @param theLocked true if locked, false if unlocked.
     */
    public void setLocked(final boolean theLocked) {
        myIsUnlocked = theLocked;
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
