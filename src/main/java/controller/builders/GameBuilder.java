package controller.builders;

import controller.Game.PlantsController;
import model.App;
import model.map.Cabin;
import model.Game;
import model.User;
import model.alive.Player;
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

    public static int getNumberOfPlayers() {
        return GameBuilder.users.length;
    }

    public static void setNextPlayerFarm(int number) {
        for (int i = 0; i < users.length; i++) {
            if (playerFarmNumbers[i] == 0) {
                playerFarmNumbers[i] = number;
                break;
            }
        }
    }

    public static void setPlayerFarmNumbers(int[] numbers) {
        playerFarmNumbers = numbers;
    }

    public static Game getResult() {
        Farm[] playerFarms = new Farm[users.length];

        for (int i = 0; i < users.length; i++) {
            FarmBuilder.reset();
            FarmBuilder.setLocation(WorldBuilder.farmLocation[i]);
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

            Location locationInCabin = new Location(
                    Cabin.getNumberOfRows() / 2,
                    Cabin.getNumberOfColumns() / 2);

            Location location = new Location(
                    playerFarms[i].getLocation().row() + cabin.getLocation().row() + locationInCabin.row(),
                    playerFarms[i].getLocation().column() + cabin.getLocation().column() + locationInCabin.column()
            );

            cabin.getTileAt(locationInCabin).setThingOnTile(players[i]);
            players[i].setCurrentLocation(location);
        }

        Game game = new Game(world, players);

        App.setCurrentGame(game);

        for (Player player : game.getPlayers()) {
            for (int t = 0; t < 10; t++) {
                PlantsController.foragingCrop(player);
            }
            for (int t = 0; t < 2; t++) {
                PlantsController.foragingMaterial(player);
            }
        }

        App.setCurrentGame(null);

        GameBuilder.reset();
        return game;
    }
}
