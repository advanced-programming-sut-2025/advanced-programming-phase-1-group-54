package model.map;

import model.enums.Symbol;

public class Quarry extends Building {
    public Quarry(Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.QUARRY;
    }
}
