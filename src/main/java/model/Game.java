package model;

import model.alive.Player;
import model.map.World;

public class Game {
    private final World world;
    private final Player[] players;
    private DateTime dateTime;
    private int turn;
    private Player currentPlayer;

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

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void thunder() {}

    public void newDay() {

    }

}
