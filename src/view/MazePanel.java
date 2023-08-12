package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

// DISPLAY THE MAZE
public class MazePanel extends JPanel implements PropertyChangeListener {
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
        this.player = maze.getPlayer();
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

        drawMazeGridWithGraphics(g2d);

        drawPlayer(g2d);

        drawDoors(g2d);

    }

    private void drawMazeGridWithGraphics(final Graphics2D theG2D) {
        // Creates the Maze grid. With entrance, exit, and floor images
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;
                Room room = maze.getRoom(row, col);
                if (room.isEntrance()) {
                    theG2D.drawImage(entranceImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else if (room.isExit()) {
                    theG2D.drawImage(exitImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else {
                    theG2D.drawImage(floorImage, x, y, GRID_SIZE, GRID_SIZE, this);
                }
                theG2D.setColor(Color.BLACK);
                theG2D.drawLine(x, y, x + GRID_SIZE, y);
                theG2D.drawLine(x, y + GRID_SIZE, x + GRID_SIZE, y + GRID_SIZE);
                theG2D.drawLine(x, y, x, y + GRID_SIZE);
                theG2D.drawLine(x + GRID_SIZE, y, x + GRID_SIZE, y + GRID_SIZE);
            }
        }
    }

    private void drawPlayer(final Graphics2D theG2D) {
        // Moves the player around the maze?
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;
                if (player.getPlayerLoc().x == row && player.getPlayerLoc().y == col) {
                    int playerX = x + GRID_SIZE / 4;
                    int playerY = y + GRID_SIZE / 4;
                    theG2D.drawImage(playerImage, playerX, playerY, GRID_SIZE / 2, GRID_SIZE / 2, this);
                }
            }
        }
    }

    private void drawDoors(final Graphics2D theG2D) {
        // Prints the doors
        int width = 0;
        int height = 0;
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                Room currRoom = maze.getRoom(row, col);
                Map<Doors, Direction> doorsDirectionMap = currRoom.getMapOfDoorsAndDir();
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;

                for (Doors door : currRoom.getMapOfDoorsAndDir().keySet()) {
                    Direction dir = currRoom.getMapOfDoorsAndDir().get(door);

                    if (dir.equals(Direction.SOUTH)) {
                        int doorX = x + GRID_SIZE / 2 - 10;
                        int doorY = y + GRID_SIZE;
                        width = 20;
                        height = 10;

                        if (door.getDoorStatus()) {
                            theG2D.setColor(Color.BLACK);
                            theG2D.fillRect(doorX, doorY, width, height);
                        } else {
                            theG2D.setColor(Color.RED);
                            theG2D.fillRect(doorX, doorY, width, height);
                        }

                    } else if (dir.equals(Direction.EAST)) {
                        int doorX = x + GRID_SIZE;
                        int doorY = y + GRID_SIZE / 2 - 10;
                        width = 10;
                        height = 20;
                        if (door.getDoorStatus()) {
                            theG2D.setColor(Color.BLACK);
                            theG2D.fillRect(doorX, doorY, width, height);
                        } else {
                            theG2D.setColor(Color.RED);
                            theG2D.fillRect(doorX, doorY, width, height);
                        }
                    }

                }

            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Maze.PROPERTY_LOCATION_CHANGE)) {
            Player newPlayerLocation = (Player) evt.getNewValue();
            player.setPlayerLoc(newPlayerLocation.getPlayerLoc());
            repaint();
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                maze.up();
                break;
            case KeyEvent.VK_DOWN:
                maze.down();
                break;
            case KeyEvent.VK_LEFT:
                maze.left();
                break;
            case KeyEvent.VK_RIGHT:
                maze.right();
                break;
        }
        repaint();
    }
}


