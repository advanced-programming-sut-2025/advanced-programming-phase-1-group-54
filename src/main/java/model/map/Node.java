package model.map;

import model.enums.Direction;

public record Node(int distance, Location location, Direction direction) implements Comparable<Node> {
    @Override
    public int compareTo(Node that) {
        if (this.distance != that.distance)
            return this.distance - that.distance;

        if (this.location.row() != that.location.row())
            return this.location.row() - that.location.row();

        if (this.location.column() != that.location.column())
            return this.location.column() - that.location.column();

        return this.direction.ordinal() - that.direction.ordinal();
    }
}
