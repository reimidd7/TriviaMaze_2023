//package model;
//
//import view.PropertyChangeEnabledTriviaMazeControls;
//
//import java.awt.Point;
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import java.util.Map;
//
//public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls {
//
//    private final PropertyChangeSupport myPcs;
//
//    private Maze myMaze;
//
//    private Point myPlayerLoc;
//
//    private int myRows;
//
//    private int myCols;
//
//    /* TODO:
//    This is the construction of the Trivia Maze!
//
//    - Initialize the Maze and a new Character in the constructor.
//    - Add all the property change notices with myPcs
//    - Fire property changes
//
//
//     */
//
//    public TriviaMaze (Maze theMaze, int theRow, int theCol) {
//        super();
//        myMaze = theMaze;
//        this.myPcs = new PropertyChangeSupport(this);
//        myPlayerLoc = new Point(0,0); // This should come from get entrance but idk how to do that rn
//        myRows = theRow;
//        myCols = theCol;
//
//    }
//
//    @Override
//    public void newGame() {
//
//    }
//
//    @Override
//    public void down() {
//        // checks if the room has a door to the south... does not account for if the door is locked or unlocked
//        boolean checkForSouth = myMaze.getRoom(myPlayerLoc).getMapOfDoorsAndDir().containsValue(Direction.SOUTH);
//        if (myPlayerLoc.x < myMaze.getRows() - 1 && checkForSouth) {
//            myPlayerLoc.translate(1, 0);
//            notifyObseversOfLocationChange();
//        }
//
//    }
//
//    @Override
//    public void up() {
//        if (myPlayerLoc.x > 0) {
//            myPlayerLoc.translate(-1, 0);
//            notifyObseversOfLocationChange();
//        }
//    }
//
//    @Override
//    public void left() {
//        if (myPlayerLoc.y > 0) {
//            myPlayerLoc.translate(0, -1);
//            notifyObseversOfLocationChange();
//        }
//
//    }
//
//    @Override
//    public void right() {
//        if (myPlayerLoc.y < myMaze.getCols() - 1) {
//            myPlayerLoc.translate(0, 1);
//            notifyObseversOfLocationChange();
//        }
//
//    }
//
//    @Override
//    public Point getPlayerLocation() {
//        return new Point(myPlayerLoc);
//    }
//
//
//
//
//
//    private void notifyObseversOfLocationChange() {
//    }
//
//    @Override
//    public void addPropertyChangeListener(PropertyChangeListener theListener) {
//
//    }
//
//    @Override
//    public void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {
//
//    }
//
//    @Override
//    public void removePropertyChangeListener(PropertyChangeListener theListener) {
//
//    }
//
//    @Override
//    public void removePropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {
//
//    }
//}
