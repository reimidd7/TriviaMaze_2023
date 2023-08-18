package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Room object has a list of doors attached to it.
 * It creates the rooms for the maze.
 *
 * @author Reilly Middlebrooks
 * @version August 2023
 */
public class Room implements Serializable {

    /**
     * This Map is the Doors/Directions attached to the Room.
     */
    private final Map<Doors, Direction> myMapOfDoors;
    /**
     * Room label to allow for attachment of doors.
     */
    private final int myRoomId;

    /**
     * Room name. Mainly for testing purposes. (Room_ID)
     */
    private final String myDescription;
    /**
     * If the Room has all locked doors, the Room is locked.
     */
    private boolean myIsLocked;
    /**
     * If the Room is the entrance, true.
     */
    private boolean myIsEntrance;
    /**
     * If the Room is the exit, true.
     */
    private boolean myIsExit;

    /**
     * Creates the default Room.
     * @param theRoomId the room number.
     * @param theDescription the room name.
     */
    public Room(final int theRoomId, final String theDescription) {
        this.myRoomId = theRoomId;
        this.myDescription = theDescription;
        this.myIsLocked = false;
        myMapOfDoors =  new HashMap<>();
    }

    /**
     * Gets the Room ID.
     * @return the Room number.
     */
    public int getRoomId() {
        return myRoomId;
    }

    /**
     * Gets the Room name. Mainly used for testing.
     * @return the room name.
     */
    public String getDescription() {
        return myDescription;
    }

    /**
     * Determines if the room is locked. USED WHEN USER PLAYS.
     * @return true if locked, false if unlocked.
     */
    public boolean isRoomLocked() {
        return myIsLocked;
    }

    /**
     * Sets id the room is locked. This happens when all the doors are locked.
     * @param theLocked true if locked, false if unlocked.
     */
    public void setRoomLocked(final boolean theLocked) {
        myIsLocked = theLocked;
    }

    /**
     * Gets the Map of doors/direction attached to the current room.
     * @return a Map of doors and their Directions.
     */
    public Map<Doors, Direction> getMapOfDoorsAndDir() {
        return myMapOfDoors;
    }

    /**
     * Adds a door to the current room.
     * @param theDoor which door to add based upon the door ID.
     */
    public void addDoor(final Doors theDoor, final Direction theDirection) {

        myMapOfDoors.put(theDoor, theDirection);
    }

    /**
     * Determines in the Room has any unlocked doors.
     * Used injuction with setLocked and isLocked.
     * @return true when there are unlocked doors, false if not.
     */
    public boolean roomHasUnlockedDoors() {
        for (Doors door: myMapOfDoors.keySet()) {
            if (door.getDoorStatus()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the door by its ID. Helpful when changing
     *      its status or calling a question.
     * @param theDoorId the door ID
     * @return a Doors object
     */
    public Doors getDoorById(final int theDoorId) {
        for (Doors door: myMapOfDoors.keySet()) {
            if (door.getDoorId() == theDoorId) {
                return door;
            }
        }
        return null;
    }

    public Doors getDoorByDirection(final Direction theDir) {
        for (Doors d: myMapOfDoors.keySet()) {
            if (myMapOfDoors.get(d).equals(theDir)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Does the room have the door in question?
     * @param theDoorId the door ID in question.
     * @return true if the room has that door, false if not.
     */
    public boolean hasDoor(final int theDoorId) {
        return getDoorById(theDoorId) != null;
    }

    /**
     * Is the Room the entrance to the maze.
     * @return true if it is the entrance, false if not.
     */
    public boolean isEntrance() {
        return myIsEntrance;
    }

    /**
     * Sets the entrance Room of the maze.
     * @param theEntrance true if it is the entrance, false if not.
     */
    public void setEntrance(final boolean theEntrance) {
        myIsEntrance = theEntrance;
    }

    /**
     * Is the Room the exit to the maze.
     * @return true if it is the exit, false if not.
     */
    public boolean isExit() {
        return myIsExit;
    }

    /**
     * Sets the exit Room of the maze.
     * @param theExit true if it is the exit, false if not.
     */
    public void setExit(final boolean theExit) {
        myIsExit = theExit;
    }

}
