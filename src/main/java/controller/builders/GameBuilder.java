package controller.builders;

import model.Game;
import model.User;
import model.alive.Player;
import model.enums.Symbol;
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
            FarmBuilder.setFarmNumber(playerFarmNumbers[i]);
            playerFarms[i] = FarmBuilder.getResult();
        }

        WorldBuilder.reset();
        WorldBuilder.setPlayerFarms(playerFarms);
        World world = WorldBuilder.getResult();

        Player[] players = new Player[users.length];
        for (int i = 0; i < users.length; i++) {
            players[i] = new Player(users[i]);

            Location location;
            do {
                location = new Location((int) (Math.random() * Farm.getNumberOfRows()) + 1,
                        (int) (Math.random() * Farm.getNumberOfColumns()) + 1);
            } while (!playerFarms[i].getTileAt(location).isWalkable());

            location = new Location(location.row() + WorldBuilder.farmLocation[i].row() - 1, location.column() + WorldBuilder.farmLocation[i].column());
            world.getTileAt(location).setThingOnTile(players[i]);
            world.getTileAt(location).setSymbol(Symbol.PLAYER);
        }

        Game game = new Game(world, players);
        GameBuilder.reset();
        return game;
    }
}
