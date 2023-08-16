package controller;

import model.Question;

import java.beans.PropertyChangeListener;

public interface TriviaMazeControls {


    /**
     * Resets the game to the default Maze grid.
     */
    void newGame();



    // Player movement update question to the door they just touched
    // if correct move character location to the new Room index
    //Player says go right... check for door... Unlocked... grab the question... user got question correct... move in room to the right

    /**
     * Try to move the player's character down.
     * If there is an Unlocked Door ask the question.
     * In there is a locked door do nothing.
     */
    void down();

    /**
     * Try to move the player's character up.
     * If there is an Unlocked Door ask the question.
     * In there is a locked door do nothing.
     */
    void up();

    /**
     * Try to move the player's character left.
     * If there is an Unlocked Door ask the question.
     * In there is a locked door do nothing.
     */
    void left();

    /**
     * Try to move the player's character right.
     * If there is an Unlocked Door ask the question.
     * In there is a locked door do nothing.
     */
    void right();

    void updateDoors();
    // if door is true set to false

    void lookUp();
    void lookDown();
    void lookLeft();
    void lookRight();


    void sendQuestion(Question q);

    /**
     * Adds a PropertyChangeListener to PropertyChangeSupport.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Adds a PropertyChangeListener with a Property name to PropertyChangeSupport.
     *
     * @param thePropertyName The name of the property that has been changed
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Removes a PropertyChangeListener from PropertyChangeSupport.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Removes a PropertyChangeListener with a Property name from PropertyChangeSupport.
     *
     * @param thePropertyName The name of the property that has been changed
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);




}
