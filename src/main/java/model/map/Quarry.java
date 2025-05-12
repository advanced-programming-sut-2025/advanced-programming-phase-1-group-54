package model.map;

import model.Building.Building;

public class Quarry extends Building {
    public Quarry(Location upperLeft, Location lowerRight) {
        super(upperLeft, new Map(lowerRight.row() - upperLeft.row(),
                lowerRight.column() - upperLeft.column()));
    }
}
