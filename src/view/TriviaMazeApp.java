package view;

import javax.swing.SwingUtilities;

/**
 * An application class to run the Trivia Maze Program.
 *
 * @author Reilly Middlebrooks, Kevin Than, Danie Oum
 * @version Summer 2023
 */
public final class TriviaMazeApp {
    private TriviaMazeApp() { }

    public static void main(final String[] theArgs) {
        SwingUtilities.invokeLater(TriviaMazeFrame:: createAndShowGui);
    }
}
