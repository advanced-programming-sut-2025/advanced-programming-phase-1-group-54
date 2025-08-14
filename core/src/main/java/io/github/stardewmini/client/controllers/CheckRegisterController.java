package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;

public class CheckRegisterController {
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

    public static String[] getSecurityQuestions() {
        return User.getSecurityQuestions();
    }
}
