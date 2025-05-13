package model.Building;

import model.Refrigerator;
import model.enums.Symbol;
import model.map.Location;
import model.map.Map;

public class Cabin extends Building {
    private final static int NUMBER_OF_ROWS = 4;
    private final static int NUMBER_OF_COLUMNS = 4;

    private final Refrigerator refrigerator = new Refrigerator();

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public Cabin(Location location) {
        super(location, new Map(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));

        this.getTileAt(new Location(0, 0)).setThingOnTile(refrigerator);
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.HOUSE;
    }
}
