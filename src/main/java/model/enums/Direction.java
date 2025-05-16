package model.enums;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    DOWN_LEFT(1, -1),
    UP_RIGHT(-1, 1),
    DOWN_RIGHT(1, 1);

    private final int dRow;
    private final int dColumn;

    Direction(int dRow, int dColumn) {
        this.dRow = dRow;
        this.dColumn = dColumn;
    }

    public int dRow() {
        return dRow;
    }

    public int dColumn() {
        return dColumn;
    }

    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP_LEFT:
                return DOWN_RIGHT;
            case DOWN_LEFT:
                return UP_RIGHT;
            case UP_RIGHT:
                return DOWN_LEFT;
            case DOWN_RIGHT:
                return UP_LEFT;
        }
        return null;
    }

    public static Direction fromString(String string) {
        for (Direction direction : Direction.values()) {
            if (direction.toString().equals(string)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase().replace("_", " ");
    }
}
