package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.*;
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

    private Doors door;

    private Room room;


    /**
     * Constructor for MazePanel.
     *
     * @param maze The Maze object to be displayed.
     */
    public MazePanel(Maze maze) {
        super();
        this.maze = maze;
        this.player = maze.getPlayer();
        this.room = maze.getRoom(player.getPlayerLoc());
        this.door = room.getDoorByDirection(player.getPlayerDir());

        try {
            entranceImage = ImageIO.read(new File("start.png"));
            exitImage = ImageIO.read(new File("mirror.png"));
            floorImage = ImageIO.read(new File("floor.png"));
            flowerImage = ImageIO.read(new File("flower.png"));
            playerImage = ImageIO.read(new File("player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        addKeyListener(new BoardKeyListener());
//        setFocusable(true);
// requestFocus();

        setVisible(true);
    }

    public static Dimension getMazeSize() {
        return MAZE_SIZE;
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawMazeGridWithGraphics(g2d);

        drawPlayer(g2d);

        drawDoors(g2d);

        //repaint();


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
        repaint();
    }

    private void drawDoors(final Graphics2D theG2D) {
        // Prints the doors
        int width = 0;
        int height = 0;
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                Room currRoom = maze.getRoom(row, col);
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;

                for (Doors d : currRoom.getMapOfDoorsAndDir().keySet()) {
                    Direction dir = currRoom.getMapOfDoorsAndDir().get(d);

                    if (dir.equals(Direction.SOUTH)) {
                        int doorX = x + GRID_SIZE / 2 - 10;
                        int doorY = y + GRID_SIZE;
                        width = 20;
                        height = 10;

                        drawChangingDoors(theG2D, doorX, doorY, width, height, d);
                        //repaint();


                    } else if (dir.equals(Direction.EAST)) {
                        int doorX = x + GRID_SIZE;
                        int doorY = y + GRID_SIZE / 2 - 10;
                        width = 10;
                        height = 20;

                        drawChangingDoors(theG2D, doorX, doorY, width, height, d);
                        //repaint();

                    }

                }
            }

        }
    }

    private void drawChangingDoors(final Graphics2D theG2D, int theX, int theY, int theW, int theH, Doors d) {
        if (d.getDoorStatus()) {
            theG2D.setColor(Color.BLACK);
            theG2D.fillRect(theX, theY, theW, theH);
        } else {
            theG2D.setColor(Color.RED);
            theG2D.fillRect(theX, theY, theW, theH);
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("\nMaze PC is this being calledn\n\n");

        if (evt.getPropertyName().equals(maze.PROPERTY_LOCATION_CHANGE)) {
            System.out.println("\nMaze PC is this being calledn\n\n");

            Player newPlayer = (Player) evt.getNewValue();
            player = new Player(newPlayer.getPlayerLoc(), newPlayer.getPlayerDir());
            //repaint();
        }
        if (maze.PROPERTY_DOOR_STATUS.equals(evt.getPropertyName())) {
            Doors updatedDoor = (Doors) evt.getNewValue();
            door = updatedDoor;
            //repaint();
        }
    }
//    private class BoardKeyListener extends KeyAdapter {
//        public void keyPressed(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//            switch (keyCode) {
//                case KeyEvent.VK_UP:
//                    maze.up();
//                    System.out.println("up");
//                    break;
//                case KeyEvent.VK_DOWN:
//                    maze.down();
//                    System.out.println("down");
//                    break;
//                case KeyEvent.VK_LEFT:
//                    maze.left();
//                    System.out.println("left");
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    maze.right();
//                    System.out.println("right");
//                    break;
//            }
//            //repaint();
//        }
//    }
}

