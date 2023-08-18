package view;

import controller.TriviaMaze;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Direction;
import model.Doors;
import model.Player;
import model.Room;


/**
 * Displays and updates the maze visual.
 * Moves the player, draws the Maze, updates door statuses.
 *
 * @author Kevin Than, Reilly Middlebrooks
 * @version Summer 2023
 */
public class MazePanel extends JPanel implements PropertyChangeListener {
    /**
     * Amount of rows in the maze.
     */
    private static final int ROWS = 5;

    /**
     * Amount of columns in the maze.
     */
    private static final int COLS = 5;

    /**
     * Dimension of the grid for the Maze.
     */
    private static final Dimension MAZE_SIZE = new Dimension(ROWS * 50, COLS * 50);

    /**
     * The size of each individual grid square.
     */
    private static final int GRID_SIZE = 85;

    /**
     * The maze object to be displayed.
     */
    private TriviaMaze myMaze;

    /**
     * The player object to activate movement.
     */
    private Player myPlayer;

    /**
     * Image for the Entrance.
     */
    private BufferedImage myEntranceImage;

    /**
     * Image for the Exit.
     */
    private BufferedImage myExitImage;

    /**
     * Image for the Floor.
     */
    private BufferedImage myFloorImage;


    /**
     * Image for the Player.
     */
    private BufferedImage myPlayerImage;

    /**
     * Current door.
     */
    private Doors myDoor;

    /**
     * Current room the player is in.
     */
    private Room myRoom;


    /**
     * Constructor for MazePanel.
     *
     * @param theMaze The Maze object to be displayed.
     */
    public MazePanel(final TriviaMaze theMaze) {
        super();
        this.myMaze = theMaze;
        this.myPlayer = theMaze.getPlayer();
        this.myRoom = theMaze.getRoom(myPlayer.getPlayerLoc());
        this.myDoor = myRoom.getDoorByDirection(myPlayer.getPlayerDir());

        try {
            myEntranceImage = ImageIO.read(new File("start.png"));
            myExitImage = ImageIO.read(new File("mirror.png"));
            myFloorImage = ImageIO.read(new File("floor.png"));
            myPlayerImage = ImageIO.read(new File("player.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myMaze.checkForWin()) {
            showExitPopup();
        } else {
            drawMazeGridWithGraphics(g2d);

            drawPlayer(g2d);

            drawDoors(g2d);
        }
    }

    private void showExitPopup() {
        final String[] options = {"Exit", "New Game"};
        SwingUtilities.invokeLater(() -> {
            final int choice = JOptionPane.showOptionDialog(this,
                    "CONGRATS YOU WON!!", "Won Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                removeAll();
                myMaze.newGame();
                resetMazePanel();
                repaint();
                revalidate();
            }
        });

    }

    private void drawMazeGridWithGraphics(final Graphics2D theG2D) {
        // Creates the Maze grid. With entrance, exit, and floor images
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                final int x = col * GRID_SIZE;
                final int y = row * GRID_SIZE;
                final Room room = myMaze.getRoom(row, col);
                if (room.isEntrance()) {
                    theG2D.drawImage(myEntranceImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else if (room.isExit()) {
                    theG2D.drawImage(myExitImage, x, y, GRID_SIZE, GRID_SIZE, this);
                } else {
                    theG2D.drawImage(myFloorImage, x, y, GRID_SIZE, GRID_SIZE, this);
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
                final int x = col * GRID_SIZE;
                final int y = row * GRID_SIZE;
                if (myPlayer.getPlayerLoc().x == row && myPlayer.getPlayerLoc().y == col) {
                    final int playerX = x + GRID_SIZE / 4;
                    final int playerY = y + GRID_SIZE / 4;
                    theG2D.drawImage(myPlayerImage, playerX, playerY, GRID_SIZE / 2,
                            GRID_SIZE / 2, this);
                }
            }
        }
        repaint();
    }

    private void drawDoors(final Graphics2D theG2D) {
        // Prints the doors
        int width = 0;
        int height = 0;
        for (int row = 0; row < myMaze.getRows(); row++) {
            for (int col = 0; col < myMaze.getCols(); col++) {
                final Room currRoom = myMaze.getRoom(row, col);
                final int x = col * GRID_SIZE;
                final int y = row * GRID_SIZE;

                for (Doors d : currRoom.getMapOfDoorsAndDir().keySet()) {
                    final Direction dir = currRoom.getMapOfDoorsAndDir().get(d);

                    if (dir.equals(Direction.SOUTH)) {
                        final int doorX = x + GRID_SIZE / 2 - 10;
                        final int doorY = y + GRID_SIZE;
                        width = 20;
                        height = 10;

                        drawChangingDoors(theG2D, doorX, doorY, width, height, d);

                    } else if (dir.equals(Direction.EAST)) {
                        final int doorX = x + GRID_SIZE;
                        final int doorY = y + GRID_SIZE / 2 - 10;
                        width = 10;
                        height = 20;

                        drawChangingDoors(theG2D, doorX, doorY, width, height, d);
                    }
                }
            }
        }
    }

    private void drawChangingDoors(final Graphics2D theG2D, final int theX, final int theY,
                                   final int theW, final int theH, final Doors theDoor) {
        if (theDoor.getDoorStatus()) {
            theG2D.setColor(Color.BLACK);
            theG2D.fillRect(theX, theY, theW, theH);
        } else {
            theG2D.setColor(Color.RED);
            theG2D.fillRect(theX, theY, theW, theH);
        }
    }

    /**
     * Resets the Panel for new game.
     */
    public void resetMazePanel() {
        myPlayer = myMaze.getPlayer();
        myRoom = myMaze.getRoom(myPlayer.getPlayerLoc());
        myDoor = myRoom.getDoorByDirection(myPlayer.getPlayerDir());

        repaint();
    }

    /**
     * Updates the state of the panel after reload.
     *
     * @param theLoadedMaze the saved game.
     */
    public void updateStateAfterLoadMaze(final TriviaMaze theLoadedMaze) {
        removeAll();
        this.myMaze = theLoadedMaze;
        this.myPlayer = myMaze.getPlayer();
        this.myRoom = myMaze.getRoom(myPlayer.getPlayerLoc());
        this.myDoor = myRoom.getDoorByDirection(myPlayer.getPlayerDir());
        // Load images and other initializations here
        revalidate();
        repaint();
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

        if (theEvt.getPropertyName().equals(myMaze.PROPERTY_LOCATION_CHANGE)) {
            final Player newPlayer = (Player) theEvt.getNewValue();
            myPlayer = new Player(newPlayer.getPlayerLoc(), newPlayer.getPlayerDir());
        }
        if (myMaze.PROPERTY_DOOR_STATUS.equals(theEvt.getPropertyName())) {
            myDoor = (Doors) theEvt.getNewValue();
        }
    }
}

