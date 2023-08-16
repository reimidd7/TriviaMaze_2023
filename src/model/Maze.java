package model;

import view.PropertyChangeEnabledTriviaMazeControls;

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

    //private Doors myDoor;

    //private Room room;

    private Question myQues;
    boolean canGetThrough = false;

    boolean dontAsk = false; //false you can ask

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
        createMaze();
        myPlayer = new Player(new Point(0, 0), Direction.NONE); // This should come from get entrance but idk how to do that rn
        //System.out.println("First   " + myPlayer.getPlayerLoc().toString() + " " + myPlayer.getPlayerDir().toString());

        setEntrance();
        setExit();

        // save old values
        Point oldPlayerLoc = myPlayer.getPlayerLoc();
        //Doors oldDoor = myDoor;
        // Change any states? player location. door status?
        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, oldPlayerLoc, new Point(myPlayer.getPlayerLoc()));
        //myPcs.firePropertyChange(PROPERTY_DOOR_STATUS, oldDoor, myDoor);
        // myPcs.firePropertyChange(PROPERTY_NEW_QUESTION, null, myDoor);
    }

    @Override
    public void down() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();

        Room room = getRoom(myPlayerLoc);
//        // checks if the room has a door to the south and if it's unlocked.
        boolean checkForSouth = room.getDoorByDirection(Direction.SOUTH).getDoorStatus();
        //Doors door = room.getDoorByDirection(myPlayer.getPlayerDir());

        //System.out.println("Question - - - " + room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion().getQuestion());


        if (getCanGetThrough()) {
            setCanGetThrough(false);
            if (myPlayerLoc.x < getRows() - 1 && checkForSouth) {
                // is the new location ok - in bounds and across an open door
                // set the question
                // CHECK if player gets the question...
                // correct move places
                // incorrect change doors


                myPlayerLoc.translate(1, 0);         //Translates location
                notifyObseversOfLocationChange();
                checkForWin();
            }
        }

        //System.out.println("Down   " + myPlayer.getPlayerLoc().toString() + " " + myPlayer.getPlayerDir().toString());


//            sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }
    @Override
    public void lookDown() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();

        Room room = getRoom(myPlayerLoc);
        myPlayer.setPlayerDir(Direction.SOUTH);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());

    }


    @Override
    public void up() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        Room room = getRoom(myPlayerLoc);
        //sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());


        if (myPlayerLoc.x > 0) {
            myPlayerLoc.translate(-1,0);         //Translates location
            notifyObseversOfLocationChange();
            checkForWin();
        }

        System.out.println("up   " + myPlayer.getPlayerLoc().toString() + " " + myPlayer.getPlayerDir().toString());

        //sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());


    }

    @Override
    public void lookUp() {
        myPlayer.setPlayerDir(Direction.NORTH);
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        Room room = getRoom(myPlayerLoc);
        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());

    }

    @Override
    public void left() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        Room room = getRoom(myPlayerLoc);

        // checks if the room has a door to the west and if it's unlocked.
        //TODO: THIS IS WRONG. NO DOORS ARE LABELED WITH WEST

        boolean checkForWest = room.hasDoorInDirection(Direction.WEST);
        if (getCanGetThrough()) {
            setCanGetThrough(false);

            if (myPlayerLoc.y > 0 ) {
                myPlayerLoc.translate(0,-1);         //Translates location
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("door locked ***");
            }

            //System.out.println("left   " + myPlayer.getPlayerLoc().toString() + " " + myPlayer.getPlayerDir().toString());

            //sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());

        }
    }

    @Override
    public void lookLeft() {
        myPlayer.setPlayerDir(Direction.WEST);
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        Room room = getRoom(myPlayerLoc);
        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void right() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();
        Room room = getRoom(myPlayerLoc);

        // checks if the room has a door to the east and if it's unlocked.
        boolean checkForEast = room.getDoorByDirection(Direction.EAST).getDoorStatus();

        //System.out.println("Question - - - " + room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion().getQuestion());
        if (getCanGetThrough()) {
            setCanGetThrough(false);

            if (myPlayerLoc.y < getCols() - 1 && checkForEast) {
                myPlayerLoc.translate(0, 1);         //Translates location
                notifyObseversOfLocationChange();
                checkForWin();
            } else {
                System.out.println("door locked ***");
            }

            // System.out.println("right   " + myPlayer.getPlayerLoc().toString() + " " + myPlayer.getPlayerDir().toString());


        }
    }
    @Override
    public void lookRight() {
        Point myPlayerLoc = myPlayer.getPlayerLoc();

        Room room = getRoom(myPlayerLoc);
        myPlayer.setPlayerDir(Direction.EAST);

        sendQuestion(room.getDoorByDirection(myPlayer.getPlayerDir()).getCurrQuestion());
    }

    @Override
    public void sendQuestion(Question q) {
        //myQues = q;
        myQues = q;
        //System.out.println("Send question");
        myPcs.firePropertyChange(PROPERTY_NEW_QUESTION, null, q);
    }


    public Question getQuestion() {
        //System.out.println("*****getQuestion()" + myQues);
        return myQues;
    }


    /*TODO: When a key is pressed we want to
        grab the question in that direction
        Ask the question
        if the user is correct move the player in that direction
        if the user is incorrect change the door color (user remains in the same room)
         */
    //I WANT AN ASK QUESTION THAT SETS OFF THE DISPLAY

    //GET current facing door
    // get current question
    // ask the question

    // qdp NOT UPDATING BECAUSE IT IS NOT PAINTED COMPONENTS?

    @Override
    public void updateDoors() { //TODO: Change status us the user gets the Q wrong

        Room room = getRoom(myPlayer.getPlayerLoc());
        Doors door = room.getDoorByDirection(myPlayer.getPlayerDir());
        //CHANGE DOOR STATE?

        if (door.getDoorStatus()) {
            System.out.println("update door call");
            door.setLocked(false);
            myPcs.firePropertyChange(PROPERTY_DOOR_STATUS, null, door);
        }
    }









    public void setCanGetThrough(boolean boo) {
        canGetThrough = boo;
    }

    public boolean getCanGetThrough() {
        return canGetThrough;
    }





    private void notifyObseversOfLocationChange() {

        myPcs.firePropertyChange(PROPERTY_LOCATION_CHANGE, null,
                myPlayer);


    }
    // ------------------------------------- ^^^ For property change stuff

    public Direction getPlayerDirection() {
        return myPlayer.getPlayerDir();
    }

    public Player getPlayer() {
        return myPlayer;
    }
    //TODO IDK this can be used in the checkFor... variable....I think

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
    public Point getEntrance() {
        for (int row = 0; row < myRows; row++) {
            for (int column = 0; column < myCols; column++) {
                if (myRooms[row][column].isEntrance()) {
                    return new Point(row, column);
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
    public Point getExit() {
        for (int row = 0; row < myRows; row++) {
            for (int column = 0; column < myCols; column++) {
                if (myRooms[row][column].isExit()) {
                    return new Point(row, column);
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
//    /**
//     * Gets a list of all doors in the maze.
//     *
//     * @return A list of doors.
//     */
//    public Doors getDoor() {
//        Room room = getRoom(myPlayer.getPlayerLoc());
//        Doors door = room.getDoorByDirection(myPlayer.getPlayerDir());
//
//        return door;
//    }

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

    public boolean checkForWin() {
        if (myPlayer.getPlayerLoc().equals(new Point(4,4))) {
            System.out.println("Congratulations! You have reached the exit and won!");
            return true;
        } else {
            return false;
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