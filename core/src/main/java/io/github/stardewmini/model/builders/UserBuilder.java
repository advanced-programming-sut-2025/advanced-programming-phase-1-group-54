package io.github.stardewmini.model.builders;

import io.github.stardewmini.model.User;
import io.github.stardewmini.model.enums.Gender;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserBuilder {
    private static UserBuilder instance;

    private UserBuilder() {
    }

    public static UserBuilder getInstance() {
        if (instance == null)
            instance = new UserBuilder();
        return instance;
    }

    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;

    private int chosenSecurityQuestionNumber;
    private String answerToSecurityQuestion;

    public void reset() {
        username = null;
        password = null;
        nickname = null;
        email = null;
        gender = null;
        chosenSecurityQuestionNumber = -1;
        answerToSecurityQuestion = null;
    }

    public void registerBasicData(String username, String password, String nickname, String email, Gender gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
    }

    public void registerSecurityQuestionAnswer(int number, String answer) {
        this.chosenSecurityQuestionNumber = number;
        this.answerToSecurityQuestion = answer;
    }

    public User getResult() {
        User user = new User(username, sha256(password), nickname, email, gender, chosenSecurityQuestionNumber, sha256(answerToSecurityQuestion));
        this.reset();
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
