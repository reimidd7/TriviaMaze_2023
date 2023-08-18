package model;

/**
 * This is an Enum class that holds directions for the doors and players.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public enum Direction {
    /**
     * North (which is up on the screen).
     */
    NORTH('N'),

    /**
     * West (which is left on the screen).
     */
    WEST('W'),

    /**
     * South (which is down on the screen).
     */
    SOUTH('S'),

    /**
     * East (which is right on the screen).
     */
    EAST('E'),

    /**
     * None points nowhere is the default direction for the player.
     */
    NONE('O');

    /**
     * Letter of the Enum.
     */
    private final char myLetter;

    Direction(final char theLetter) {
        myLetter = theLetter;
    }

}
