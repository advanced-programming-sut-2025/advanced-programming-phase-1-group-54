package model;

import model.Shops.Shop;
import model.alive.Player;
import model.enums.SubMenu;
import model.enums.Weather;
import model.map.World;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;

public class Game implements DailyUpdate {
    private SubMenu subMenu = SubMenu.DEFAULT;

    private final World world;
    private final Player[] players;

    private DateTime dateTime;
    private int turn;

    private ArrayList<Shop> npcShops;


    private Weather currentWeather;
    private ArrayList<PlayerRelationship> playerRelationships;
    private Weather tomorrowWeather;

    public Game(World world, Player[] players) {
        this.world = world;
        this.players = players;
        this.playerRelationships = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            for (int j = i+1; j < players.length; j++) {
                playerRelationships.add(new PlayerRelationship(players[i], players[j]));
            }
        }
    }
    public ArrayList<PlayerRelationship> getPlayerRelationships() {
        return playerRelationships;
    }
    public void setPlayerRelationships(ArrayList<PlayerRelationship> playerRelationships) {
        this.playerRelationships = playerRelationships;
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

    public ArrayList<Shop> getNpcShops() {
        return npcShops;
    }

    public void setNpcShops(ArrayList<Shop> npcShops) {
        this.npcShops = npcShops;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return players[turn];
    }

    public void thunder() {}

    @Override
    public void nextDayUpdate() {
        currentWeather = tomorrowWeather;
        tomorrowWeather = Weather.getRandom(dateTime.getSeason());

        for (Player player : players) {
            player.nextDayUpdate();
        }
        dateTime.increaseDay(1);
    }

    public void nextTurn() {
        turn++;
        if (turn >= players.length) {
            turn = 0;
        }
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
    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if(player.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }
}
