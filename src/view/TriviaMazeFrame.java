package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class TriviaMazeFrame extends JFrame implements PropertyChangeListener {

    private MazePanel gameState;

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
        final JMenu fileclick = new JMenu("File");
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenu help = new JMenu("Help");
        final JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    save(gameState, "gameState");
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Saved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Saved Failed");

                }
            }
        });

        final JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    load("gameState.dat");
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Game Loaded!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TriviaMazeFrame.this, "Error Loaded");
                }

            }
        });
        final JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                exitGame();
            }
        });


        help.add(instructionSubMenu("Game Controls", "This is Game Controls"));
        help.add(instructionSubMenu("Goal", "This is the goal"));
        help.add(instructionSubMenu("About", "Trivia Maze 1.0 was created by Danie Oum, "
                + "Kevin Than, and Reilly Middelbrooks. \n We hope you enjoy!"));
        fileclick.add(newGame);
        fileclick.add(saveGame);
        fileclick.add(loadGame);
        fileclick.add(exitGame);
        bar.add(fileclick);
        bar.add(help);
        return bar;
    }

    private void exitGame(){
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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
