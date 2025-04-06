package controller;

import model.Result;

public class LoginMenuController implements CheckInputValid {

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
