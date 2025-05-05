package model;

import com.google.gson.Gson;
import controller.builders.GameBuilder;
import model.alive.Player;
import model.enums.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Menu currentMenu = Menu.LOGIN;

    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/");

    private static final File usersFile = new File(savesDir, "users.json");
    private static final File loggedInUserFile = new File(savesDir, "loggedInUser.txt");
    private static User loggedInUser;

    private static final File gamesFile = new File(savesDir, "games.json");

    private static final ArrayList<Game> games = new ArrayList<>(); // TODO : remove
    private static Game currentGame;

    static {
        savesDir.mkdir();
        try (Scanner scanner = new Scanner(loggedInUserFile)) {
            if (scanner.hasNextLine())
                App.setLoggedInUser(App.getUserByUsername(scanner.nextLine()));
            if (App.getLoggedInUser() != null)
                App.setCurrentMenu(Menu.MAIN);
        } catch (FileNotFoundException ignored) {
        }
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
        try (FileWriter writer = new FileWriter(loggedInUserFile)) {
            writer.write(loggedInUser.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserByUsername(String username) {
        try (Scanner scanner = new Scanner(usersFile)) {
            Gson gson = new Gson();
            for (User user : gson.fromJson(scanner.nextLine(), User[].class)) {
                if (user.getUsername().equals(username))
                    return user;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void addUser(User user) {
        try (Scanner scanner = new Scanner(usersFile);
             FileWriter writer = new FileWriter(usersFile)) {
            Gson gson = new Gson();
            User[] users = gson.fromJson(scanner.nextLine(), User[].class);
            User[] newUsers = new User[users.length + 1];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            newUsers[users.length] = user;
            writer.write(gson.toJson(newUsers));
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

    public static void addGame(Game game) {
        try (FileWriter writer = new FileWriter(gamesFile, true)) {
            // TODO
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        games.add(game);
    }

    public static Game getGameByUser(User user) {
        try (Scanner scanner = new Scanner(gamesFile)) {
            // TODO
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Game game : games) {
            for (Player player : game.getPlayers()) {
                if (player.getControllingUser() == user)
                    return game;
            }
        }
        return null;
    }
}