package view;

public interface TriviaMazeControls {

    void newGame();

    void step();

    // Player movement update question to the door they just touched
    // if correct move character location to the new Room index
    void down();

    void up();

    void left();

    void right();

    void setCharacterAtPoint();

    void answerMultipleChoice();

    void answerTrueFalse();

    void answerShortAnswer();

    void checkDoors();

    void updateDoors();
    // if door is true set to false

}
