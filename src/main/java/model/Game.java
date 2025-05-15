package model;

import controller.Game.CommonGameController;
import model.lives.Player;
import model.enums.SubMenu;
import model.enums.Weather;
import model.map.World;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;

public class Game implements DailyUpdate, HourUpdate {
    private SubMenu subMenu = SubMenu.DEFAULT;

    private final World world;
    private final Player[] players;

    private final DateTime dateTime = new DateTime();
    private int turn;

    private final ArrayList<PlayerRelationship> playerRelationships;

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

    public Player getCurrentPlayer() {
        return players[turn];
    }

    @Override
    public void nextDayUpdate() {
        world.nextDayUpdate();
        world.setTomorrowWeather(Weather.getRandom(dateTime.getSeason()));

        for (Player player : players) {
            player.nextDayUpdate();
        }

        for (PlayerRelationship relationship : playerRelationships) {
            relationship.reset();
        }

        dateTime.increaseDay(1);

        CommonGameController.nextDayMoney();
        //TODO in every turn check the gifts trades etc
        //TODO fill the shops
    }

    @Override
    public void nextHourUpdate() {
        world.nextHourUpdate();

        for (Player player : players) {
            player.nextHourUpdate();
        }

        dateTime.increaseHour(1);
        if (dateTime.getHour() == DateTime.getStartHour()) {
            nextDayUpdate();
        }
    }

    public void nextTurn() {
        do {
            turn++;
            if (turn >= players.length) {
                turn = 0;
                nextHourUpdate();
            }
        } while (players[turn].isFallen());

    }

    public Weather getCurrentWeather() {
        return world.getCurrentWeather();
    }

    public Weather getTomorrowWeather() {
        return world.getTomorrowWeather();
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
