package model.map;

import model.enums.Symbol;

public class GenericWall extends Building {
    private final Symbol symbol;

    public GenericWall(Area area, Symbol symbol) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
