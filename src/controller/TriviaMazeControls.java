package controller;

import model.Question;

/**
 * Interface to store the controls needed for the Trivia Game.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public interface TriviaMazeControls {

    /**
     * Resets the game to the default Maze grid.
     */
    void newGame();

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

    /**
     * Updates the door status's.
     * If the user get the answer incorrect the door changes.
     */
    void updateDoors();

    /**
     * Sets the player direction NORTH,
     *      sends the question to the display panel.
     */
    void lookUp();

    /**
     * Sets the player direction SOUTH,
     *      sends the question to the display panel.
     */
    void lookDown();

    /**
     * Sets the player direction WEST,
     *      sends the question to the display panel.
     */
    void lookLeft();

    /**
     * Sets the player direction EAST,
     *      sends the question to the display panel.
     */
    void lookRight();

    /**
     * Sends a new question to the display panel.
     * @param theQ the current question.
     */
    void sendQuestion(Question theQ);

}
