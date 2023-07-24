package Model;

// Create an AbstractMaze.java that can get the current Room information and accessible doors/rooms
// Create an AbstractRoom.java

// Serialization
/*
import java.io.Serializable;
import java.util.Set;

public interface Maze extends Iterable<Room>, Serializable {
    Set<Room> getRooms();
    Set<Room> getDoors(Room room);
}
*/

public class Maze {
    private Room[][] rooms;
    private int currentRow;
    private int currentColumn;

    public Maze(int rows, int columns) {
        rooms = new Room[rows][columns];
        currentRow = 0;
        currentColumn = 0;
        // Initialize the maze with rooms
        // ...

    }

    public Room getCurrentRoom() {
        return rooms[currentRow][currentColumn];
    }

    public void movePlayer(Direction direction) { // make an enum for direction?
        // Update the currentRow and currentColumn based on the direction
        // ...
    }

    // Other methods to manage the maze structure
    // ...

    // Separate Room into different class?
    public class Room {
        private boolean isLocked;
        private QuestionAnswer questionAnswer;
    }

    public Room(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
        isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        isLocked = false;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }
}