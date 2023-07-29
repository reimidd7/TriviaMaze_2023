package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * This class create the frame for the Trivia Maze game to sit in.
 * Added is a file menu to assist the player.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public class TriviaMazeFrame extends JFrame implements PropertyChangeListener {

    /**
     * Default frame height.
     */
    private static final int FRAME_HEIGHT = 16 * 30; //480
    /**
     * Default frame width.
     */
    private static final int FRAME_WIDTH = 16 * 55; //880


    /**
     * Constructor to create the Frame for Trivia Maze.
     * Uses JFrame as super.
     */
    public TriviaMazeFrame() {
        super();
        createFrame();
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

        final JMenu newGame = new JMenu("New Game");
        final JMenu saveGame = new JMenu("Save Game");
        final JMenu exitGame = new JMenu("Exit Game");
        final JMenu help = new JMenu("Help");


        help.add(instructionSubMenu("Game Controls", "This is Game Controls"));
        help.add(instructionSubMenu("Goal", "This is the goal"));
        help.add(instructionSubMenu("About", "Trivia Maze 1.0 was created by Danie Oum, "
                + "Kevin Than, and Reilly Middelbrooks. \n We hope you enjoy!"));


        bar.add(newGame);
        bar.add(saveGame);
        bar.add(exitGame);
        bar.add(help);

        return bar;
    }

    /**
     * not done! finding a way to implementing it correctly.
     */
    private static void saveSerial(Serializable data, String fileName) throws Exception {
        try
    }
    private void saveGame() {
        try (ObjectOutpututSteam outputStream = new ObjectOutputStream(new FileOutputSteam("dont know file path yet"))) {
            GameData gameData = getGameState();
            ObjectOutPutSteam state = new ObjectOutputStream(new FileOutputSteam());
            outputStream.writeObject(state);
            System.out.println("Game Data Saved!");
        } catch (Exception e) {
            ex.printStackTrace();
        }
    }

    private gameData getGameData implements
    void Serializable
    {

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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
