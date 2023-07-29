package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Room object has a list of doors attached to it.
 * It creates the rooms for the maze.
 *
 * @author Reilly Middlebrooks
 * @version August 2023
 */
public class Room {

    /**
     * This list is the doors attached to the Room.
     */
    List<Doors> listOfDoors;
    /**
     * Room label to allow for attachment of doors.
     */
    private final int roomId;

    /**
     * Room name. Mainly for testing purposes. (Room_ID)
     */
    private final String description;
    /**
     * If the Room has all locked doors, the Room is locked.
     */
    private boolean isLocked;
    /**
     * If the Room is the entrance, true.
     */
    private boolean isEntrance;
    /**
     * If the Room is the exit, true.
     */
    private boolean isExit;

    /**
     * Creates the default Room.
     * @param roomId the room number.
     * @param description the room name.
     */
    public Room(int roomId, String description) {
        this.roomId = roomId;
        this.description = description;
        this.isLocked = false;
        listOfDoors =  new ArrayList<>();
    }

    /**
     * Gets the Room ID.
     * @return the Room number.
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Gets the Room name. Mainly used for testing.
     * @return the room name.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Determines if the room is locked. USED WHEN USER PLAYS.
     * @return true if locked, false if unlocked.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Sets id the room is locked. This happens when all the doors are locked.
     * @param locked true if locked, false if unlocked.
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Gets the list of doors attached to the certain room.
     * @return a list of doors.
     */
    public List<Doors> getListOfDoors () {
        return listOfDoors;
    }

    /**
     * Adds a door to the current room.
     * @param door which door to add based upon the door ID.
     */
    public void addDoor(Doors door) {
        listOfDoors.add(door);
    }

    /**
     * Determines in the Room has any unlocked doors.
     * Used injuction with setLocked and isLocked.
     * @return true when there are unlocked doors, false if not.
     */
    public boolean hasUnlockedDoors() {
        for (Doors door: listOfDoors) {
            if (!door.getDoorStatus()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the door by its ID. Helpful when changing
     *      its status or calling a question.
     * @param doorId the door ID
     * @return a Doors object
     */
    public Doors getDoorById(int doorId) {
        for (Doors door: listOfDoors) {
            if (door.getDoorId() == doorId) {
                return door;
            }
        }
        return null;
    }

    /**
     * Does the room have the door in question?
     * @param doorId the door ID in question.
     * @return true if the room has that door, false if not.
     */
    public boolean hasDoor(int doorId) {
        return getDoorById(doorId) != null;
    }

    /**
     * Is the Room the entrance to the maze.
     * @return true if it is the entrance, false if not.
     */
    public boolean isEntrance() {
        return isEntrance;
    }

    /**
     * Sets the entrance Room of the maze.
     * @param entrance true if it is the entrance, false if not.
     */
    public void setEntrance(boolean entrance) {
        isEntrance = entrance;
    }

    /**
     * Is the Room the exit to the maze.
     * @return true if it is the exit, false if not.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the exit Room of the maze.
     * @param exit true if it is the exit, false if not.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

}
