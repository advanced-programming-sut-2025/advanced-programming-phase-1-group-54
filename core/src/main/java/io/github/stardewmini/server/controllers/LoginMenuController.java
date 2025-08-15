package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.model.builders.UserBuilder;
import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;

public class LoginMenuController {

    public static Result login(String username, String password, boolean stayLoggedIn) {
        User user = GameApp.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (!UserBuilder.sha256(password).equals(user.getPasswordHash()))
            return new Result(false, "Incorrect password");

        // TODO stay logged in
        return new Result(true, "Successfully logged in");
    }

    public static Result getSecurityQuestion(String username) {
        User user = GameApp.getUserByUsername(username);
        if (user != null)
            return new Result(true, user.getSecurityQuestion());
        return new Result(false, "User not found");
    }

    public static Result answer(String username, String answer) {
        User user = GameApp.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (!UserBuilder.sha256(answer).equals(user.getAnswerHash()))
            return new Result(false, "Incorrect answer");

        return new Result(true, "answer correct");
    }

    public static Result changePassword(String username, String password, String confirmPassword) {
        User user = GameApp.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (password.isEmpty() && confirmPassword.isEmpty())
            return new Result(false, "You must enter a new password");

        Result checkPasswordResult = RegisterMenuController.checkPassword(password, confirmPassword);
        if (!checkPasswordResult.success())
            return checkPasswordResult;

        user.setPasswordHash(UserBuilder.sha256(password));
        GameApp.saveUsers();
        return new Result(true, "Password changed successfully");
    }

    public static Result changePasswordToRandom(String username) {
        User user = GameApp.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        String password = RegisterMenuController.getRandomPassword();
        user.setPasswordHash(UserBuilder.sha256(password));
        GameApp.saveUsers();
        return new Result(true, "Password changed successfully. your new password is: " + password);
    }

}
