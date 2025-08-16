package io.github.stardewmini.common.model.builders;

import io.github.stardewmini.client.app.App;
import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Cabin;
import io.github.stardewmini.common.model.map.Farm;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.World;
import io.github.stardewmini.common.model.relationships.NPCFriendship;

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

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Integer> playerFarmNumbers = new ArrayList<>();
    private long seed;

    public void reset() {
        users = new ArrayList<>();
        playerFarmNumbers = new ArrayList<>();
        seed = 0;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public void addUserFarm(User user, int number) {
        users.add(user);
        playerFarmNumbers.add(number);
    }

    public Game getResult() {
        System.out.println("OK KIR 11");
        DateTime dateTime = new DateTime();
        System.out.println("OK KIR 12");
        Random rng = new Random(seed);
        System.out.println("OK KIR 13");

        Farm[] playerFarms = new Farm[users.size()];
        System.out.println("OK KIR 14");


        for (int i = 0; i < users.size(); i++) {
            FarmBuilder.getInstance().reset();
            System.out.println("OK KIR 15");
            FarmBuilder.getInstance().setLocation(WorldBuilder.getFarmLocation(i));
            System.out.println("OK KIR 16");
            FarmBuilder.getInstance().setFarmNumber(playerFarmNumbers.get(i));
            System.out.println("OK KIR 17");
            FarmBuilder.getInstance().setDateTime(dateTime);
            System.out.println("OK KIR 18");
            FarmBuilder.getInstance().setRng(rng);
            System.out.println("OK KIR 19");
            playerFarms[i] = FarmBuilder.getInstance().getResult();
            System.out.println("OK KIR AKHAR");
        }

        System.out.println("OK KIR 100");


        WorldBuilder.getInstance().reset();
        WorldBuilder.getInstance().setPlayerFarms(playerFarms);
        WorldBuilder.getInstance().setDateTime(dateTime);
        WorldBuilder.getInstance().setRng(rng);
        World world = WorldBuilder.getInstance().getResult();

        System.out.println("OK KIR 102");


        Player[] players = new Player[users.size()];
        for (int i = 0; i < users.size(); i++) {
            ArrayList<NPCFriendship> npcFriendships = new ArrayList<>();
            for (NPC npc : world.getNpcs()) {
                NPCFriendship npcFriendship = new NPCFriendship(npc);
                npcFriendships.add(npcFriendship);
                dateTime.addDailyUpdateListener(npcFriendship);
            }

            players[i] = new Player(users.get(i), playerFarms[i], npcFriendships);

            System.out.println("OK KIR 103");

            dateTime.addDailyUpdateListener(players[i]);
            dateTime.addHourUpdateListener(players[i]);
            System.out.println("OK KIR 103/1");
            Cabin cabin = playerFarms[i].getCabin();
            System.out.println("OK KIR 103/12");

            Location locationInCabin = new Location(0, 0);
/*            do {
                locationInCabin = cabin.getRandomLocation();
                System.out.println(locationInCabin.row());
                System.out.println(locationInCabin.column());
            } while (cabin.getTileAt(locationInCabin).getThingOnTile() == null);*/

            System.out.println("OK KIR 103/2");

            Location location = new Location(
                    playerFarms[i].getLocation().row() + cabin.getLocation().row() + locationInCabin.row(),
                    playerFarms[i].getLocation().column() + cabin.getLocation().column() + locationInCabin.column()
            );

            System.out.println("OK KIR 104");

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
        String[] playerNames = new String[users.size()];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = users.get(i).getUsername();
        }

        int[] playerFarms = new int[playerFarmNumbers.size()];
        for (int i = 0; i < playerFarms.length; i++) {
            playerFarms[i] = playerFarmNumbers.get(i);
        }

        return new GameData(playerNames, playerFarms, seed);
    }

    public void setGameData(GameData gameData) {
        this.reset();

        users.clear();
        users = new ArrayList<>();
        for (int i = 0; i < gameData.playerNames().length; i++) {
            users.add(App.getUserByUsername(gameData.playerNames()[i]));
        }

        playerFarmNumbers.clear();
        playerFarmNumbers = new ArrayList<>();
        for (int i = 0; i < gameData.playerFarms().length; i++) {
            playerFarmNumbers.add(gameData.playerFarms()[i]);
        }

        this.seed = gameData.seed();
    }

    public int getNumberOfSubmits() {
        return users.size();
    }
}
