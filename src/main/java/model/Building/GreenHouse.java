package model.Building;

import model.map.Location;
import model.map.Map;

public class GreenHouse extends Building {
    private final static int numberOfRows = 5;
    private final static int numberOfColumns = 6;

    private boolean built;

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static int getNumberOfColumns() {
        return numberOfColumns;
    }

    public GreenHouse(Location location) {
        super(location, new Map(numberOfRows, numberOfColumns));
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
}
