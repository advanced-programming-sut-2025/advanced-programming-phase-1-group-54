package model;

import controller.Game.NpcController;
import model.lives.Player;
import model.enums.SubMenu;
import model.enums.Weather;
import model.map.World;
import model.relationships.Relationship;

import java.util.ArrayList;

public class Game implements DailyUpdate, HourUpdate {
    private SubMenu subMenu = SubMenu.DEFAULT;

    private final World world;
    private final Player[] players;

    private final DateTime dateTime = new DateTime();
    private int turn;

    private int votes;
    private int deleteVotes;

    private final ArrayList<Relationship> relationships;

    public Game(World world, Player[] players) {
        this.world = world;
        this.players = players;
        this.relationships = new ArrayList<>();

        for (int i = 0; i < players.length; i++) {
            for (int j = i + 1; j < players.length; j++) {
                relationships.add(new Relationship(players[i], players[j]));
            }
        }
    }

    public int getDeleteVotes() {
        return deleteVotes;
    }

    public void setDeleteVotes(int deleteVotes) {
        this.deleteVotes = deleteVotes;
    }

    public void increaseDeleteVotes() {
        this.deleteVotes++;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void increaseVotes() {
        this.votes++;
    }


    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public ArrayList<Relationship> getRelationshipsOf(Player player) {
        ArrayList<Relationship> relationshipsOfPlayer = new ArrayList<>();
        for (Relationship relationship : relationships) {
            if (relationship.getPlayer1().equals(player) || relationship.getPlayer2().equals(player))
                relationshipsOfPlayer.add(relationship);
        }

        return relationshipsOfPlayer;
    }

    public Relationship getRelationship(Player player1, Player player2) {
        for (Relationship relationship : relationships) {
            if ((relationship.getPlayer1().equals(player1) && relationship.getPlayer2().equals(player2))
                    || (relationship.getPlayer1().equals(player2) && relationship.getPlayer2().equals(player1)))
                return relationship;
        }

        return null;
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
        world.foraging(dateTime.getSeason());

        for (Player player : players) {
            player.nextDayUpdate();
        }

        for (Relationship relationship : relationships) {
            relationship.nextDayUpdate();
        }

        dateTime.increaseDay(1);
        NpcController.resetNpcEveryDay();
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
            if (player.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }
}
