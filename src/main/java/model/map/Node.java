package model.map;

import model.enums.Direction;

public record Node(int distance, Location location, Direction direction) implements Comparable<Node> {
    @Override
    public int compareTo(Node that) {
        return this.distance - that.distance;
    }
}
