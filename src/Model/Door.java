package Model;

public interface Door {
    //is door locked
    //get question
    //randomly assign question?

    /**
     * This method sets the door status as False = locked and True = unlocked.
     * @return false when door is locked and true when unlocked.
     */
    boolean getDoorStatus();

    /**
     * Randomly selects the type of question that will be attached to the Door.
     */
     void setQuestion();

    /**
     * Gets the current Question that was set.
     * @return the current question.
     */
    Question getCurrQuestion();


}
