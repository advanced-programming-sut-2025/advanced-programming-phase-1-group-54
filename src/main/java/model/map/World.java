package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.enums.Weather;

public class World implements Map {
    private static final int NUMBER_OF_ROWS = 100;
    private static final int NUMBER_OF_COLUMNS = 100;

    private final Tile[][] tiles = new Tile[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

    private Weather currentWeather;

    public Weather currentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public Tile getTileAt(Location location) {
        return tiles[location.row()][location.column()];
    }
}
