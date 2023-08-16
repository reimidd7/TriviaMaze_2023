package view;

import controller.TriviaMaze;
import model.Doors;
import model.Maze;
import model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

/**
 * This class create the frame for the Trivia Maze game to sit in.
 * Added is a file menu to assist the player.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public class TriviaMazeFrame extends JFrame {

    //private MazePanel gameState;

    /**
     * Default frame height.
     */
    private static final int FRAME_HEIGHT = 16 * 30; //480
    /**
     * Default frame width.
     */
    private static final int FRAME_WIDTH = 16 * 55; //880

    private final TriviaMaze myMaze;

    private Question myQuestion;


    /**
     * Constructor to create the Frame for Trivia Maze.
     * Uses JFrame as super.
     */
    public TriviaMazeFrame(TriviaMaze theMaze) {
        super();
        myMaze = theMaze;
        myQuestion = myMaze.getQuestion();
//        addKeyListener(new BoardKeyListener());
//        setFocusable(true);
//        requestFocus();
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
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenu help = new JMenu("Help");
        final JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //save(gameState, "gameState");
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Saved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Saved Failed");

                }
            }
        });
        // Create load game menu item and add ActionListener.
        final JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //load game when clicked.
                try {
                    load("gameState.dat");
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Loaded!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Error Loaded");
                }

            }
        });
        // Create exit game menu item and add ActionListener.
        final JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                exitGame();
            }
        });


        help.add(instructionSubMenu("Game Controls", "To move the player around the board "
                + "use the arrow keys on your keyboard."));
        help.add(instructionSubMenu("Goal", "The goal is to get your player to reach the Mirror. "
                + "\nThis is done by using the arrow keys to choose doorways, "
                + "then answer the question. \nIf correct you get to move ahead to the next Room."
                + "\nIf incorrect the door locks and you must find a new way around"));
        help.add(instructionSubMenu("About", "Trivia Maze 1.0 was created by Danie Oum, "
                + "Kevin Than, and Reilly Middelbrooks. \n We hope you enjoy!"));
        fileClick.add(newGame);
        fileClick.add(saveGame);
        fileClick.add(loadGame);
        fileClick.add(exitGame);
        bar.add(fileClick);
        bar.add(help);
        return bar;
    }

    private void exitGame() {
        int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?","Yes or No", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_NO_OPTION) {
            dispose();
            System.exit(0);
        }

    }
    private static void save(Serializable data, String fileName) throws Exception {
        try (ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(fileName))) {
            saveData.writeObject(data);
        }
    }

    private static Object load(String fileName) throws Exception {
        try (ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(fileName))) {
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

    public static void createAndShowGui() {
        // Create the Maze object here
        TriviaMaze maze = new TriviaMaze();
        maze.newGame();

        final TriviaMazeFrame frame = new TriviaMazeFrame(maze);
        //maze.addPropertyChangeListener(frame);



        // Pass the Maze object to the MazePanel constructor
        final MazePanel mazePanel = new MazePanel(maze);
        maze.addPropertyChangeListener(mazePanel);


        final UserControlsPanel controlsPanel = new UserControlsPanel();

        QuestionDisplayPanel questionPanel = new QuestionDisplayPanel(maze);
        maze.addPropertyChangeListener(questionPanel);

        final JPanel eastInfo = new JPanel();
        eastInfo.setLayout(new GridLayout(2,1, 0, 16));
        eastInfo.add(controlsPanel);
        eastInfo.add(questionPanel);
        frame.setLayout(new GridLayout(1,2,16,0));
        frame.add(mazePanel);
        frame.add(eastInfo);

        frame.setVisible(true);

    }

}
