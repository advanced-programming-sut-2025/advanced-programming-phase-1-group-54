package model;

import model.alive.Player;
import model.enums.SubMenu;
import model.enums.Weather;
import model.map.World;

public class Game {
    private SubMenu subMenu = SubMenu.DEFAULT;

    private final World world;
    private final Player[] players;
    private DateTime dateTime;
    private int turn;
    private Player currentPlayer;

    private Weather currentWeather;
    private Weather tommorrowWeather;

    public Game(World world, Player[] players) {
        this.world = world;
        this.players = players;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public World getWorld() {
        return world;
    }

    public Player[] getPlayers() {
        return players;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void thunder() {}

    public void newDay() {

    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Weather getTomorrowWeather() {
        return tommorrowWeather;
    }

}
