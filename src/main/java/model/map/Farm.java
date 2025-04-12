package model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

public class Farm extends Map {
    private int numberOfRows;
    private int numberOfColumns;
    private Tile[][] tiles = new Tile[numberOfRows][numberOfColumns];

    private boolean greenhouseBuilt;
}
