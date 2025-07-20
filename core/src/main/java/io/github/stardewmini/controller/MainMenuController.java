package io.github.stardewmini.controller;

import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Result;

public class MainMenuController {
    public static Result logout() {
        App.setLoggedInUser(null);
        return new Result(true, "Logged out.");
    }
}
