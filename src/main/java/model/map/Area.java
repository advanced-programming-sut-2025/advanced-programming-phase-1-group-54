package model.map;

public record Area(Location upperLeftLocation, Location lowerRightLocation) {
    int numberOfRows() {
        return lowerRightLocation.row() - upperLeftLocation.row() + 1;
    }

    int numberOfColumns() {
        return lowerRightLocation.column() - upperLeftLocation.column() + 1;
    }
}
