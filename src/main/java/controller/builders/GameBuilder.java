package controller.builders;

import model.App;
import model.GameData;
import model.map.Cabin;
import model.Game;
import model.User;
import model.lives.Player;
import model.map.Farm;
import model.map.Location;
import model.map.World;

public class GameBuilder {
    private static User[] users;
    private static int[] playerFarmNumbers;

    public static void reset() {
        users = null;
        playerFarmNumbers = null;
    }

    public static void setUsers(User[] users) {
        GameBuilder.users = users;
        playerFarmNumbers = new int[users.length];
    }

    public static boolean setNextPlayerFarm(int number) {
        for (int i = 0; i < users.length; i++) {
            if (playerFarmNumbers[i] == 0) {
                playerFarmNumbers[i] = number;
                return (i == users.length - 1);
            }
        }
        return false;
    }


    public static Game getResult() {
        Farm[] playerFarms = new Farm[users.length];

        for (User user : users) {
            user.setInGame(true);
        }
        App.saveUsers();

        for (int i = 0; i < users.length; i++) {
            FarmBuilder.reset();
            FarmBuilder.setLocation(WorldBuilder.getFarmLocation(i));
            FarmBuilder.setFarmNumber(playerFarmNumbers[i]);
            playerFarms[i] = FarmBuilder.getResult();
        }

        WorldBuilder.reset();
        WorldBuilder.setPlayerFarms(playerFarms);
        World world = WorldBuilder.getResult();

        Player[] players = new Player[users.length];
        for (int i = 0; i < users.length; i++) {
            players[i] = new Player(users[i], playerFarms[i]);

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
        }

        Game game = new Game(world, players);
        GameBuilder.reset();
        return game;
    }

    public static GameData getGameData() {
        String[] playerNames = new String[users.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = users[i].getUsername();
        }

        int[] playerFarms = new int[playerFarmNumbers.length];
        System.arraycopy(playerFarms, 0, playerFarmNumbers, 0, playerFarmNumbers.length);

        return new GameData(playerNames, playerFarms);
    }

    public static void setGameData(GameData gameData) {
        GameBuilder.reset();

        users = new User[gameData.playerNames().length];
        for (int i = 0; i < gameData.playerNames().length; i++) {
            users[i] = App.getUserByUsername(gameData.playerNames()[i]);
        }

        playerFarmNumbers = new int[gameData.playerFarms().length];
        System.arraycopy(playerFarmNumbers, 0, gameData.playerFarms(), 0, gameData.playerNames().length);
    }
}
