package model.map;

import model.Building.Building;
import model.enums.Symbol;

public class Lake extends Building {
    public Lake(Location upperLeftLocation, Location lowerRightLocation) {
        super(upperLeftLocation, new Map(lowerRightLocation.row() - upperLeftLocation.row(),
                lowerRightLocation.column() - upperLeftLocation.column()));
    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.LAKE;
    }
}
