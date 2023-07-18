package View;

import javax.swing.*;

/**
 * This class create the frame for the Trivia Maze game to sit in.
 *  Added is a file menu to assist the player.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public class TriviaMazeFrame extends JFrame {

    /**
     * Default frame height.
     */
    private static final int FRAME_HEIGHT = 600;
    /**
     * Default frame width.
     */
    private static final int FRAME_WIDTH = 400;



    /*** FOR TESTING ONLY ***/
    public static void main (String [] args) {
        TriviaMazeFrame frame = new TriviaMazeFrame();
        frame.setVisible(true);

    }
    /***---------------***/
    /**\
     * Constructor to create the Frame for Trivia Maze.
     *  Uses JFrame as super.
     */
    public TriviaMazeFrame() {
        super();
        createFrame();
    }

    /**
     * A method to assist in the creation of the Frame.
     */
    private void createFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Team 13 - Trivia Maze");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(createFileMenu());
    }

    /**
     * Creates the menu bar for the frame.
     * @return the completed menu.
     */
    private static JMenuBar createFileMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu newGame = new JMenu("New Game");
        JMenu saveGame = new JMenu("Save Game");
        JMenu exitGame = new JMenu("Exit Game");
        JMenu instructions = new JMenu("Instructions");

        bar.add(newGame);
        bar.add(saveGame);
        bar.add(exitGame);
        bar.add(instructions);

        /***THIS IS WHERE THE ACTION LISTENERS AND DESCRIPTIONS WILL GO***/

        return bar;
    }
}
