package controller;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import model.Direction;
import model.Doors;
import model.Maze;
import model.Player;
import model.Question;
import model.Room;

/**
 * This class contains the control methods for the Maze game.
 * It is the fully functioning Maze using the creation methods
 *      from the Maze class in the model.
 *
 * @author Reilly Middlebrooks, Kevin Than, Danie Oum
 * @version Summer 2023
 */
public final class TriviaMaze extends Maze
        implements PropertyChangeEnabledTriviaMazeControls, Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;

    /**
     * The number of rows in the maze.
     */
    private static final int NUM_ROWS = 5;

    /**
     * The number of columns in the maze.
     */
    private static final int NUM_COLS = 5;

    /**
     * The number of rows in the maze.
     */
    private static final int EXIT_ROW = 4;

    /**
     * The number of columns in the maze.
     */
    private static final int EXIT_COL = 4;

    /**
     * Contains the player object for the game.
     * It Represents the user.
     */
    private Player myPlayer;

    /**
     * Contains the current question.
     */
    private Question myQues;

    /**
     * Manager for property change listeners.
     */
    private final PropertyChangeSupport myPcs;

    public TriviaMaze() {
        super(NUM_ROWS, NUM_COLS);
        myPcs = new PropertyChangeSupport(this);
    }

    @Override
    public void newGame() {
        createMaze();
        myPlayer = new Player(new Point(0, 0), Direction.NONE);
        setEntrance();
        setExit();

        // save old values
        final Point oldPlayerLoc = myPlayer.getPlayerLoc();

        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc,
                new Point(myPlayer.getPlayerLoc()));
    }

    @Override
    public void down() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        // checks if the room has a door to the south and if it's unlocked.
        final boolean checkForSouth = room.getDoorByDirection(Direction.SOUTH).getDoorStatus();

        if (playerLoc.x < getRows() - 1 && checkForSouth) {
            playerLoc.translate(1, 0);         //Translates location
            myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, getRoom(playerLoc));
            notifyObserversOfLocationChange();
            checkForWin();
        }
    }

    @Override
    public void lookDown() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        myPlayer.setPlayerDir(Direction.SOUTH);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void up() {
        final Point playerLoc = myPlayer.getPlayerLoc();

        if (playerLoc.x > 0) {
            playerLoc.translate(-1, 0);         //Translates location
            myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, getRoom(playerLoc));

            notifyObserversOfLocationChange();
            checkForWin();
        }
    }

    @Override
    public void lookUp() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        myPlayer.setPlayerDir(Direction.NORTH);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void left() {
        final Point playerLoc = myPlayer.getPlayerLoc();

        if (playerLoc.y > 0) {
            playerLoc.translate(0, -1);         //Translates location
            myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, getRoom(playerLoc));

            notifyObserversOfLocationChange();
            checkForWin();
        }
    }

    @Override
    public void lookLeft() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        myPlayer.setPlayerDir(Direction.WEST);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void right() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        // checks if the room has a door to the east and if it's unlocked.
        final boolean checkForEast = room.getDoorByDirection(Direction.EAST).getDoorStatus();

        if (playerLoc.y < getCols() - 1 && checkForEast) {
            playerLoc.translate(0, 1);         //Translates location
            myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, getRoom(playerLoc));

            notifyObserversOfLocationChange();
            checkForWin();
        }
    }
    @Override
    public void lookRight() {
        final Point playerLoc = myPlayer.getPlayerLoc();
        final Room room = getRoom(playerLoc);

        myPlayer.setPlayerDir(Direction.EAST);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void sendQuestion(final Question theQ) {
        myQues = theQ;
        myPcs.firePropertyChange(PROPERTY_NEW_QUESTION, null, theQ);
    }

    @Override
    public void updateDoors() {
        final Room room = getRoom(myPlayer.getPlayerLoc());
        final Doors door = room.getDoorByDirection(myPlayer.getPlayerDir());

        if (door.getDoorStatus()) {
            System.out.println("update door call");
            door.setLocked(false);
            myPcs.firePropertyChange(PROPERTY_DOOR_STATUS, null, door);
        }
    }



    public Doors getCurrentDoor() {
        final Room room = getRoom(myPlayer.getPlayerLoc());

        return room.getDoorByDirection(myPlayer.getPlayerDir());
    }

    // Makes the property change call easier to read.
    private void notifyObserversOfLocationChange() {
        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, null,
                myPlayer);
    }

    /**
     * Gets the player currently in use.
     *
     * @return the Player object.
     */
    public Player getPlayer() {
        return myPlayer;
    }

    /**
     * Gets the question currently in use.
     *
     * @return the Question object.
     */
    public Question getQuestion() {
        return myQues;
    }

    /**
     * Check for if the user.Player made it to the exit room.
     *
     * @return true if the Player has won, false if not.
     */
    public boolean checkForWin() {
        if (myPlayer.getPlayerLoc().equals(new Point(EXIT_ROW, EXIT_COL))) {
            System.out.println("Congratulations! You have reached the exit and won!");
            return true;
        } else {
            return false;
        }
    }


    /**
     * Copies the state from the saved game to the in motion game.
     * @param theLoadedMaze the saved game.
     */
    public void copyStateFrom(final TriviaMaze theLoadedMaze) {
        //this.myRooms = theLoadedMaze.myRooms;
        this.myPlayer = theLoadedMaze.myPlayer;
        this.myQues = theLoadedMaze.myQues;

    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
    }

}
