package model;

import model.alive.Player;
import model.map.World;

public class Game {
    private final World world;
    private final Player[] players;
    private DateTime dateTime;

    public Game(World world, Player[] players) {
        this.world = world;
        this.players = players;
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

    public void thunder() {}

    public void newDay() {

    }

}
