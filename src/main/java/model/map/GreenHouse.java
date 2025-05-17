package model.map;

import model.DailyUpdate;
import model.enums.Feature;
import model.enums.Symbol;

public class GreenHouse extends Building implements DailyUpdate {
    private final static int NUMBER_OF_ROWS = 5;
    private final static int NUMBER_OF_COLUMNS = 6;
    private final static int NEEDED_MONEY = 1000;
    private final static int NEEDED_WOOD = 500;

    private boolean built;

    private final GenericWall waterTank;

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

        waterTank = new GenericWall(new Area(new Location(0, 0), new Location(0, NUMBER_OF_COLUMNS - 1)), Symbol.WELL);
        for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
            this.getTileAt(new Location(0, j)).setThingOnTile(waterTank);
            this.getTileAt(new Location(0, j)).addFeature(Feature.WATER);
        }
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

    public GenericWall getWaterTank() {
        return waterTank;
    }

    @Override
    public void nextDayUpdate() {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                this.getTileAt(new Location(i, j)).nextDayUpdate();
            }
        }
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
