package view;

import javax.swing.SwingUtilities;

public final class TriviaMazeApp {
    private TriviaMazeApp() { }

    public static void main(final String[] theArgs) {
        SwingUtilities.invokeLater(TriviaMazeFrame:: createAndShowGui);
    }
}
