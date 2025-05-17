package model;

import com.google.gson.Gson;
import model.enums.Gender;

import java.io.*;
import java.util.Objects;

public class User {
    private String username;
    private String passwordHash;
    private String nickname;
    private String email;
    private final Gender gender;

    private long maximumMoney;
    private long numberOfPlayedGames;
    private boolean inGame;

    private final int securityQuestionNumber;
    private final String answerHash;

    public static String[] getSecurityQuestions() {
        try (InputStream inputStream = User.class.getClassLoader().getResourceAsStream("securityQuestions.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            return gson.fromJson(reader.readLine(), String[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User(String username, String passwordHash, String nickname, String email,
                Gender gender, int securityQuestionNumber, String answerHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.maximumMoney = 0;
        this.numberOfPlayedGames = 0;
        this.securityQuestionNumber = securityQuestionNumber;
        this.answerHash = answerHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public long getMaximumMoney() {
        return maximumMoney;
    }

    public void setMaximumMoney(long maximumMoney) {
        this.maximumMoney = maximumMoney;
    }

    public long getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    public void increaseNumberOfPlayedGames() {
        this.numberOfPlayedGames++;
    }

    public String getSecurityQuestion() {
        return User.getSecurityQuestions()[securityQuestionNumber - 1];
    }

    public String getAnswerHash() {
        return answerHash;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
