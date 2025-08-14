package io.github.stardewmini.client.controllers;

import io.github.stardewmini.client.app.GameApp;
import io.github.stardewmini.common.model.Result;

public class GeneralController {
    public static Result logout() {
        GameApp.setLoggedInUser(null);
        return new Result(true, "Logged Out!");
    }
}
