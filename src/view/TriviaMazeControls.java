package view;

import model.Doors;
import model.Question;

import java.awt.Point;
import java.beans.PropertyChangeSupport;

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


   // Point getPlayerLocation();


    //void checkDoors();

    void updateDoors(Doors door);
    // if door is true set to false

    //Question newQuestion();





}
