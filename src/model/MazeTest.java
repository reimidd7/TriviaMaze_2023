package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit Test for the Maze class.
 *
 * @author Danie Oum
 * @version Summer 2023
 */
class MazeTest {

    /**
     * Maze object for testing.
     */
    private Maze myMaze;

    @BeforeEach
    void setUp() {
        myMaze = new Maze(5, 5);
    }

    @Test
    public void testCorrectNumberOfRoomsAndDoors() {
        final int expectedRooms = 5 * 5;

        assertEquals(expectedRooms, myMaze.getRows() * myMaze.getCols());
    }

    @Test
    void testTotalDoorsCount() {
        final int expectedTotalDoors = 40;

        assertEquals(expectedTotalDoors, myMaze.createAllDoors().size());
    }

    @Test
    void getRows() {
        assertEquals(5, myMaze.getRows());
    }

    @Test
    void getCols() {
        assertEquals(5, myMaze.getCols());
    }

    @Test
    void getDoors() {
        assertEquals(40, myMaze.createAllDoors().size());
    }
}