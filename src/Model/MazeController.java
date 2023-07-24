package Model;

// Needs to be reworked or removed/replaced
// Controls of the game
public class MazeController {
    private Maze maze;
    private QuestionDatabase questionDatabase; // SQL
    private GameView gameView; // GUI

    public MazeController(Maze maze, QuestionDatabase questionDatabase, GameView gameView) {
        this.maze = maze;
        this.questionDatabase = questionDatabase; // SQL
        this.gameView = gameView; // GUI
    }

    public void startGame() {
        // start at the beginning of the maze; info about current room
        // if currentRoom is locked -> random question from database
        // display question on the GUI
        // listen for the player?
        // if player answer is correct unlock the door
        // else the door remains locked
        // move to next room
    }

    public Room getCurrentRoom() {
        return maze.getCurrentRoom();
    }

    public QuestionAnswer getCurrentQuestion() {
        return maze.getCurrentRoom().getQuestionAnswer();
    }
}
