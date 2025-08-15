package io.github.stardewmini.common.model.builders;

import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Cabin;
import io.github.stardewmini.common.model.map.Farm;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.World;
import io.github.stardewmini.common.model.relationships.NPCFriendship;
import io.github.stardewmini.server.app.App;

import java.util.ArrayList;
import java.util.Random;

public class GameBuilder {
    private static GameBuilder instance;

    private GameBuilder() {
    }

    public static GameBuilder getInstance() {
        if (instance == null)
            instance = new GameBuilder();
        return instance;
    }

    private User[] users;
    private int[] playerFarmNumbers;
    private int seed;
    private Random rng;

    public void reset() {
        users = null;
        playerFarmNumbers = null;
        seed = 0;
        rng = null;
    }

    public void setUsers(User[] users) {
        this.users = new User[users.length];
        System.arraycopy(users, 0, this.users, 0, users.length);
        playerFarmNumbers = new int[users.length];
    }

    public boolean setNextPlayerFarm(int number) {
        for (int i = 0; i < users.length; i++) {
            if (playerFarmNumbers[i] == 0) {
                playerFarmNumbers[i] = number;
                return (i == users.length - 1);
            }
        }
        return true;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getNextPlayerName() {
        for (int i = 0; i < users.length; i++) {
            if (playerFarmNumbers[i] == 0) {
                return users[i].getUsername();
            }
        }
        return null;
    }

    public Game getResult() {
        DateTime dateTime = new DateTime();
        Random rng = new Random(seed);

        Farm[] playerFarms = new Farm[users.length];

        for (int i = 0; i < users.length; i++) {
            FarmBuilder.getInstance().reset();
            FarmBuilder.getInstance().setLocation(WorldBuilder.getFarmLocation(i));
            FarmBuilder.getInstance().setFarmNumber(playerFarmNumbers[i]);
            FarmBuilder.getInstance().setDateTime(dateTime);
            FarmBuilder.getInstance().setRng(rng);
            playerFarms[i] = FarmBuilder.getInstance().getResult();
        }

        WorldBuilder.getInstance().reset();
        WorldBuilder.getInstance().setPlayerFarms(playerFarms);
        WorldBuilder.getInstance().setDateTime(dateTime);
        WorldBuilder.getInstance().setRng(rng);
        World world = WorldBuilder.getInstance().getResult();

        Player[] players = new Player[users.length];
        for (int i = 0; i < users.length; i++) {
            ArrayList<NPCFriendship> npcFriendships = new ArrayList<>();
            for (NPC npc : world.getNpcs()) {
                NPCFriendship npcFriendship = new NPCFriendship(npc);
                npcFriendships.add(npcFriendship);
                dateTime.addDailyUpdateListener(npcFriendship);
            }

            players[i] = new Player(users[i], playerFarms[i], npcFriendships);


            dateTime.addDailyUpdateListener(players[i]);
            dateTime.addHourUpdateListener(players[i]);

            Cabin cabin = playerFarms[i].getCabin();

            Location locationInCabin;
            do {
                locationInCabin = cabin.getRandomLocation();
            } while (cabin.getTileAt(locationInCabin).getThingOnTile() == null);

            Location location = new Location(
                    playerFarms[i].getLocation().row() + cabin.getLocation().row() + locationInCabin.row(),
                    playerFarms[i].getLocation().column() + cabin.getLocation().column() + locationInCabin.column()
            );

            cabin.getTileAt(locationInCabin).setThingOnTile(players[i]);
            players[i].setCurrentLocation(location);
            players[i].setCabinLocation(location);
        }

        Game game = new Game(dateTime, seed, rng, world, players);
        dateTime.addDailyUpdateListener(game);

        this.reset();
        return game;
    }

    public GameData getGameData() {
        String[] playerNames = new String[users.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = users[i].getUsername();
        }

        int[] playerFarms = new int[playerFarmNumbers.length];
        System.arraycopy(playerFarmNumbers, 0, playerFarms, 0, playerFarmNumbers.length);

        return new GameData(playerNames, playerFarms, seed);
    }

    public void setGameData(GameData gameData) {
        this.reset();

        users = new User[gameData.playerNames().length];
        for (int i = 0; i < gameData.playerNames().length; i++) {
            users[i] = App.getUserByUsername(gameData.playerNames()[i]);
        }

        playerFarmNumbers = new int[gameData.playerFarms().length];
        System.arraycopy(gameData.playerFarms(), 0, playerFarmNumbers, 0, gameData.playerNames().length);
    }
}
