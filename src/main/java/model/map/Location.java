package model.map;

import model.enums.Direction;

public record Location(int row, int column) {
    public Location getLocationAt(Direction direction) {
        return new Location(row + direction.dRow(), column + direction.dColumn());
    }

    // TODO use this if needed
    public Location delta(Location that) {
        return new Location(this.row - that.row(), this.column - that.column());
    }
}
