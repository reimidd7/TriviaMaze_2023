package model;

public enum Direction {
    NONE('O'),
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
    EAST('E');

    /**
     * Letter of the Enum.
     */
    private final char myLetter;

    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    /**
     * Returns the Direction represented by the given letter.
     *
     * @param theLetter The letter.
     * @return the Direction represented by the given letter, or null if no
     *         Direction is represented by the given letter.
     */
    public static Direction valueOf(final char theLetter) {
        Direction result = null;

        for (final Direction direction : Direction.values()) {
            if (direction.letter() == theLetter) {
                result = direction;
            }
        }

        return result;
    }

    /**
     * Returns the letter corresponding to this direction.
     *
     * @return the letter corresponding to this direction.
     */
    public char letter() {
        return myLetter;
    }
}
