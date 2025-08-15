package io.github.stardewmini.common.model.map;

import io.github.stardewmini.common.model.enums.Direction;

public record Location(int row, int column) {
    public Location getLocationAt(Direction direction) {
        return new Location(row + direction.dRow(), column + direction.dColumn());
    }

    public Location delta(Location that) {
        return new Location(this.row - that.row(), this.column - that.column());
    }

    public double distance(Location that) {
        return Math.sqrt((this.column - that.column) * (this.column - that.column) +
            (this.row - that.row) * (this.row - that.row));
    }

    public Location add(Location that) {
        return new Location(this.row + that.row(), this.column + that.column());
    }
}
