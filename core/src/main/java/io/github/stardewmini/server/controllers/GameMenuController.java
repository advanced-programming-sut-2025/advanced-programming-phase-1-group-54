package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.builders.FarmBuilder;
import io.github.stardewmini.common.model.builders.GameBuilder;

import java.util.ArrayList;

public class GameMenuController {
    public static Result selectNewGameUsers(ArrayList<String> usernames) {
        User loggedInUser = App.getLoggedInUser();
        User[] users = new User[usernames.size()];

        for (int i = 0; i < usernames.size(); i++) {
            users[i] = App.getUserByUsername(usernames.get(i));
            if (users[i] == null)
                return new Result(false, "User " + usernames.get(i) + " not found");
        }

        if (users.length == 0)
            return new Result(false, "Please enter at least one user to play with");

        if (users.length > 3)
            return new Result(false, "You can enter at most three users to play with");

        if (loggedInUser.isInGame())
            return new Result(false, "You are already in a game, you can't enter any other games.");

        for (int i = 0; i < users.length; i++) {
            if (users[i].isInGame())
                return new Result(false, "User " + usernames.get(i) + " is already in a game. you can't play with them");

            if (users[i].equals(loggedInUser))
                return new Result(false, "Don't play with yourself.");
        }

        User[] players = new User[users.length + 1];
        players[0] = loggedInUser;
        System.arraycopy(users, 0, players, 1, users.length);

        GameBuilder.getInstance().reset();
        GameBuilder.getInstance().setUsers(players);

        return new Result(true, "Players registered.");
    }

    public static Result chooseNewGameMap(int number) {
        if (number < 1 || number > FarmBuilder.getInstance().getNumberOfFarms())
            return new Result(false, "Map number must be between 1 and " + FarmBuilder.getInstance().getNumberOfFarms());

        boolean isFinished = GameBuilder.getInstance().setNextPlayerFarm(number);
        return new Result((isFinished? 1 : 0), "Map number " + number + " chosen");
    }

    public static Result createNewGame() {
        GameData gameData = GameBuilder.getInstance().getGameData();
        App.addGameData(gameData);
        return new Result(true, "Successfully created game!");
    }

    public static String getNextPlayerUsername() {
        return GameBuilder.getInstance().getNextPlayerName();
    }

    public static Result findUsername(String username) {
        User user = App.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User " + username + " not found");

        if (user.isInGame())
            return new Result(false, "User " + username + " is already in a game. you can't play with them");

        if (user.equals(App.getLoggedInUser()))
            return new Result(false, "Don't play with yourself.");

        return new Result(true, "User found!");
    }

    public static Result loadGame() {
        User loggedInUser = App.getLoggedInUser();
        GameData gameData = App.getGameDataOf(loggedInUser);

        if (gameData == null)
            return new Result(false, "You are not in any game! you must first create a new game!");

        GameBuilder.getInstance().reset();
        GameBuilder.getInstance().setGameData(gameData);
        Game game = GameBuilder.getInstance().getResult();
        App.setCurrentGame(game);
        return new Result(true, "Loading... Done!");
    }

    public static int getNumberOfFarms() {
        return FarmBuilder.getInstance().getNumberOfFarms();
    }

    public static void reset() {
        GameBuilder.getInstance().reset();
    }
}
