package view;

import model.Doors;
import model.Maze;
import model.Room;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

// DISPLAY THE MAZE
public class MazePanel extends JPanel {
    private static final int ROWS = 5;
    private static final int COLS = 5;

    private static final Dimension MAZE_SIZE = new Dimension(ROWS * 50, COLS * 50);

    private static final int GRID_SIZE = 85;

    private Maze maze; // The Maze object to be displayed

    /**
     * Constructor for MazePanel.
     *
     * @param maze The Maze object to be displayed.
     */
    public MazePanel(Maze maze) {
        this.maze = maze;
        setBackground(Color.MAGENTA);
        setVisible(true);
    }

    public static Dimension getMazeSize() {
        return MAZE_SIZE;
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setPaint(Color.BLACK);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;
                Room room = maze.getRoom(row, col);

                // Draw room rectangle
                if (room.isEntrance()) {
                    g2d.setPaint(Color.GREEN);
                } else if (room.isExit()) {
                    g2d.setPaint(Color.RED);
                } else {
                    g2d.setPaint(Color.WHITE);
                }
                g2d.fillRect(x, y, GRID_SIZE, GRID_SIZE);

                g2d.setPaint(Color.BLACK);
                List<Doors> doors = (List<Doors>) room.getMapOfDoorsAndDir().keySet(); //TODO: This needs to be refactored now that the Doors are stored in a Map<Doors, Directions>
                for (Doors door : doors) {
                    int doorId = door.getDoorId();
                    int doorX = x;
                    int doorY = y;

                    if (doorId == 1) {
                        doorX += GRID_SIZE / 2;
                    } else if (doorId == 2) {
                        doorX += GRID_SIZE;
                        doorY += GRID_SIZE / 2;
                    } else if (doorId == 3) {
                        doorX += GRID_SIZE / 2;
                        doorY += GRID_SIZE;
                    } else if (doorId == 4) {
                        doorY += GRID_SIZE / 2;
                    }

                    g2d.fillRect(doorX, doorY, GRID_SIZE / 4, GRID_SIZE / 4);
                }
            }
        }
    }
}

