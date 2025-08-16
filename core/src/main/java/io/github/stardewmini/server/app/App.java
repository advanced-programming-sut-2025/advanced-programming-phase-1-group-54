package io.github.stardewmini.server.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.GameData;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.server.model.Lobby;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/");
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
//        try{
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
//            PreparedStatement stmt = conn.prepareStatement("SELECT json_data FROM users");
//
//            ResultSet rs = stmt.executeQuery();
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//            users = new ArrayList<>();
//            while (rs.next()) {
//                String json = rs.getString("json_data");
//
//                Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
//                ArrayList<User> tempList = gson.fromJson(json, userListType);
//
//                if(tempList != null) {
//                    users.addAll(tempList);
//                }
//            }
////            users = new ArrayList<>(List.of(gson.fromJson(json, User[].class)));
//
//            rs.close();
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

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

    public static void saveUsers()  {
            String sql = """
        CREATE TABLE IF NOT EXISTS UsersJson (
            id INTEGER PRIMARY KEY,
            data TEXT
        );
    """;

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Asus/Desktop/database.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("جدول UsersJson ساخته شد!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Asus/Desktop/database.db");){
            Gson gson = new Gson();
            String json = gson.toJson(users);

            Statement st = conn.createStatement();

            st.executeUpdate("DELETE FROM UsersJson");

            String sqlInsert = "INSERT INTO UsersJson(id, data) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, 1);
            stmt.setString(2, json);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        try (FileWriter writer = new FileWriter(usersFile)) {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            writer.write(gson.toJson(users));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
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
