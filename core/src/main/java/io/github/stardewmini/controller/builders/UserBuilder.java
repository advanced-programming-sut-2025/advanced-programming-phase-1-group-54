package io.github.stardewmini.controller.builders;

import io.github.stardewmini.model.User;
import io.github.stardewmini.model.enums.Gender;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserBuilder {
    private static String username;
    private static String password;
    private static String nickname;
    private static String email;
    private static Gender gender;

    private static int chosenSecurityQuestionNumber;
    private static String answerToSecurityQuestion;

    public static void reset() {
        username = null;
        password = null;
        nickname = null;
        email = null;
        gender = null;
        chosenSecurityQuestionNumber = -1;
        answerToSecurityQuestion = null;
    }

    public static void registerBasicData(String username, String password, String nickname, String email, Gender gender) {
        UserBuilder.username = username;
        UserBuilder.password = password;
        UserBuilder.nickname = nickname;
        UserBuilder.email = email;
        UserBuilder.gender = gender;
    }

    public static void registerSecurityQuestionAnswer(int number, String answer) {
        UserBuilder.chosenSecurityQuestionNumber = number;
        UserBuilder.answerToSecurityQuestion = answer;
    }

    public static User getResult() {
        User user = new User(username, sha256(password), nickname, email, gender, chosenSecurityQuestionNumber, sha256(answerToSecurityQuestion));
        UserBuilder.reset();
        return user;
    }

    public static String sha256(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(string.getBytes());

            // Convert byte array into signum representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // pad with leading zero
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
