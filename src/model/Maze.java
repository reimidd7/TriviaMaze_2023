//package model;
//
//// Create an AbstractMaze.java that can get the current Room information and accessible doors/rooms
//// Create an AbstractRoom.java
//
//// Serialization
///*
//import java.io.Serializable;
//import java.util.Set;
//
//public interface Maze extends Iterable<Room>, Serializable {
//    Set<Room> getRooms();
//    Set<Room> getDoors(Room room);
//}
//*/
//
//public class Maze {
//    private Room[][] rooms;
//    private int currentRow;
//    private int currentColumn;
//
//    public Maze(int rows, int columns) {
//        rooms = new Room[rows][columns];
//        currentRow = 0;
//        currentColumn = 0;
//        // Initialize the maze with rooms
//        // ...
//
//    }
//
//    public Room getCurrentRoom() {
//        return rooms[currentRow][currentColumn];
//    }
//
//    public void movePlayer(Direction direction) { // make an enum for direction?
//        // Update the currentRow and currentColumn based on the direction
//        // ...
//    }
//
//    // Other methods to manage the maze structure
//    // ...
//
