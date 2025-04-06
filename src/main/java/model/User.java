package model;

import model.enums.Gender;

public class User {
    private String username;
    private String passwordHash;
    private String nickname;
    private String email;
    private final Gender gender;
    private long maximumScore;
    private long numberOfPlayedGames;

    public User(String username, String password, String nickname, String email, Gender gender) {
        this.username = username;
        this.passwordHash = App.SHA256(password);
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.maximumScore = 0;
        this.numberOfPlayedGames = 0;
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

    public void setPassword(String password) {
        this.passwordHash = App.SHA256(password);
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

    public long getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(long maximumScore) {
        this.maximumScore = maximumScore;
    }

    public long getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    public void increaseNumberOfPlayedGames() {
        this.numberOfPlayedGames++;
    }
}
