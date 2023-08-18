package view;

import controller.TriviaMaze;
import model.Direction;
import model.Doors;
import model.Room;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class UserControlsPanel extends JPanel {

    private TriviaMaze myMaze;

    private Room myRoom;

    private HashMap<Doors, Direction> mapOfRoomDoors;

    public UserControlsPanel(final TriviaMaze theMaze) {
        super();
        myMaze = theMaze;
        JLabel label = new JLabel("Use the arrow keys on your keyboard to move the Player.");
        add(label);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        myRoom = myMaze.getRoom(myMaze.getPlayer().getPlayerLoc());
        mapOfRoomDoors = (HashMap<Doors, Direction>) myRoom.getMapOfDoorsAndDir();

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int triangleSize = Math.min(width, height) / 4; // Adjust triangle size as needed
        int spacing = triangleSize / 6; // Adjust spacing as needed

        int halfTriangleSize = triangleSize / 2;
        int spacingWithTriangle = spacing + halfTriangleSize;

        // Clear the panel
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

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
        int[] xPointsUp = {centerX, centerX - halfTriangleSize, centerX + halfTriangleSize};
        int[] yPointsUp = {centerY - triangleSize - spacingWithTriangle, centerY - spacingWithTriangle, centerY - spacingWithTriangle};
        g.setColor(Color.BLACK);
        g.fillPolygon(xPointsUp, yPointsUp, 3);
        // Down Triangle
        int[] xPointsDown = {centerX, centerX - halfTriangleSize, centerX + halfTriangleSize};
        int[] yPointsDown = {centerY + triangleSize + spacingWithTriangle, centerY + spacingWithTriangle, centerY + spacingWithTriangle};
        g.setColor(Color.BLACK);
        g.fillPolygon(xPointsDown, yPointsDown, 3);
        // Left Triangle
        int[] xPointsLeft = {centerX - triangleSize - spacingWithTriangle, centerX - spacingWithTriangle, centerX - spacingWithTriangle};
        int[] yPointsLeft = {centerY, centerY - halfTriangleSize, centerY + halfTriangleSize};
        g.setColor(Color.BLACK);
        g.fillPolygon(xPointsLeft, yPointsLeft, 3);
        // Right Triangle
        int[] xPointsRight = {centerX + triangleSize + spacingWithTriangle, centerX + spacingWithTriangle, centerX + spacingWithTriangle};
        int[] yPointsRight = {centerY, centerY - halfTriangleSize, centerY + halfTriangleSize};
        g.setColor(Color.BLACK);
        g.fillPolygon(xPointsRight, yPointsRight, 3);

    }


}
