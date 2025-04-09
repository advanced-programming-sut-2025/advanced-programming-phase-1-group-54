package model.enums;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0,1),
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
}
