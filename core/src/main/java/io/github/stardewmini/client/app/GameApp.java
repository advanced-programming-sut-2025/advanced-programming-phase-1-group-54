package io.github.stardewmini.client.app;

import com.google.gson.Gson;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.common.model.lives.Player;

import java.io.File;

public class GameApp { /* save local copies here */
    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/client/");

    private static Game currentGame;
    private static User loggedInUser;

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        GameApp.currentGame = currentGame;
    }

    public static Player getCurrentPlayer() {
        return GameApp.getCurrentGame().getPlayerByUsername(loggedInUser.getUsername());
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        GameApp.loggedInUser = loggedInUser;
    }
}
