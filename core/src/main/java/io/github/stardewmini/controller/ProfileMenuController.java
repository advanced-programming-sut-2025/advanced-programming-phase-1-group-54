package io.github.stardewmini.controller;

import io.github.stardewmini.common.model.builders.UserBuilder;
import io.github.stardewmini.common.model.App;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;

public class ProfileMenuController {

    public static Result changeUsername(String username) {
        User currentUser = App.getLoggedInUser();

        if (username.equals(currentUser.getUsername()))
            return new Result(false, "Please enter a new username");

        Result checkUsernameResult = RegisterMenuController.checkUsername(username);
        if (!checkUsernameResult.success())
            return checkUsernameResult;

        currentUser.setUsername(username);
        App.saveUsers();
        return new Result(true, "Username changed successfully.");
    }

    public static Result changePassword(String newPassword, String oldPassword) {
        User currentUser = App.getLoggedInUser();
        if (!UserBuilder.sha256(oldPassword).equals(currentUser.getPasswordHash()))
            return new Result(false, "Old Password does not match");

        if (UserBuilder.sha256(newPassword).equals(currentUser.getPasswordHash()))
            return new Result(false, "Please enter a new password");

        Result checkPasswordResult = RegisterMenuController.checkPassword(newPassword, newPassword);
        if (!checkPasswordResult.success())
            return checkPasswordResult;

        currentUser.setPasswordHash(UserBuilder.sha256(newPassword));
        App.saveUsers();
        return new Result(true, "Password changed successfully.");
    }

    public static Result changeNickname(String nickname) {
        User currentUser = App.getLoggedInUser();
        if (nickname.equals(currentUser.getNickname()))
            return new Result(false, "Please enter a new nickname");

        currentUser.setNickname(nickname);
        App.saveUsers();
        return new Result(true, "Nickname changed successfully.");
    }

    public static Result changeEmail(String email) {
        User currentUser = App.getLoggedInUser();
        if (email.equals(currentUser.getEmail()))
            return new Result(false, "Please enter a new email address");

        Result checkEmailResult = RegisterMenuController.checkEmail(email);
        if (!checkEmailResult.success())
            return checkEmailResult;

        currentUser.setEmail(email);
        App.saveUsers();
        return new Result(true, "Email changed successfully.");
    }

    public static Result showUserInfo() {
        User currentUser = App.getLoggedInUser();
        String message = "User Info: \n" + "Username: " + currentUser.getUsername() + "\n";
        message += "Nickname: " + currentUser.getNickname() + "\n";
        message += "Email: " + currentUser.getEmail() + "\n";
        message += "Gender: " + currentUser.getGender() + "\n";
        message += "Number of Games: " + currentUser.getNumberOfPlayedGames() + "\n";
        message += "Max. Money: " + currentUser.getMaximumMoney();

        return new Result(true, message);
    }
}
