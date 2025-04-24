package model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

public class Farm implements Map {
    private static final int NUMBER_OF_ROWS = 25;
    private static final int NUMBER_OF_COLUMNS = 25;

    private final Tile[][] tiles;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public Farm(Tile[][] tiles) {
        this.tiles = tiles;
    }

    @Override
    public Tile getTileAt(Location location) {
        return tiles[location.row() - 1][location.column() - 1];
    }
}
