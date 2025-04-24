package controller.builders;

import model.Game;
import model.User;
import model.alive.Player;
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
                playerFarmNumbers[i] = number - 1;
            }
        }
    }

    public static void setPlayerFarmNumbers(int[] numbers) {
        playerFarmNumbers = numbers;
    }

    public static Game getResult() {
        World world = new World();
        Player[] players = new Player[users.length];

        // TODO
        Game game = new Game(world, players);
        GameBuilder.reset();
        return game;
    }
}
