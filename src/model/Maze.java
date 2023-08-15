package model;

import view.PropertyChangeEnabledTriviaMazeControls;
import view.QuestionDisplayPanel;
import view.TriviaMazeControls;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to organize the rooms and doors and create the maze functional.
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class Maze implements PropertyChangeEnabledTriviaMazeControls {
    /**
     * The total amount of doors needed for our 5x5 maze.
     */
    private static final int AMT_OF_DOORS = 40;
    /**
     * Tha amount of rows that contain doors.
     * 4 so there is no doors on the outside of the maze.
     */
    private static final int ROWS_OF_DOORS = 4;

    /**
     * The 2d array that stores the rooms and doors of the Maze.
     */
    private final Room[][] myRooms;
    /**
     * The amount of rows needed for the Maze.
     */
    private final int myRows;
    /**
     * The amount of columns needed for the Maze.
     */
    private final int myCols;

    private Player myPlayer;

    private Doors myDoor;

    private Room room;
    private QuestionDisplayPanel myQuestionDisplayPanel;
    private final PropertyChangeSupport myPcs;


    /**
     * This method initializes the rows and columns of the maze and calls other methods.
     *
     * @param theRows The rows of the maze
     * @param theCols The columns of the maze
     */
    public Maze(final int theRows, final int theCols) {
        super();
        myRows = theRows;
        myCols = theCols;
        myRooms = new Room[theRows][theCols];
        myPcs = new PropertyChangeSupport(this);
    }

    @Override
    public void newGame() {

        this.createMaze();
        this.myPlayer = new Player(new Point(0, 0), Direction.NONE);
        this.room = this.getRoom(this.myPlayer.getPlayerLoc());
        this.myDoor = null;
        this.setEntrance();
        this.setExit();

        // Removed old player location and door since we are starting a new game
        this.myPcs.firePropertyChange("PLAYER_CHANGE_LOCATION", null, this.myPlayer.getPlayerLoc());
        this.myPcs.firePropertyChange("DOOR_STATUS_CHANGE", null, this.myDoor);
    }

    @Override
    public void down() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        if (myPlayerLoc.x < getRows() - 1) {
            if (isDoorUnlockedByDirection(myPlayerLoc, Direction.SOUTH)) {
                myPlayerLoc.translate(1, 0);
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("The door is locked! You can't go down.");
            }
        }
    }

    @Override
    public void up() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        if (myPlayerLoc.x > 0) {
            if (isDoorUnlockedByDirection(myPlayerLoc, Direction.NORTH)) {
                myPlayerLoc.translate(-1, 0);
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("The door is locked! You can't go up.");
            }
        }
    }

    @Override
    public void left() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        if (myPlayerLoc.y > 0) {
            if (isDoorUnlockedByDirection(myPlayerLoc, Direction.WEST)) {
                myPlayerLoc.translate(0, -1);
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("The door is locked! You can't go left.");
            }
        }
    }

    @Override
    public void right() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        if (myPlayerLoc.y < getCols() - 1) {
            if (isDoorUnlockedByDirection(myPlayerLoc, Direction.EAST)) {
                myPlayerLoc.translate(0, 1);
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("The door is locked! You can't go right.");
            }
        }
    }

    @Override
    public Point getPlayerLocation() {
        return new Point(myPlayer.getPlayerLoc());
    }

    @Override
    public void checkDoors() { //TODO: IDK if we need this. Maybe as a private method?
        Room playerRoom = getRoom(myPlayer.getPlayerLoc());
        Map<Doors, Direction> map = playerRoom.getMapOfDoorsAndDir();
        playerRoom.hasUnlockedDoors();

    }

    @Override
    public void updateDoors(Doors door) { //TODO: Change status us the user gets the Q wrong
        Doors oldDoor = getDoor();
        //CHANGE DOOR STATE?

        if (door.getDoorStatus()) {
            door.setLocked(false);
            myPcs.firePropertyChange(PROPERTY_DOOR_STATUS, oldDoor, door);
        }
    }

    private void notifyObseversOfLocationChange() {

        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, null,
                new Player(myPlayer.getPlayerLoc(), myPlayer.getPlayerDir()));

    }


    public Direction getPlayerDirection() {
        return myPlayer.getPlayerDir();
    }

    public Player getPlayer() {
        return myPlayer;
    }

    //TODO IDK this can be used in the checkFor... variable....I think
    public boolean isDoorUnlockedByDirection(Point thePlayerLoc, Direction theDir) {
        Room currRoom = getRoom(thePlayerLoc); //room where the player is located
        Doors currDoor = null;
        for (Doors d : currRoom.getMapOfDoorsAndDir().keySet()) {
            Direction dirOfCurrDoor = currRoom.getMapOfDoorsAndDir().get(d);
            if (dirOfCurrDoor.equals(theDir)) {
                currDoor = d;
            }
        }
        if (currDoor.getDoorStatus()) {
            return true;
        } else {
            return false;
        }
    }
    // --------------------------------^^ NEW stuff

    /**
     * Gets the Room at the given index.
     *
     * @param theRow The row of the maze
     * @param theCol The column of the maze
     * @return Returns the room
     */
    public Room getRoom(final int theRow, final int theCol) {
        if (theRow >= 0 && theRow < myRows && theCol >= 0 && theCol < myCols) {
            return myRooms[theRow][theCol];
        }
        return null;
    }

    public Room getRoom(Point thePlayerLoc) {
        int row = thePlayerLoc.x;
        int col = thePlayerLoc.y;
        if (row >= 0 && row < myRows && col >= 0 && col < myCols) {
            return myRooms[row][col];
        }
        return null;
    }

    /**
     * This method is the getter for the entrance of the maze.
     *
     * @return Returns the room of the entrance
     */
    public Room getEntrance() {
        for (int row = 0; row < myRows; row++) {
            for (int column = 0; column < myCols; column++) {
                if (myRooms[row][column].isEntrance()) {
                    return myRooms[row][column];
                }
            }
        }
        return null;
    }

    /**
     * This method is the getter for the exit of the maze.
     *
     * @return Returns a room
     */
    public Room getExit() {
        for (int row = 0; row < myRows; row++) {
            for (int column = 0; column < myCols; column++) {
                if (myRooms[row][column].isExit()) {
                    return myRooms[row][column];
                }
            }
        }
        return null;
    }

    /**
     * Gets the number of rows in the maze.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return myRows;
    }

    /**
     * Gets the number of columns in the maze.
     *
     * @return The number of columns.
     */
    public int getCols() {
        return myCols;
    }

    /**
     * Gets a list of all doors in the maze.
     *
     * @return A list of doors.
     */
    public Doors getDoor() {
        Room room = getRoom(myPlayer.getPlayerLoc());
        Doors door = room.getDoorByDirection(myPlayer.getPlayerDir());

        return door;
    }

    /*
     * This method creates the maze using a list of Doors and Room.
     * Connects the Doors to two Rooms with no duplication.
     */
    private void createMaze() {
        final List<Doors> currDoors = createAllDoors();
        final List<Room> currRooms = createAllRooms();
        int roomId = 0;

        for (int r = 0; r < myRows; r++) {
            for (int c = 0; c < myCols; c++) {
                addRoom(r, c, currRooms.get(roomId));
                roomId++;
            }
        }
        // Adding doors to the rooms
        connectRoomsToDoors(currDoors);
    }

    // Adds the doors to the vertical and horizontal Rooms.
    private void connectRoomsToDoors(final List<Doors> theCurrDoors) {
        int doorId = 0;

        // Add doors to the vertical rooms
        for (int row1 = 0, row2 = 0; row1 <= ROWS_OF_DOORS
                && row2 <= ROWS_OF_DOORS; row1++, row2++) {
            for (int col1 = 0, col2 = 1; col1 <= ROWS_OF_DOORS
                    && col2 <= ROWS_OF_DOORS; col1++, col2++) {
                connectRooms(row1, col1, row2, col2, theCurrDoors.get(doorId));
                doorId++;
            }
        }

        // Add doors to the horizontal rooms
        for (int col1 = 0, col2 = 0; col1 <= ROWS_OF_DOORS
                && col2 <= ROWS_OF_DOORS; col1++, col2++) {
            for (int row1 = 0, row2 = 1; row1 <= ROWS_OF_DOORS
                    && row2 <= ROWS_OF_DOORS; row1++, row2++) {
                connectRooms(row1, col1, row2, col2, theCurrDoors.get(doorId));
                doorId++;
            }
        }
    }

    //This method creates all the doors of the maze.
    private List<Doors> createAllDoors() {
        final int amtOfDoors = AMT_OF_DOORS;
        final List<Doors> allDoors = new ArrayList<>(amtOfDoors);

        for (int i = 1; i <= amtOfDoors; i++) {
            allDoors.add(new Doors(i));
        }

        return allDoors;
    }

    // This method creates all the rooms of the maze.
    private List<Room> createAllRooms() {
        final int amtOfRooms = myRows * myCols;
        final List<Room> allRooms = new ArrayList<>(amtOfRooms);

        for (int i = 1; i <= amtOfRooms; i++) {
            allRooms.add(new Room(i, "Room" + i));
        }

        return allRooms;
    }

    // Adds a Room to the given row and column in the Maze.
    private void addRoom(final int theRow, final int theCol, final Room theRoom) {
        myRooms[theRow][theCol] = theRoom;
    }


    // Connects the Doors to the Rooms.
    private void connectRooms(final int row1, final int col1,
                              final int row2, final int col2, final Doors theDoor) {
        final Room room1 = getRoom(row1, col1);
        final Room room2 = getRoom(row2, col2);
        if (room1 != null && room2 != null) {
            if (row1 == row2) {
                room1.addDoor(theDoor, Direction.EAST);
                room2.addDoor(theDoor, Direction.WEST);
            }
            if (col1 == col2) {
                room1.addDoor(theDoor, Direction.SOUTH);
                room2.addDoor(theDoor, Direction.NORTH);
            }
        }
    }

    // This method sets the entrance of the maze.
    private void setEntrance() {
        myRooms[0][0].setEntrance(true);
    }

    // This method sets the exit of the maze.
    private void setExit() {
        this.myRooms[this.myRows - 1][this.myCols - 1].setExit(true);
    }

    private void checkForWin() {
        Room currentRoom = getRoom(myPlayer.getPlayerLoc());
        if (currentRoom != null && currentRoom.isExit()) {
            System.out.println("Congratulations! You have reached the exit and won!");
        }
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener theListener) {

    }

    @Override
    public void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener theListener) {

    }

    @Override
    public void removePropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {

    }

}