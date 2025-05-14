package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import model.DailyUpdate;
import model.alive.Player;
import model.enums.Direction;
import model.enums.Weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class World implements DailyUpdate {
    private static final int NUMBER_OF_ROWS = 50;
    private static final int NUMBER_OF_COLUMNS = 50;
    private static final int NUMBER_OF_THUNDER = 3;

    private final Farm[] playerFarms;

    private final Map map;

    private Weather currentWeather;
    private Weather tomorrowWeather;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public World(Farm[] playerFarms, Map map) {
        this.playerFarms = playerFarms;
        this.map = map;
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public Farm getFarm(Player player) {
        return player.getFarm();
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    public void thunder() {
        for (Farm farm : playerFarms) {
            for (int i = 1; i <= NUMBER_OF_THUNDER; i++) {
                farm.thunderStrike(farm.getRandomLocation());
            }
        }
    }

    public int getDistance(Location A, Location B) {
        return map.getDistance(A, B);
    }

    public ArrayList<Direction> getShortestPath(Location A, Location B) {
        return map.getShortestPath(A, B);
    }

    @Override
    public void nextDayUpdate() {
        currentWeather = tomorrowWeather;


        for (Farm farm : playerFarms) {
            farm.nextDayUpdate();
        }

        if (currentWeather == Weather.RAIN || currentWeather == Weather.STORM) {
            for (Farm farm : playerFarms) {
                farm.rain();
            }
        }
        else {
            for (Farm farm : playerFarms) {
                farm.dry();
            }
        }

        if (currentWeather.equals(Weather.STORM)) {
            thunder();
        }
    }
}
