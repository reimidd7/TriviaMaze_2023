package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private Maze maze;

    @BeforeEach
    public void setUp() {
        maze = new Maze(5, 5);
    }

    @Test
    public void testCorrectNumberOfRoomsAndDoors() {
        int expectedRooms = 5 * 5;
        int expectedDoors = 40;

        assertEquals(expectedRooms, maze.getRows() * maze.getCols());
        assertEquals(expectedDoors, maze.getDoors().size());
    }

    @Test
    public void testEntranceAndExit() {
        Room entrance = maze.getEntrance();
        Room exit = maze.getExit();

        assertNotNull(entrance);
        assertNotNull(exit);

        assertTrue(entrance.isEntrance());
        assertTrue(exit.isExit());

        assertEquals(0, entrance.getRow());
        assertEquals(0, entrance.getCol());
        assertEquals(maze.getRows() - 1, exit.getRow());
        assertEquals(maze.getCols() - 1, exit.getCol());
    }

    @Test
    void getRoom() {
        Room room = maze.getRoom(2, 3);
        assertNotNull(room);
        assertEquals("Room" + (2 * maze.getCols() + 3 + 1), room.getDescription());
    }

    @Test
    void testGetEntranceAndExit() {
        Room entrance = maze.getEntrance();
        Room exit = maze.getExit();

        assertNotNull(entrance);
        assertNotNull(exit);
    }

    @Test
    public void getRows() {
        assertEquals(5, maze.getRows());
    }

    @Test
    public void getCols() {
        assertEquals(5, maze.getCols());
    }

    @Test
    public void getDoors() {
        assertEquals(40, maze.getDoors().size());
    }
}