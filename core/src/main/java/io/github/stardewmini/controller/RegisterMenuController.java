package io.github.stardewmini.controller;

import io.github.stardewmini.model.builders.UserBuilder;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.User;
import io.github.stardewmini.model.enums.Gender;

public class RegisterMenuController {
    public static Result register(String username, String password, String confirmPassword, String nickname,
                                  String email, Gender gender) {
        if (!isUsernameUnique(username)) {
            return new Result(true,
                String.format("Username is taken, recommended username: %s", getNewUsername(username)));
        }

        Result checkUsernameResult = checkUsername(username);
        if (!checkUsernameResult.success())
            return checkUsernameResult;

        Result checkPasswordResult = checkPassword(password, confirmPassword);
        if (!checkPasswordResult.success())
            return checkPasswordResult;

        Result checkEmailResult = checkEmail(email);
        if (!checkEmailResult.success())
            return checkEmailResult;

        UserBuilder.getInstance().reset();
        UserBuilder.getInstance().registerBasicData(username, password, nickname, email, gender);
        return new Result(true, "Successfully registered data!");
    }

    public static Result resetUserBuilder() {
        UserBuilder.getInstance().reset();
        return new Result(true, null);
    }

    static boolean isUsernameUnique(String username) {
        return App.getUserByUsername(username) == null;
    }

    static boolean isUsernameValid(String username) {
        return username.matches("[a-zA-Z0-9-]+");
    }

    static boolean isEmailValid(String email) {
        String regex = "(?!.*[?><,\"';:\\\\/|\\]\\[}{+=)(*&^%$#!])" +
            "(?!.*\\.\\..*@)[a-zA-Z0-9]([a-zA-Z0-9._-]*[a-zA-Z0-9])?@" +
            "([a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?!-)[a-zA-Z0-9-]{2,}(?<!-)";
        return email.matches(regex);
    }

    static boolean isPasswordValid(String password) {
        return password.matches("[a-zA-Z0-9?><,\"';:\\\\/|\\]\\[}{+=)(*&^%$#!]+");
    }

    static Result checkPasswordStrength(String password) {
        if (password.length() < 8)
            return new Result(false, "Password must be at least 8 characters");
        if (!password.matches(".*[a-z].*"))
            return new Result(false, "Password must contain at least one lowercase letter");
        if (!password.matches(".*[A-Z].*"))
            return new Result(false, "Password must contain at least one uppercase letter");
        if (!password.matches(".*[0-9].*"))
            return new Result(false, "Password must contain at least one digit");
        if (!password.matches(".*[?><,\"';:\\\\/|\\]\\[}{+=)(*&^%$#!].*"))
            return new Result(false, "Password must contain at least one special character");

        return new Result(true, "Password is strong");
    }

    static Result checkUsername(String username) {
        if (!isUsernameValid(username))
            return new Result(false, "Username is invalid!");
        if (!isUsernameUnique(username))
            return new Result(false, "Username is taken!");

        return new Result(true, "username ok");
    }

    static Result checkPassword(String password, String confirmPassword) {
        if (!isPasswordValid(password))
            return new Result(false, "Password is invalid!");

        Result checkPasswordStrengthResult = checkPasswordStrength(password);
        if (!checkPasswordStrengthResult.success())
            return checkPasswordStrengthResult;

        if (!password.equals(confirmPassword))
            return new Result(false, "Passwords do not match!");

        return new Result(true, "password ok");
    }

    static Result checkEmail(String email) {
        if (!isEmailValid(email))
            return new Result(false, "Email is invalid!");

        return new Result(true, "email ok");
    }

    public static String getRandomPassword() {
        String specialCharacters = "?><,\"';:\\/|][}{+=)(*&^%$#!";
        Result checkPasswordStrengthResult;
        char[] password;

        do {
            int length = (int) (Math.random() * 8) + 8;
            password = new char[length];
            for (int i = 0; i < length; i++) {
                int choice = (int) (Math.random() * 4);
                switch (choice) {
                    case 0:
                        password[i] = (char) ((int) (Math.random() * 26) + 'a');
                        break;
                    case 1:
                        password[i] = (char) ((int) (Math.random() * 26) + 'A');
                        break;
                    case 2:
                        password[i] = (char) ((int) (Math.random() * 10) + '0');
                        break;
                    case 3:
                        password[i] = specialCharacters.charAt((int) (Math.random() * specialCharacters.length()));
                        break;
                }
            }
            checkPasswordStrengthResult = checkPasswordStrength(new String(password));
        } while (!checkPasswordStrengthResult.success());

        return new String(password);
    }

    static String getNewUsername(String username) {
        String newUsername;
        String characters = "0123456789-";
        do {
            newUsername = username;
            int length = (int) (Math.random() * 3) + 1;
            for (int i = 0; i < length; i++) {
                int choice = (int) (Math.random() * characters.length());
                newUsername += characters.charAt(choice);
            }
        } while (!isUsernameUnique(newUsername));
        return newUsername;
    }

    public static String[] getSecurityQuestions() {
        return User.getSecurityQuestions();
    }

    public static Result pickQuestion(int number, String answer, String confirmAnswer) {
        if (number < 0 || number > User.getSecurityQuestions().length)
            return new Result(false, String.format("Number should be between 1 and %d",
                User.getSecurityQuestions().length));

        if (!answer.equals(confirmAnswer))
            return new Result(false, "Answers do not match");

        UserBuilder.getInstance().registerSecurityQuestionAnswer(number, answer);
        User user = UserBuilder.getInstance().getResult();
        App.addUser(user);
        return new Result(true, "Successfully registered!");
    }
}
