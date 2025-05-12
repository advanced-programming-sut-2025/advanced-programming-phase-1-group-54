package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.alive.Player;
import model.enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class World {
    private static final int NUMBER_OF_ROWS = 52;
    private static final int NUMBER_OF_COLUMNS = 100;

    private final Farm[] playerFarms;

    private final Map map;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public World(Farm[] playerFarms, Map map) {
        this.playerFarms = playerFarms;
        this.map = map;
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public Farm getFarm(Player player) {
        for (Farm farm : playerFarms) {
            if (farm.getOwner().equals(player))
                return farm;
        }
        return null;
    }

    public int getDistance(Location A, Location B) {
        return map.getDistance(A, B);
    }

    public ArrayList<Direction> getShortestPath(Location A, Location B) {
        return map.getShortestPath(A, B);
    }
}
