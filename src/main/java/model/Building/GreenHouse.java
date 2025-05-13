package model.Building;

import model.enums.Feature;
import model.enums.Symbol;
import model.map.Area;
import model.map.Lake;
import model.map.Location;
import model.map.Map;

public class GreenHouse extends Building {
    private final static int NUMBER_OF_ROWS = 5;
    private final static int NUMBER_OF_COLUMNS = 6;
    private final static int NEEDED_MONEY = 1000;
    private final static int NEEDED_WOOD = 500;

    private boolean built;

    private final Lake waterTank;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public static int getNeededMoney() {
        return NEEDED_MONEY;
    }

    public static int getNeededWood() {
        return NEEDED_WOOD;
    }

    public GreenHouse(Location location) {
        super(location, new Map(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));

        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                this.getTileAt(new Location(i, j)).addFeature(Feature.PROTECTED);
            }
        }

        waterTank = new Lake(new Area(new Location(0, 0), new Location(0, NUMBER_OF_COLUMNS - 1)));
        for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
            this.getTileAt(new Location(0, j)).setThingOnTile(waterTank);
            this.getTileAt(new Location(0, j)).addFeature(Feature.WATERED);
        }
    }

    public Lake getWaterTank() {
        return waterTank;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

    @Override
    public boolean canEnter() {
        return isBuilt();
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.GREENHOUSE;
    }
}
