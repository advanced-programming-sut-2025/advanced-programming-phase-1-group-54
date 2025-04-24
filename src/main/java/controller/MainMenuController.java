package controller;

import model.App;
import model.Result;

public class MainMenuController {
    public static Result logout() {
        App.setLoggedInUser(null);
        return new Result(true, "Logged out.");
    }
}
