package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to organize the rooms and doors and create the maze functional.
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class Maze implements Serializable {

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
    }

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

    public Room getRoom(final Point thePlayerLoc) {
        final int row = thePlayerLoc.x;
        final int col = thePlayerLoc.y;
        if (row >= 0 && row < myRows && col >= 0 && col < myCols) {
            return myRooms[row][col];
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

    /*
     * This method creates the maze using a list of Doors and Room.
     * Connects the Doors to two Rooms with no duplication.
     */
    protected void createMaze() {
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
    public List<Doors> createAllDoors() {
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
    protected void setEntrance() {
        myRooms[0][0].setEntrance(true);
    }

    // This method sets the exit of the maze.
    protected void setExit() {
        myRooms[ROWS_OF_DOORS][ROWS_OF_DOORS].setExit(true);
    }

}