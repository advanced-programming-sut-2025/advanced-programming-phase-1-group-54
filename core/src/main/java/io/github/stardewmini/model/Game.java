package io.github.stardewmini.model;

import io.github.stardewmini.model.enums.Weather;
import io.github.stardewmini.model.items.plants.Plant;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.map.World;
import io.github.stardewmini.model.relationships.Relationship;

import java.util.ArrayList;
import java.util.HashMap;

public class Game implements DailyUpdate {
    private final World world;
    private final Player[] players;

    private final DateTime dateTime;
    private int turn;

    private int votes;
    private int deleteVotes;

    private final ArrayList<Relationship> relationships;

    private final HashMap<Player,FishingGame> fishingGames;

    public Game(DateTime dateTime, World world, Player[] players) {
        this.dateTime = dateTime;
        this.world = world;
        this.players = players;
        this.relationships = new ArrayList<>();
        this.fishingGames = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            for (int j = i + 1; j < players.length; j++) {
                Relationship relationship = new Relationship(players[i], players[j]);
                relationships.add(relationship);
                dateTime.addDailyUpdateListener(relationship);
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

    public HashMap<Player, FishingGame> getFishingGames() {
        return fishingGames;
    }

    @Override
    public void nextDayUpdate() {
        world.setTomorrowWeather(Weather.getRandom(dateTime.getSeason()));
        world.foraging(dateTime.getSeason());

        for (Player player : players) {
            for (Plant plant : player.getFarm().getPlants().keySet()) {
                if (plant.isDead()) {
                    dateTime.removeDailyUpdateListener(plant);
                }
            }
        }
    }


    public void nextTurn() {
        do {
            turn++;
            if (turn >= players.length) {
                turn = 0;
                dateTime.increaseHour(1);
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
