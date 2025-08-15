package io.github.stardewmini.server.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.GameData;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.server.model.Lobby;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/server/");
    private static final File usersFile = new File(savesDir, "users.json");
    private static final File gamesFile = new File(savesDir, "games.json");

    private static ArrayList<User> users;
    private static ArrayList<Lobby> lobbies = new ArrayList<>();
    private static Game currentGame;


    static {
        if (!savesDir.mkdir()) {
            try {
                if (!usersFile.createNewFile())
                    readUsers();
                gamesFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void readUsers() {
        try (FileReader reader = new FileReader(usersFile)) {
            Gson gson = new Gson();
            users = new ArrayList<>(List.of(gson.fromJson(reader, User[].class)));
        } catch (NullPointerException ignored) {
            users = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<GameData> readGames() {
        ArrayList<GameData> games;
        try (FileReader reader = new FileReader(gamesFile)) {
            Gson gson = new Gson();
            games = new ArrayList<>(List.of(gson.fromJson(reader, GameData[].class)));
        } catch (NullPointerException ignored) {
            games = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return games;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
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
        ArrayList<GameData> games = readGames();
        for (GameData game : games) {
            for (String username : game.playerNames())
                if (user.getUsername().equals(username))
                    return game;
        }
        return null;
    }

    public static void addGameData(GameData gameData) {
        try (FileWriter writer = new FileWriter(gamesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            ArrayList<GameData> games = readGames();

            games.add(gameData);
            writer.write(gson.toJson(games));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteGameData(GameData gameData) {
        try (FileWriter writer = new FileWriter(gamesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<GameData> games = readGames();

            games.remove(gameData);

            writer.write(gson.toJson(games));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addLobby(Lobby lobby) {
        lobbies.add(lobby);
    }

    public static void removeLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }

    public static Lobby getLobbyById(int id) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId() == id) {
                return lobby;
            }
        }

        return null;
    }

    public static List<Lobby> getLobbies() {
        return List.copyOf(lobbies);
    }
}
