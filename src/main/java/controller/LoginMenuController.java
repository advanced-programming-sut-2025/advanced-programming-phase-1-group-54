package controller;

import controller.builders.UserBuilder;
import model.App;
import model.Result;
import model.User;

public class LoginMenuController {
    public static Result login(String username, String password, boolean stayLoggedIn) {
        User user = App.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (!UserBuilder.hash(password).equals(user.getPasswordHash()))
            return new Result(false, "Incorrect password");

        App.setLoggedInUser(user);
        if (stayLoggedIn)
            App.saveLoggedInUser();
        return new Result(true, "Successfully logged in");
    }

    public static String getSecurityQuestion(String username) {
        User user = App.getUserByUsername(username);
        if (user != null)
            return user.getSecurityQuestion();
        return null;
    }

    public static Result answer(String username, String answer) {
        User user = App.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (!UserBuilder.hash(answer).equals(user.getAnswerHash()))
            return new Result(false, "Incorrect answer");

        return new Result(true, "answer correct");
    }

    public static Result changePassword(String username, String password, String confirmPassword) {
        User user = App.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        if (password.isEmpty() && confirmPassword.isEmpty())
            return new Result(false, "You must enter a new password");

        Result checkPasswordResult = RegisterMenuController.checkPassword(password, confirmPassword);
        if (!checkPasswordResult.success())
            return checkPasswordResult;

        user.setPasswordHash(UserBuilder.hash(password));
        return new Result(true, "Password changed successfully");
    }

    public static Result changePasswordToRandom(String username) {
        User user = App.getUserByUsername(username);
        if (user == null)
            return new Result(false, "User not found");

        String password = RegisterMenuController.getRandomPassword();
        user.setPasswordHash(UserBuilder.hash(password));
        return new Result(true, "Password changed successfully. your new password is: " + password);
    }

}
