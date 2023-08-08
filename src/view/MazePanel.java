package view;

import model.Doors;
import model.Maze;
import model.Player;
import model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

// DISPLAY THE MAZE
public class MazePanel extends JPanel {
    private static final int ROWS = 5;
    private static final int COLS = 5;

    private static final Dimension MAZE_SIZE = new Dimension(ROWS * 50, COLS * 50);

    private static final int GRID_SIZE = 85;

    private Maze maze; // The Maze object to be displayed

    private Player player;
    private BufferedImage entranceImage;
    private BufferedImage exitImage;
    private BufferedImage floorImage;
    private BufferedImage flowerImage;
    private BufferedImage playerImage;

    /**
     * Constructor for MazePanel.
     *
     * @param maze The Maze object to be displayed.
     */
    public MazePanel(Maze maze) {
        this.maze = maze;
        this.player = new Player(0, 0);
        try {
            entranceImage = ImageIO.read(new File("start.png"));
            exitImage = ImageIO.read(new File("mirror.png"));
            floorImage = ImageIO.read(new File("floor.png"));
            flowerImage = ImageIO.read(new File("flower.png"));
            playerImage = ImageIO.read(new File("player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public static Dimension getMazeSize() {
        return MAZE_SIZE;
    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D g2d = (Graphics2D) theGraphics;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;
                Room room = maze.getRoom(row, col);
                if (room.isEntrance()) {
                    g2d.drawImage(entranceImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else if (room.isExit()) {
                    g2d.drawImage(exitImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else {
                    g2d.drawImage(floorImage, x, y, GRID_SIZE, GRID_SIZE, this);
                }

                if (player.getCurrentRow() == row && player.getCurrentCol() == col) {
                    int playerX = x + GRID_SIZE / 4;
                    int playerY = y + GRID_SIZE / 4;
                    g2d.drawImage(playerImage, playerX, playerY, GRID_SIZE / 2, GRID_SIZE / 2, this);
                }
//                List<Doors> doors = room.getListOfDoors();
//                for (Doors door : doors) {
//                    int doorId = door.getDoorId();
//                    int doorX = x;
//                    int doorY = y;
//
//                    if (doorId == 1) {
//                        doorX += GRID_SIZE / 2;
//                    } else if (doorId == 2) {
//                        doorX += GRID_SIZE;
//                        doorY += GRID_SIZE / 2;
//                    } else if (doorId == 3) {
//                        doorX += GRID_SIZE / 2;
//                        doorY += GRID_SIZE;
//                    } else if (doorId == 4) {
//                        doorY += GRID_SIZE / 2;
//                    }

                   // g2d.drawImage(flowerImage, doorX, doorY, GRID_SIZE / 2, GRID_SIZE / 2, this);
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(x, y, x + GRID_SIZE, y);
                    g2d.drawLine(x, y + GRID_SIZE, x + GRID_SIZE, y + GRID_SIZE);
                    g2d.drawLine(x, y, x, y + GRID_SIZE);
                    g2d.drawLine(x + GRID_SIZE, y, x + GRID_SIZE, y + GRID_SIZE);
                }
            }
        }
    }
}

