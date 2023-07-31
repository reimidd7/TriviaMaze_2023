package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

//DISPLAY THE MAZE
//ADD DOORS LOCKED AND UNLOCKED (THIS WILL BE UPDATED)
//ADD A CHARACTER?
//ADD A WAY TO MARK THE USERS PATH
//NEED TO CHANGER IMPLEMENTS Serializable to the data storage.
public class MazePanel extends JPanel  implements Serializable {
    private static final int ROWS = 5;
    private static final int COLS = 5;

    private static final Dimension MAZE_SIZE = new Dimension(ROWS * 50, COLS * 50);

    private static final int GRID_SIZE = 85;




    /**
     * ADD JAVA DOC!
     */
    public MazePanel() {
        //size is set by default layout in TriviaMazeGUI
        super();
        setBackground(Color.MAGENTA);
        setVisible(true);
    }
//    public void createMazeGrid() {
//        JPanel grid = new JPanel();
//        grid.setPreferredSize(MAZE_SIZE);
//        grid.setLocation(40,40);
//        grid.paintComponents();
//    }

    public static Dimension getMazeSize() {
        return MAZE_SIZE;
    }



    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setPaint(Color.BLACK);

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                g2d.drawRect(col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }
    }
}
