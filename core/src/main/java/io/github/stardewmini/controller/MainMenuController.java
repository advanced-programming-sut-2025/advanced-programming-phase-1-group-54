package io.github.stardewmini.controller;

import io.github.stardewmini.common.model.App;
import io.github.stardewmini.common.model.Result;

public class MainMenuController {
    public static Result logout() {
        App.setLoggedInUser(null);
        return new Result(true, "Logged out.");
    }
}
