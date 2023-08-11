package model;

import java.awt.Point;

public class Player {

    private Point myPlayerLoc;

    private Direction myPlayerDir;

    public Player(Point theLoc, Direction theDir) {
        this.myPlayerLoc = theLoc;
        this.myPlayerDir = theDir;
    }

    public Point getPlayerLoc() {
        return myPlayerLoc;
    }

    public void setPlayerLoc(Point thePlayerLoc) {
        this.myPlayerLoc = thePlayerLoc;
    }

    public Direction getPlayerDir() {
        return myPlayerDir;
    }

}
