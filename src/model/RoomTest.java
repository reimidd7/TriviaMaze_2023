package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the Room class. Uses JUnit5 tests.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
class RoomTest {

    /**
     * Room object for testing.
     */
    private Room myRoom;
    /**
     * List of door objects for testing.
     */
    private List<Doors> myDoors;

    @BeforeEach
    void setUp() {
        myDoors = new ArrayList<>();

        // Add doors for testing
        myDoors.add(new Doors(1));
        myDoors.add(new Doors(2));

        // Add Doors to Rooms
        myRoom = new Room(1, "Room 1");
        myRoom.addDoor(myDoors.get(0), Direction.EAST);
        myRoom.addDoor(myDoors.get(1), Direction.SOUTH);
    }

    @Test
    void testGetRoomId() {
        assertEquals(1, myRoom.getRoomId(), "Room Id not gotten correctly");
    }

    @Test
    void testGetDescription() {
        assertEquals("Room 1", myRoom.getDescription(), "Room Description is incorrect");
    }

    @Test
    void testIsLockedDefaultFalse() {
        assertFalse(myRoom.isRoomLocked(), "The Room is unlocked");
    }

    @Test
    void testIsLockedTrue() {
        myRoom.setRoomLocked(true);
        assertTrue(myRoom.isRoomLocked(), "The Room is locked");
    }

    @Test
    void testGetMapOfDoorsSize() {
        final Map<Doors, Direction> roomDoors = myRoom.getMapOfDoorsAndDir();
        assertEquals(myDoors.size(), roomDoors.size());
    }

    @Test
    void testGetMapOfDoorsAndDir() {
        final Map<Doors, Direction> roomDoors = myRoom.getMapOfDoorsAndDir();
        assertTrue(roomDoors.containsKey(myDoors.get(0)));
        assertTrue(roomDoors.containsKey(myDoors.get(1)));
    }

    @Test
    void testHasUnlockedDoorsDefaultTrue() {
        assertTrue(myRoom.roomHasUnlockedDoors(), "The default the doors are unlocked");
    }

    @Test
    void testHasUnlockedDoorsFalse() {
        myDoors.get(1).setLocked(false);
        myDoors.get(0).setLocked(false);
        assertFalse(myRoom.roomHasUnlockedDoors(), "There should be no unlocked doors");
    }

    @Test
    void testHasUnlockedDoorsMisMatch() {
        // if one door is unlocked and one is locked == true
        myDoors.get(1).setLocked(true);
        myDoors.get(0).setLocked(false);
        assertTrue(myRoom.roomHasUnlockedDoors(), "There should be unlocked doors");
    }

    @Test
    void testGetDoorByIdNoDoorWithTheID() {
        assertNull(myRoom.getDoorById(3));
    }

    @Test
    void testGetDoorById() {
        assertEquals(myDoors.get(0), myRoom.getDoorById(1));
    }

    @Test
    void testHasDoorYes() {
        assertTrue(myRoom.hasDoor(1), "Should have door 1");
    }

    @Test
    void testHasDoorNo() {
        assertFalse(myRoom.hasDoor(3), "Should not have door 3");
    }

    @Test
    void testIsEntranceYes() {
        myRoom.setEntrance(true);
        assertTrue(myRoom.isEntrance());
    }

    @Test
    void testIsEntranceNo() {
        assertFalse(myRoom.isEntrance());
    }

    @Test
    void testIsExitYes() {
        myRoom.setExit(true);
        assertTrue(myRoom.isExit());
    }

    @Test
    void testIsExitNo() {
        assertFalse(myRoom.isExit());
    }

}