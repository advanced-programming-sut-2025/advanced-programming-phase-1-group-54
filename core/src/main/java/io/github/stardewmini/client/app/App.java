package io.github.stardewmini.client.app;

import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.common.model.lives.Player;

import java.io.File;

public class App { /* save local copies here */
    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/client/");

    private static Game currentGame;
    private static User loggedInUser;

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Player getCurrentPlayer() {
        return App.getCurrentGame().getPlayerByUsername(loggedInUser.getUsername());
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }
}
