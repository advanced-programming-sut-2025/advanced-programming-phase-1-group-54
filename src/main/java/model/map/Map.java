package model.map;

import model.enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Map {
    private final int numberOfRows;
    private final int numberOfColumns;
    private final Tile[][] tiles;

    public Map(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        tiles = new Tile[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                tiles[row][column] = new Tile();
            }
        }
    }

    public Map(int numberOfRows, int numberOfColumns, Tile[][] tiles) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.tiles = tiles;
    }

    public Tile getTileAt(Location location) {
        if (location == null ||
                location.row() < 0 || location.row() >= numberOfRows
                || location.column() < 0 || location.column() >= numberOfColumns)
            return null;
        return tiles[location.row()][location.column()];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][][] getDistancesFrom(Location A) {
        if (getTileAt(A) == null)
            return null;

        int[][][] distances = new int[getNumberOfRows()][getNumberOfColumns()][Direction.values().length];
        for (int[][] row : distances) {
            for (int[] cell : row) {
                Arrays.fill(cell, Integer.MAX_VALUE);
            }
        }

        for (Direction direction : Direction.values()) {
            distances[A.row()][A.column()][direction.ordinal()] = 0;
        }

        TreeSet<Node> nodes = new TreeSet<>();
        for (Direction direction : Direction.values())
            nodes.add(new Node(0, A, direction));

        while (!nodes.isEmpty()) {
            Node node = nodes.pollFirst();

            for (Direction direction : Direction.values()) {
                Location newLocation = node.location().getLocationAt(direction);

                if (this.getTileAt(newLocation) == null || !(this.getTileAt(newLocation).isWalkable()))
                    continue;

                int newDistance = node.distance() + 1 + (direction != node.direction() ? 10 : 0);

                if (newDistance < distances[newLocation.row()][newLocation.column()][direction.ordinal()]) {
                    nodes.remove(new Node(distances[newLocation.row()][newLocation.column()][direction.ordinal()], newLocation, direction));
                    distances[newLocation.row()][newLocation.column()][direction.ordinal()] = newDistance;
                    nodes.add(new Node(distances[newLocation.row()][newLocation.column()][direction.ordinal()], newLocation, direction));
                }
            }
        }

        return distances;
    }

    public ArrayList<Direction> getShortestPath(Location A, Location B) {
        if (getTileAt(A) == null || getTileAt(B) == null)
            return null;

        int[][][] distances = getDistancesFrom(A);

        Direction lastDirection = null;
        int distance = Integer.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            if (distances[B.row()][B.column()][direction.ordinal()] < distance) {
                distance = distances[B.row()][B.column()][direction.ordinal()];
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

            if (getTileAt(lastLocation) == null)
                continue;

            for (Direction direction : Direction.values()) {
                if (distance == distances[lastLocation.row()][lastLocation.column()][direction.ordinal()] + 1 +
                        (lastDirection != direction ? 10 : 0)) {
                    distance = distances[lastLocation.row()][lastLocation.column()][direction.ordinal()];
                    lastDirection = direction;
                }
            }
        }

        return new ArrayList<>(shortestPath.reversed());
    }

    public int getDistance(Location A, Location B) {
        if (getTileAt(A) == null || getTileAt(B) == null)
            return Integer.MAX_VALUE;

        int[][][] distances = getDistancesFrom(A);

        int distance = Integer.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            if (distances[B.row()][B.column()][direction.ordinal()] < distance)
                distance = distances[B.row()][B.column()][direction.ordinal()];
        }

        return distance;
    }

    public Location getRandomLocation() {
        return new Location((int) (Math.random() * getNumberOfRows()), (int) (Math.random() * getNumberOfColumns()));
    }
}
