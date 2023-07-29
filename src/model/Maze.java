package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Class to organize the rooms and doors and create the maze functional.
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version 2023 20 July
 */
public class Maze {
    private Room[][] myRooms;
    private int myRows;
    private int myCols;

    /**
     * This method initializes the rows and columns of the maze and calls other methods.
     *
     * @param theRows The rows of the maze
     * @param theCols The columns of the maze
     */
    public Maze(int theRows, int theCols) {
        this.myRows = theRows;
        this.myCols = theCols;
        this.myRooms = new Room[theRows][theCols];
        createMaze();
        setEntrance();
        setExit();
    }

    /**
     * Maze testing.
     *
     * @param args
     */
    public static void main (String [] args) {
        Maze maze = new Maze(5, 5);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Room currentRoom = maze.getRoom(row, col);
                System.out.println("[" + row + "][" + col + "]    " + "Room: " + currentRoom.getDescription());
                for (Doors door : currentRoom.getListOfDoors()) {
                    System.out.println(" Door " + door.getDoorId());
                }
            }
        }
    }

    /**
     * This method creates the maze.
     */
    private void createMaze() {
        List<Doors> currDoors = createAllDoors();
        List<Room> currRooms = createAllRooms();
        int roomId = 0;

        for (int r = 0; r < myRows; r++) {
            for (int c = 0; c < myCols; c++) {
                addRoom(r, c, currRooms.get(roomId));
                roomId++;
            }
        }

        // Adding doors to the vertical rooms
        int row1 = 0;
        int row2 = 0;
        int doorId = 0;
        while (row1 <= 4 && row2 <= 4) {
            int col1 = 0;
            int col2 = 1;
            while (col1 <= 4 && col2 <= 4) {
                connectRooms(row1, col1, row2, col2, currDoors.get(doorId));
                doorId++;
                col1++;
                col2++;
            }
            row1++;
            row2++;
        }

        // Add doors to the horizontal rooms
        int col_1 = 0;
        int col_2 = 0;
        while (col_1 <= 4 && col_2 <= 4) {
            int row_1 = 0;
            int row_2 = 1;
            while (row_1 <= 4 && row_2 <= 4) {
                connectRooms(row_1, col_1, row_2, col_2, currDoors.get(doorId));
                doorId++;
                row_1++;
                row_2++;
            }
            col_1++;
            col_2++;
        }
    }

    /**
     * This method create all the doors of the maze.
     *
     * @return Returns allDoors
     */
    private List<Doors> createAllDoors() {
        int amtOfDoors = 40;
        List<Doors> allDoors = new ArrayList<>(amtOfDoors);
        for (int i = 1; i <= amtOfDoors; i++) {
            allDoors.add(new Doors(i));
        }
        return allDoors;
    }

    /**
     * This method creates all of the rooms of the maze.
     * @return Returns allRooms
     */
    private List<Room> createAllRooms() {
        int amtOfRooms = myRows * myCols;
        List<Room> allRooms = new ArrayList<>(amtOfRooms);
        for (int i = 1; i <= amtOfRooms; i++) {
            allRooms.add(new Room(i, "Room" + i));
        }
        return allRooms;
    }

    /**
     * Constructs the room.
     *
     * @param theRow The row of the maze
     * @param theCol The column of the maze
     * @param theRoom The room of the maze
     */
    public void addRoom(int theRow, int theCol, Room theRoom) {
        myRooms[theRow][theCol] = theRoom;
    }

    /**
     * This is the getter method for the room
     *
     * @param theRow The row of the maze
     * @param theCol The column of the maze
     * @return Returns the room
     */
    public Room getRoom(int theRow, int theCol) {
        if (theRow >= 0 && theRow < myRows && theCol >= 0 && theCol < myCols) {
            return myRooms[theRow][theCol];
        }
        return null;
    }

    /**
     * This method connects the rooms.
     *
     * @param row1 The row1
     * @param col1 The col2
     * @param row2 The row2
     * @param col2 The col2
     * @param door The door
     */
    public void connectRooms(int row1, int col1, int row2, int col2, Doors door) {
        Room room1 = getRoom(row1, col1);
        Room room2 = getRoom(row2, col2);
        if (room1 != null && room2 != null) {
            room1.addDoor(door);
            room2.addDoor(door);
        }
    }

    /**
     * This method sets the entrance of the maze.
     */
    private void setEntrance() {
        myRooms[0][0].setEntrance(true);
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
     * This method sets the exit of the maze.
     */
    public void setExit() {
        myRooms[4][4].setExit(true);
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
}