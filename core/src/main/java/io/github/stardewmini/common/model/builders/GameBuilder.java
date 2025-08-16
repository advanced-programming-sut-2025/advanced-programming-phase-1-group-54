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

        DateTime dateTime = new DateTime();

        Random rng = new Random(seed);


        Farm[] playerFarms = new Farm[users.size()];


        for (int i = 0; i < users.size(); i++) {
            FarmBuilder.getInstance().reset();

            FarmBuilder.getInstance().setLocation(WorldBuilder.getFarmLocation(i));

            FarmBuilder.getInstance().setFarmNumber(playerFarmNumbers.get(i));

            FarmBuilder.getInstance().setDateTime(dateTime);

            FarmBuilder.getInstance().setRng(rng);

            playerFarms[i] = FarmBuilder.getInstance().getResult();

        }


        WorldBuilder.getInstance().reset();
        WorldBuilder.getInstance().setPlayerFarms(playerFarms);
        WorldBuilder.getInstance().setDateTime(dateTime);
        WorldBuilder.getInstance().setRng(rng);
        World world = WorldBuilder.getInstance().getResult();

        System.out.println(users.size());


        Player[] players = new Player[users.size()];
        for (int i = 0; i < users.size(); i++) {
            ArrayList<NPCFriendship> npcFriendships = new ArrayList<>();
            for (NPC npc : world.getNpcs()) {
                NPCFriendship npcFriendship = new NPCFriendship(npc);
                npcFriendships.add(npcFriendship);
                dateTime.addDailyUpdateListener(npcFriendship);
            }

            players[i] = new Player(users.get(i), playerFarms[i], npcFriendships);


            dateTime.addDailyUpdateListener(players[i]);
            dateTime.addHourUpdateListener(players[i]);

            Cabin cabin = playerFarms[i].getCabin();


            Location locationInCabin = new Location(0, 0);
/*            do {
                locationInCabin = cabin.getRandomLocation();
                System.out.println(locationInCabin.row());
                System.out.println(locationInCabin.column());
            } while (cabin.getTileAt(locationInCabin).getThingOnTile() == null);*/


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
