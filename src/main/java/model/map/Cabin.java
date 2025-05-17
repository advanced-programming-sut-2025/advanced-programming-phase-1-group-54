package model.map;

import model.enums.Symbol;

public class Cabin extends Building {
    private final static int NUMBER_OF_ROWS = 4;
    private final static int NUMBER_OF_COLUMNS = 4;

    private final Refrigerator refrigerator = new Refrigerator();

    public Cabin(Location location) {
        super(location, new Map(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));

        this.getTileAt(getRandomLocation()).setThingOnTile(refrigerator);
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.HOUSE;
    }
}
