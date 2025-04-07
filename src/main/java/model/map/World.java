package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.Tile;

public class World {
    private int numberOfRows;
    private int numberOfColumns;
    private Tile[][] tiles = new Tile[numberOfRows][numberOfColumns];
}
