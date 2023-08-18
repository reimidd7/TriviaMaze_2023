package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * JUnit tests for the PLayer class
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class PlayerTest {

    @Test
    void testSetPlayerDirection() {
        Point playerLoc = new Point(2, 3); // Replace with the desired player location
        Player player = new Player(playerLoc, Direction.NONE);

        player.setPlayerDir(Direction.NORTH);
        assertEquals(Direction.NORTH, player.getPlayerDir());

        player.setPlayerDir(Direction.WEST);
        assertEquals(Direction.WEST, player.getPlayerDir());

        player.setPlayerDir(Direction.SOUTH);
        assertEquals(Direction.SOUTH, player.getPlayerDir());

        player.setPlayerDir(Direction.EAST);
        assertEquals(Direction.EAST, player.getPlayerDir());
    }
}
