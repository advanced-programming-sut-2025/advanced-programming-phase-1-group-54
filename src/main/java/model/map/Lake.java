package model.map;

import model.Building.Building;
import model.enums.Symbol;

public class Lake extends Building {
    public Lake(Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
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
