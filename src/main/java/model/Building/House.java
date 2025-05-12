package model.Building;

import model.map.Location;
import model.map.Map;

public class House extends Building {
    public House(Location upperLeftLocation, Location lowerRightLocation) {
        super(upperLeftLocation, new Map(lowerRightLocation.row() - upperLeftLocation.row(),
                lowerRightLocation.column() - upperLeftLocation.column()));
    }
}
