package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RoomTest {

    private Room room;
    private List<Doors> doors;

    @BeforeEach
    void setUp() {
        doors = new ArrayList<>();

        // Add doors for testing
        doors.add(new Doors(1));
        doors.add(new Doors(2));

        // Add Doors to Rooms
        room = new Room(1, "Room 1");
        room.addDoor(doors.get(0));
        room.addDoor(doors.get(1));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetRoomId() {
        assertEquals(1, room.getRoomId(), "Room Id not gotten correctly");
    }

    @Test
    void testGetDescription() {
        assertEquals("Room 1", room.getDescription(), "Room Description is incorrect");
    }

    @Test
    void testIsLockedDefaultFalse() {
        assertFalse(room.isLocked(), "The Room is unlocked");
    }

    @Test
    void testIsLockedTrue() {
        room.setLocked(true);
        assertTrue(room.isLocked(), "The Room is locked");
    }

    @Test
    void testGetListOfDoorsSize() {
        List<Doors> roomDoors = room.getListOfDoors();
        assertEquals(doors.size(), roomDoors.size());
    }

    @Test
    void testGetListOfDoors() {
        List<Doors> roomDoors = room.getListOfDoors();
        assertEquals(doors.get(0), roomDoors.get(0));
        assertEquals(doors.get(1), roomDoors.get(1));
    }

    @Test
    void testHasUnlockedDoorsDefaultTrue() {
        assertTrue(room.hasUnlockedDoors(), "The default the doors are unlocked");
    }

    @Test
    void testHasUnlockedDoorsFalse() {
        doors.get(1).setLocked(false);
        doors.get(0).setLocked(false);
        assertFalse(room.hasUnlockedDoors(), "There should be no unlocked doors");
    }

    @Test
    void testHasUnlockedDoorsMisMatch() {
        // if one door is unlocked and one is locked == true
        doors.get(1).setLocked(true);
        doors.get(0).setLocked(false);
        assertTrue(room.hasUnlockedDoors(), "There should be unlocked doors");
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    void testGetDoorByIdNoDoorWithTheID() {
        assertNull(room.getDoorById(3));
    }

    @Test
    void testGetDoorById() {
        assertEquals(doors.get(0), room.getDoorById(1));
    }

    @Test
    void testHasDoorYes() {
        assertTrue(room.hasDoor(1), "Should have door 1");
    }

    @Test
    void testHasDoorNo() {
        assertFalse(room.hasDoor(3), "Should not have door 3");
    }

    @Test
    void testIsEntranceYes() {
        room.setEntrance(true);
        assertTrue(room.isEntrance());
    }

    @Test
    void testIsEntranceNo() {
        assertFalse(room.isEntrance());
    }

    @Test
    void testIsExitYes() {
        room.setExit(true);
        assertTrue(room.isExit());
    }

    @Test
    void testIsExitNo() {
        assertFalse(room.isExit());
    }
}