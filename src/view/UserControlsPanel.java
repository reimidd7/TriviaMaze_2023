package view;

import controller.TriviaMaze;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Direction;
import model.Doors;
import model.Room;

/**
 * Displays a control panel to tell the user to use their arrow keys.
 *
 * @author Danie Oum, Reilly Middlebrooks
 * @version Summer 2023
 */
public class UserControlsPanel extends JPanel {

    /**
     * Amount of points for a triangle.
     */
    private static final int TRI = 3;

    /**
     * Current Maze in motion.
     */
    private final TriviaMaze myMaze;

    public UserControlsPanel(final TriviaMaze theMaze) {
        super();
        myMaze = theMaze;
        final JLabel label = new JLabel("Use the arrow keys on your keyboard "
                + " to move the Player.");
        add(label);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Room room = myMaze.getRoom(myMaze.getPlayer().getPlayerLoc());
        //Map<Doors, Direction> mapOfRoomDoors = room.getMapOfDoorsAndDir();

        final int width = getWidth();
        final int height = getHeight();

        final int centerX = width / 2;
        final int centerY = height / 2;

        final int triangleSize = Math.min(width, height) / 4; // Adjust triangle size as needed
        final int spacing = triangleSize / 6; // Adjust spacing as needed

        final int halfTriangleSize = triangleSize / 2;
        final int spacingWithTriangle = spacing + halfTriangleSize;

        // Clear the panel
        theGraphics.setColor(Color.white);
        theGraphics.fillRect(0, 0, width, height);

//        for (Doors d: mapOfRoomDoors.keySet()) {
//            System.out.println(d.getDoorId() + "  " + mapOfRoomDoors.get(d).toString());
//
//            if (mapOfRoomDoors.get(d).equals(Direction.NORTH) && d.getDoorStatus()) {
//                // Up Triangle
//                int[] xPointsUp = {centerX, centerX - halfTriangleSize, centerX + halfTriangleSize};
//                int[] yPointsUp = {centerY - triangleSize - spacingWithTriangle, centerY - spacingWithTriangle, centerY - spacingWithTriangle};
//                g.setColor(Color.BLACK);
//                g.fillPolygon(xPointsUp, yPointsUp, 3);
//
//            } else if (mapOfRoomDoors.get(d).equals(Direction.SOUTH) && d.getDoorStatus()) {
//                // Down Triangle
//                int[] xPointsDown = {centerX, centerX - halfTriangleSize, centerX + halfTriangleSize};
//                int[] yPointsDown = {centerY + triangleSize + spacingWithTriangle, centerY + spacingWithTriangle, centerY + spacingWithTriangle};
//                g.setColor(Color.BLACK);
//                g.fillPolygon(xPointsDown, yPointsDown, 3);
//
//            } else if (mapOfRoomDoors.get(d).equals(Direction.WEST) && d.getDoorStatus()) {
//                // Left Triangle
//                int[] xPointsLeft = {centerX - triangleSize - spacingWithTriangle, centerX - spacingWithTriangle, centerX - spacingWithTriangle};
//                int[] yPointsLeft = {centerY, centerY - halfTriangleSize, centerY + halfTriangleSize};
//                g.setColor(Color.BLACK);
//                g.fillPolygon(xPointsLeft, yPointsLeft, 3);
//
//            } else if (mapOfRoomDoors.get(d).equals(Direction.EAST) && d.getDoorStatus()) {
//                // Right Triangle
//                int[] xPointsRight = {centerX + triangleSize + spacingWithTriangle, centerX + spacingWithTriangle, centerX + spacingWithTriangle};
//                int[] yPointsRight = {centerY, centerY - halfTriangleSize, centerY + halfTriangleSize};
//                g.setColor(Color.BLACK);
//                g.fillPolygon(xPointsRight, yPointsRight, 3);
//            }
//        }
        // Up Triangle
        final int[] xPointsUp = {centerX, centerX - halfTriangleSize,
                centerX + halfTriangleSize};
        final int[] yPointsUp = {centerY - triangleSize - spacingWithTriangle,
                centerY - spacingWithTriangle, centerY - spacingWithTriangle};
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillPolygon(xPointsUp, yPointsUp, TRI);
        // Down Triangle
        final int[] xPointsDown = {centerX, centerX - halfTriangleSize,
                centerX + halfTriangleSize};
        final int[] yPointsDown = {centerY + triangleSize + spacingWithTriangle,
                centerY + spacingWithTriangle, centerY + spacingWithTriangle};
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillPolygon(xPointsDown, yPointsDown, TRI);
        // Left Triangle
        final int[] xPointsLeft = {centerX - triangleSize - spacingWithTriangle,
                centerX - spacingWithTriangle, centerX - spacingWithTriangle};
        final int[] yPointsLeft = {centerY, centerY - halfTriangleSize,
                centerY + halfTriangleSize};
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillPolygon(xPointsLeft, yPointsLeft, TRI);
        // Right Triangle
        final int[] xPointsRight = {centerX + triangleSize + spacingWithTriangle,
                centerX + spacingWithTriangle, centerX + spacingWithTriangle};
        final int[] yPointsRight = {centerY, centerY - halfTriangleSize,
                centerY + halfTriangleSize};
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillPolygon(xPointsRight, yPointsRight, TRI);

    }
}
