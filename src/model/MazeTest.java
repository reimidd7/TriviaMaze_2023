package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze(5, 5);
    }

    @Test
    public void testCorrectNumberOfRoomsAndDoors() {
        int expectedRooms = 5 * 5;

        assertEquals(expectedRooms, maze.getRows() * maze.getCols());
    }

    @Test
    void testTotalDoorsCount() {
        int expectedTotalDoors = 40;

        assertEquals(expectedTotalDoors, maze.createAllDoors().size());
    }

    @Test
    void getRows() {
        assertEquals(5, maze.getRows());
    }

    @Test
    void getCols() {
        assertEquals(5, maze.getCols());
    }

    @Test
    void getDoors() {
        assertEquals(40, maze.createAllDoors().size());
    }
}