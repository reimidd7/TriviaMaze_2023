package model;

public class Player {
    private int currentRow;
    private int currentCol;
    public Player(int theRow, int theCol) {
        this.currentRow = theRow;
        this.currentCol = theCol;
    }
    public int getCurrentRow() {
        return currentRow;
    }
    public int getCurrentCol() {
        return currentCol;
    }
}
