package controller;

import controller.builders.FarmBuilder;
import controller.builders.GameBuilder;
import model.*;

public class GameMenuController {
    public static Result selectNewGameUsers(String[] usernames) {
        User loggedInUser = App.getLoggedInUser();
        User[] users = new User[usernames.length];

        for (int i = 0; i < usernames.length; i++) {
            users[i] = App.getUserByUsername(usernames[i]);
            if (users[i] == null)
                return new Result(false, "User " + usernames[i] + " not found");
        }

        if (users.length == 0)
            return new Result(false, "Please enter at least one user to play with");

        if (users.length > 3)
            return new Result(false, "You can enter at most three users to play with");

        if (loggedInUser.isInGame())
            return new Result(false, "You are already in a game, you can't enter any other games.");

        for (int i = 0; i < users.length; i++) {
            if (users[i].isInGame())
                return new Result(false, "User " + usernames[i] + " is already in a game. you can't play with them");

            if (users[i].equals(loggedInUser))
                return new Result(false, "Don't play with yourself.");
        }

        User[] players = new User[users.length + 1];
        players[0] = loggedInUser;
        System.arraycopy(users, 0, players, 1, users.length);

        GameBuilder.reset();
        GameBuilder.setUsers(players);

        return new Result(true, "Players registered.");
    }

    public static Result chooseNewGameMap(int number) {
        if (number < 1 || number > FarmBuilder.getNumberOfFarms())
            return new Result(false, "Map number must be between 1 and " + FarmBuilder.getNumberOfFarms());

        boolean isFinished = GameBuilder.setNextPlayerFarm(number);
        return new Result((isFinished? 0 : 1), "Map number " + number + " chosen");
    }

    public static Result createNewGame() {
        GameData gameData = GameBuilder.getGameData();
        App.addGameData(gameData);
        return new Result(true, "Successfully created game!");
    }

    public static Result loadGame() {
        User loggedInUser = App.getLoggedInUser();
        GameData gameData = App.getGameDataOf(loggedInUser);

        if (gameData == null)
            return new Result(false, "You are not in any game! you must first create a new game!");

        GameBuilder.reset();
        GameBuilder.setGameData(gameData);
        Game game = GameBuilder.getResult();
        App.setCurrentGame(game);
        return new Result(true, "Loading... Done!");
    }
}
