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
}