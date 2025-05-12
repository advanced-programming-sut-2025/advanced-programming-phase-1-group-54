package model.map;

import model.Building.Building;
import model.enums.Symbol;

public class Quarry extends Building {
    public Quarry(Location upperLeft, Location lowerRight) {
        super(upperLeft, new Map(lowerRight.row() - upperLeft.row(),
                lowerRight.column() - upperLeft.column()));
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.QUARRY;
    }
}
