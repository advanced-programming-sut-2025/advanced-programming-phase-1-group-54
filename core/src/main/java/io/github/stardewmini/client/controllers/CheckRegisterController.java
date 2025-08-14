package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;

public class CheckRegisterController {
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
