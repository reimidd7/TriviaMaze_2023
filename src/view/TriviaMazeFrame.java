package view;

import controller.TriviaMaze;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class create the frame for the Trivia Maze game to sit in.
 * Added is a file menu to assist the player.
 *
 * @author Reilly Middlebrooks, Kevin Than, Danie Oum
 * @version Summer 2023
 */
public class TriviaMazeFrame extends JFrame {
    /**
     * Default frame height.
     */
    private static final int FRAME_HEIGHT = 16 * 30; //480

    /**
     * Default frame width.
     */
    private static final int FRAME_WIDTH = 16 * 55; //880

    /**
     * An instance of the question display panel.
     */
    private static QuestionDisplayPanel questionPanel;

    /**
     * An instance of the maze display panel.
     */
    private static MazePanel mazePanel;

    /**
     * Trivia Maze object for gameplay.
     */
    private final TriviaMaze myMaze;


    /**
     * Constructor to create the Frame for Trivia Maze.
     * Uses JFrame as super.
     */
    public TriviaMazeFrame(final TriviaMaze theMaze) {
        super();
        myMaze = theMaze;

        createFrame();

        setVisible(true);
    }

    /**
     * A method to assist in the creation of the Frame.
     */
    private void createFrame() {
        setTitle("Team 13 - Trivia Maze");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(createFileMenu());
        setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the menu bar for the frame.
     *
     * @return the completed menu.
     */
    private JMenuBar createFileMenu() {
        final JMenuBar bar = new JMenuBar();
        final JMenu fileClick = new JMenu("File");

        final JMenu help = new JMenu("Help");
        final JMenuItem saveGame = new JMenuItem("Save Game");
        final String fileName = "gamestate.ser";

        saveGame.addActionListener(theE -> {
            try {
                save(myMaze, fileName);
                JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Saved Failed");
            }
        });

        // Create load game menu item and add ActionListener.
        final JMenuItem loadGame = new JMenuItem("Load Game");

        loadGame.addActionListener(e -> {
            //load game when clicked.
            try {
                final TriviaMaze loadedMaze = (TriviaMaze) load("gameState.ser");
                myMaze.copyStateFrom(loadedMaze);
                questionPanel.updateStateAfterLoadQuestion(loadedMaze);
                mazePanel.updateStateAfterLoadMaze(loadedMaze);
                JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Loaded!");
            } catch (final Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Error Loading");
            }
        });

        // Create exit game menu item and add ActionListener.
        final JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(theE -> exitGame());

        help.add(instructionSubMenu("Game Controls", "To move the player around the board "
                + "use the arrow keys on your keyboard."));
        help.add(instructionSubMenu("Goal", """
                The goal is to get your player to reach the Mirror.\s
                This is done by using the arrow keys to choose doorways, then answer the question.\s
                If correct you get to move ahead to the next Room.
                If incorrect the door locks and you must find a new way around"""));
        help.add(instructionSubMenu("About", "Trivia Maze 1.0 was created by Danie Oum, "
                + "Kevin Than, and Reilly Middelbrooks. \n We hope you enjoy!"));

        final JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(theE -> {
            myMaze.newGame();
            questionPanel.resetQuestionPanel();
            mazePanel.resetMazePanel();
            System.out.println("Player " + myMaze.getPlayer().getPlayerLoc() + " " + myMaze.getPlayer().getPlayerDir());
        });

        fileClick.add(newGame);
        fileClick.add(saveGame);
        fileClick.add(loadGame);
        fileClick.add(exitGame);
        bar.add(fileClick);
        bar.add(help);
        return bar;
    }

    private void exitGame() {
        final int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?", "Yes or No", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
            dispose();
            System.exit(0);
        }
    }
    private static void save(final Serializable theData, final String theFileName)
            throws Exception {
        try (ObjectOutputStream saveData =
                     new ObjectOutputStream(new FileOutputStream(theFileName))) {
            saveData.writeObject(theData);
        }
    }

    private static Object load(final String theFileName) throws Exception {
        try (ObjectInputStream loadData =
                     new ObjectInputStream(new FileInputStream(theFileName))) {
            return loadData.readObject();
        }
    }

    private JMenuItem instructionSubMenu(final String theText, final String theCommand) {
        final JMenuItem item = new JMenuItem(theText);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(TriviaMazeFrame.this, theCommand);
            }
        });
        return item;
    }

    /**
     * The creation of the full GUI.
     * Is called in the TriviaMazeAPP class.
     */
    public static void createAndShowGui() {
        // Create the Maze object here
        final TriviaMaze maze = new TriviaMaze();
        maze.newGame();

        final TriviaMazeFrame frame = new TriviaMazeFrame(maze);

        // Pass the Maze object to the MazePanel constructor
        mazePanel = new MazePanel(maze);
        maze.addPropertyChangeListener(mazePanel);


        questionPanel = new QuestionDisplayPanel(maze);
        maze.addPropertyChangeListener(questionPanel);

        final UserControlsPanel controlsPanel = new UserControlsPanel(maze);

        final JPanel eastInfo = new JPanel();
        eastInfo.setLayout(new GridLayout(2, 1, 0,  16));
        eastInfo.add(controlsPanel);
        eastInfo.add(questionPanel);
        frame.setLayout(new GridLayout(1, 2, 16, 0));
        frame.add(mazePanel);
        frame.add(eastInfo);

        frame.setVisible(true);
    }
}
