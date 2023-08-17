package model;

import java.awt.Point;

/**
 * Initilizes the Player object. Represents the user.
 *
 * @author Reilly Middelbrooks, Kevin Than
 * @version Summer 2023
 */
public class Player {

    /**
     * Point of player's location in the Maze.
     */
    private final Point myPlayerLoc;

    /**
     * Direction the player is facing.
     */
    private Direction myPlayerDir;

    public Player(final Point theLoc, final Direction theDir) {
        this.myPlayerLoc = theLoc;
        this.myPlayerDir = theDir;
    }

    public Point getPlayerLoc() {
        return myPlayerLoc;
    }

    public Direction getPlayerDir() {
        return myPlayerDir;
    }

    public void setPlayerDir(final Direction theDirection) {
        myPlayerDir = theDirection;
    }
}
