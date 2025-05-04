package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.Walkable;
import model.enums.Direction;
import model.enums.Weather;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class World implements Map {
    private static final int NUMBER_OF_ROWS = 100;
    private static final int NUMBER_OF_COLUMNS = 100;

    private final Tile[][] tiles;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public World(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getEnergyToWalk(Location A, Location B) {
        int[][][] distances = new int[getNumberOfRows()][getNumberOfColumns()][Direction.values().length];
        for (int[][] row : distances) {
            for (int[] cell : row) {
                Arrays.fill(cell, 1_000_000_000);
            }
        }

        TreeSet<Node> nodes = new TreeSet<>();
        for (Direction direction : Direction.values())
            nodes.add(new Node(0, A, direction));

        while (!nodes.isEmpty()) {
            Node node = nodes.pollFirst();

            for (Direction direction : Direction.values()) {
                Location newLocation = node.location().getLocationAt(direction);

                if (!(this.getTileAt(newLocation).isWalkable()))
                    continue;

                int newDistance = node.distance() + 1 + (direction != node.direction() ? 10 : 0);

                if (newDistance < distances[newLocation.row() - 1][newLocation.column() - 1][direction.ordinal()]) {
                    nodes.remove(new Node(distances[newLocation.row() - 1][newLocation.column() - 1][direction.ordinal()], newLocation, direction));
                    distances[newLocation.row() - 1][newLocation.column() - 1][direction.ordinal()] = newDistance;
                    nodes.remove(new Node(distances[newLocation.row() - 1][newLocation.column() - 1][direction.ordinal()], newLocation, direction));
                }
            }
        }

        int distance = Integer.MAX_VALUE;
        for (int value : distances[B.row() - 1][B.column() - 1]) {
            distance = Math.min(distance, value);
        }

        return distance / 20;
    }


    private Weather currentWeather;

    public Weather currentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public Tile getTileAt(Location location) {
        return tiles[location.row()][location.column()];
    }
}
