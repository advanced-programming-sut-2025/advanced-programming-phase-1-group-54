package model;

import model.map.World;

public class Game {
    private final int numberOfPlayer;
    private final World worldMap;
    private final Player[] players;
    private User mainPlayer;
    private DateTime dateTime;

    public Game(World worldMap, int numberOfPlayer, Player[] players, User mainPlayer) {
        this.worldMap = worldMap;
        this.numberOfPlayer = numberOfPlayer;
        this.players = players;
        this.mainPlayer = mainPlayer;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public World getWorldMap() {
        return worldMap;
    }

    public Player[] getPlayers() {
        return players;
    }

    public User getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(User mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void thunder() {}

    public void newDay() {

    }

}
