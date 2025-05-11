package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class World implements Map {
    private static final int NUMBER_OF_ROWS = 52;
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

    public int[][][] getDistancesFrom(Location A) {
        int[][][] distances = new int[getNumberOfRows()][getNumberOfColumns()][Direction.values().length];
        for (int[][] row : distances) {
            for (int[] cell : row) {
                Arrays.fill(cell, Integer.MAX_VALUE);
            }
        }

        for (Direction direction : Direction.values()) {
            distances[A.row() - 1][A.column() - 1][direction.ordinal()] = 0;
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
                    nodes.add(new Node(distances[newLocation.row() - 1][newLocation.column() - 1][direction.ordinal()], newLocation, direction));
                }
            }
        }

        return distances;
    }

    public ArrayList<Direction> getShortestPath(Location A, Location B) {
        int[][][] distances = getDistancesFrom(A);

        Direction lastDirection = null;
        int distance = Integer.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            if (distances[B.row() - 1][B.column() - 1][direction.ordinal()] < distance) {
                distance = distances[B.row() - 1][B.column()][direction.ordinal()];
                lastDirection = direction;
            }
        }

        if (distance == Integer.MAX_VALUE)
            return null;

        ArrayList<Direction> shortestPath = new ArrayList<>();
        Location lastLocation = B;

        while (lastLocation != A) {
            shortestPath.add(lastDirection);
            lastLocation = lastLocation.getLocationAt(lastDirection.opposite());

            for (Direction direction : Direction.values()) {
                if (distance == distances[lastLocation.row() - 1][lastLocation.column() - 1][direction.ordinal()] + 1 +
                        (lastDirection != direction ? 10 : 0)) {
                    distance = distances[lastLocation.row() - 1][lastLocation.column() - 1][direction.ordinal()];
                    lastDirection = direction;
                }
            }
        }

        return new ArrayList<>(shortestPath.reversed());
    }

    public int getDistance(Location A, Location B) {
        int[][][] distances = getDistancesFrom(A);

        int distance = Integer.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            if (distances[B.row() - 1][B.column() - 1][direction.ordinal()] < distance)
                distance = distances[B.row() - 1][B.column()][direction.ordinal()];
        }

        return distance;
    }

    @Override
    public Tile getTileAt(Location location) {
        return tiles[location.row() - 1][location.column() - 1];
    }
}
