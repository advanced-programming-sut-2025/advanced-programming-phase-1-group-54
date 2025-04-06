package controller;

import model.Result;

public class LoginMenuController implements MenuController, CheckInputValid {
    @Override
    public Result enterMenu(String menuName) {
        return null;
    }

    @Override
    public Result exitMenu() {
        return null;
    }

    @Override
    public Result showCurrentMenu() {
        return null;
    }

    public Result login(String username, String password, boolean stayLoggedIn) {
        return new Result(true, null);
    }

    public String getSecurityQuestion() {
        return null;
    }

    public Result answer(String answer) {
        return null;
    }

}
