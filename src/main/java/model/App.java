package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.lives.Player;
import model.enums.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Menu currentMenu = Menu.LOGIN;

    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/");
    private static final File usersFile = new File(savesDir, "users.json");
    private static final File loggedInUserFile = new File(savesDir, "loggedInUser.json");
    private static final File gamesFile = new File(savesDir, "games.json");

    private static ArrayList<User> users = new ArrayList<>();

    private static User loggedInUser;
    private static Game currentGame;

    static {
        try {
            savesDir.mkdir();
            usersFile.createNewFile();
            gamesFile.createNewFile();
            loggedInUserFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader reader = new FileReader(usersFile)) {
            Gson gson = new Gson();
            users = new ArrayList<>(List.of(gson.fromJson(reader, User[].class)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader reader = new FileReader(loggedInUserFile)) {
            Gson gson = new Gson();
            String username = gson.fromJson(reader, String.class);
            App.setLoggedInUser(App.getUserByUsername(username));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (App.getLoggedInUser() != null)
            App.setCurrentMenu(Menu.MAIN);
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        if (loggedInUser == null)
            loggedInUserFile.delete();
        App.loggedInUser = loggedInUser;
    }

    public static void saveLoggedInUser() {
        try {
            loggedInUserFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(loggedInUserFile)) {
            writer.write(loggedInUser.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public static void saveUsers() {
        try (FileWriter writer = new FileWriter(usersFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(users));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static GameData getGameDataOf(User user) {
        try (FileReader reader = new FileReader(gamesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<GameData> games = new ArrayList<>(List.of(gson.fromJson(reader, GameData[].class)));
            for (GameData game : games) {
                for (String username : game.playerNames())
                    if (user.getUsername().equals(username))
                        return game;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addGameData(GameData gameData) {
        try (FileReader reader = new FileReader(gamesFile);
             FileWriter writer = new FileWriter(gamesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<GameData> games = new ArrayList<>(List.of(gson.fromJson(reader, GameData[].class)));
            games.add(gameData);

            writer.write(gson.toJson(games));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteGameData(GameData gameData) {
        try (FileReader reader = new FileReader(gamesFile);
             FileWriter writer = new FileWriter(gamesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<GameData> games = new ArrayList<>(List.of(gson.fromJson(reader, GameData[].class)));
            games.remove(gameData);

            writer.write(gson.toJson(games));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}