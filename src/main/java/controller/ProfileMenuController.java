package controller;

import controller.builders.UserBuilder;
import model.App;
import model.Result;
import model.User;

public class ProfileMenuController {
    // TODO : save changes to file

    public static Result changeUsername(String username) {
        User currentUser = App.getLoggedInUser();

        if (username.equals(currentUser.getUsername()))
            return new Result(false, "Please enter a new username");

        Result checkUsernameResult = RegisterMenuController.checkUsername(username);
        if (!checkUsernameResult.success())
            return checkUsernameResult;

        currentUser.setUsername(username);
        return new Result(true, "Username changed successfully.");
    }

    public static Result changePassword(String newPassword, String oldPassword) {
        User currentUser = App.getLoggedInUser();
        if (!UserBuilder.hash(oldPassword).equals(currentUser.getPasswordHash()))
            return new Result(false, "Old Password does not match");

        if (UserBuilder.hash(newPassword).equals(currentUser.getPasswordHash()))
            return new Result(false, "Please enter a new password");

        Result checkPasswordResult = RegisterMenuController.checkPassword(newPassword, newPassword);
        if (!checkPasswordResult.success())
            return checkPasswordResult;

        return new Result(true, "Password changed successfully.");
    }

    public static Result changeNickname(String nickname) {
        User currentUser = App.getLoggedInUser();
        if (nickname.equals(currentUser.getNickname()))
            return new Result(false, "Please enter a new nickname");

        currentUser.setNickname(nickname);
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
        return new Result(true, "Email changed successfully.");
    }

    public static Result showUserInfo() {
        // TODO
        return null;
    }
}
