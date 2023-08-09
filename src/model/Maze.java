package model;

import view.PropertyChangeEnabledTriviaMazeControls;
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

    private Point myPlayerLoc;

    private final PropertyChangeSupport myPcs;


    /**
     * This method initializes the rows and columns of the maze and calls other methods.
     *
     * @param theRows The rows of the maze
     * @param theCols The columns of the maze
     */
    public Maze(final int theRows, final int theCols) {
        super();
        this.myRows = theRows;
        this.myCols = theCols;
        this.myRooms = new Room[theRows][theCols];
        this.myPcs = new PropertyChangeSupport(this);
//        createMaze();
//        setEntrance();
//        setExit();
        newGame();

    }


    @Override
    public void newGame() {

        createMaze();
        myPlayerLoc = new Point(0,0); // This should come from get entrance but idk how to do that rn
        setEntrance();
        setExit();

        // Change any states? player location. door status?
    }

    @Override
    public void down() {
        Point oldPlayerLoc = myPlayerLoc;
        // checks if the room has a door to the south and if it's unlocked.
        //boolean checkForSouth = getRoom(myPlayerLoc).getDoorByDirection(Direction.SOUTH).getDoorStatus();

        if (myPlayerLoc.x < getRows() - 1 ) {
            myPlayerLoc.translate(1, 0);
            notifyObseversOfLocationChange();
            //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

        }

        // CHANGE LOCATION STATE?
        //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

    }



    @Override
    public void up() {
        Point oldPlayerLoc = myPlayerLoc;

        // checks if the room has a door to the south and if it's unlocked.
        //boolean checkForNorth = getRoom(myPlayerLoc).getDoorByDirection(Direction.NORTH).getDoorStatus();

        if (myPlayerLoc.x > 0 ) {
            myPlayerLoc.translate(-1, 0);
            //myPlayerLoc.move(-1, 0);
            notifyObseversOfLocationChange();
            //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

        }
        // CHANGE LOCATION STATE?
       // myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

    }

    @Override
    public void left() {
        Point oldPlayerLoc = myPlayerLoc;

        // checks if the room has a door to the south and if it's unlocked.
        //boolean checkForWest = getRoom(myPlayerLoc).getDoorByDirection(Direction.WEST).getDoorStatus();

        if (myPlayerLoc.y > 0 ) {
            myPlayerLoc.translate(0, -1);
            notifyObseversOfLocationChange();
            //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

        }
        // CHANGE LOCATION STATE?
        //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);


    }

    @Override
    public void right() {
        Point oldPlayerLoc = myPlayerLoc;

        // checks if the room has a door to the south and if it's unlocked.
       // boolean checkForEast = getRoom(myPlayerLoc).getDoorByDirection(Direction.EAST).getDoorStatus();

        if (myPlayerLoc.y < getCols() - 1 ) {
            myPlayerLoc.translate(0, 1);
            //myPlayerLoc.move(0,1);
            notifyObseversOfLocationChange();
            //myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, myPlayerLoc);

        }
        // CHANGE LOCATION STATE?


    }

    @Override
    public Point getPlayerLocation() {

        return new Point(myPlayerLoc);

        // CHANGE LOCATION STATE?


    }

    @Override
    public void checkDoors() { //TODO: IDK if we need this. Maybe as a private method?
        Room playerRoom = getRoom(myPlayerLoc);
        Map<Doors, Direction> map = playerRoom.getMapOfDoorsAndDir();
        playerRoom.hasUnlockedDoors();

    }

    @Override
    public void updateDoors() { //TODO: Change status us the user gets the Q wrong
        //CHANGE DOOR STATE?
    }

    private void notifyObseversOfLocationChange() {
        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, null, new Point(myPlayerLoc));
    }
    // ------------------------------------- ^^^ For property change stuff

    //TODO IDK this can be used in the checkFor... variable....I think
    public boolean isDoorUnlockedByDirection(Point thePlayerLoc, Direction theDir) {
        Room currRoom = getRoom(thePlayerLoc); //room where the player is located
        Doors currDoor = null;
        for (Doors d: currRoom.getMapOfDoorsAndDir().keySet()) {
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
     * Maze testing.
     */
//    public static void main(String [] args) {
//        Maze maze = new Maze(5, 5);
//        for (int row = 0; row < 5; row++) {
//            for (int col = 0; col < 5; col++) {
//                Room currentRoom = maze.getRoom(row, col);
//                System.out.println("[" + row + "][" + col + "]    " + "Room: " + currentRoom.getDescription());
//                for (Doors door : currentRoom.getMapOfDoorsAndDir().keySet()) {
//                    Direction d = currentRoom.getMapOfDoorsAndDir().get(door);
//                     System.out.println(" Door " + door.getDoorId() + " " + d);
//                }
//            }
//        }
//    }

    /**
     * TODO: Might not need this...
     * When given a certain Room, it will return the map of Doors and Directions.
     *  ** MIGHT WANT TO CHANGE THE PARAMETER TO THE ROOMID.
     *
     * @param theCurrentRoom current room that we need to know the Doors and Directions of.
     * @return map of doors and directions
     */
//    public Map<Doors, Direction>
//                        getDoorsAndDirectionOfACertainRoom(final Room theCurrentRoom) {
//        final Map<Doors, Direction> doorsDirectionMap;
//        doorsDirectionMap = theCurrentRoom.getMapOfDoorsAndDir();
//        return doorsDirectionMap;
//    }

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
    public Doors getDoor(Point thePlayerLoc, Direction thedir) {
        Room currRoom = getRoom(thePlayerLoc);
        Doors doorInDirection = null;

        if (currRoom.hasDoorInDirection(thedir)) {
            doorInDirection = currRoom.getDoorByDirection(thedir);
        }
        return doorInDirection;
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
        myRooms[ROWS_OF_DOORS][ROWS_OF_DOORS].setExit(true);
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