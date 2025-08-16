package io.github.stardewmini.client.app;

import com.google.gson.Gson;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.common.model.lives.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class App { /* save local copies here */
    private static final File savesDir = new File(System.getProperty("user.home") + "/Desktop/StardewValley/");

    private static final File usersFile = new File(savesDir, "users.json");

    private static ArrayList<User> users = new ArrayList<>();

    static {
        if (!savesDir.mkdir()) {
            try {
                if (!usersFile.createNewFile())
                    readUsers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Game currentGame;
    private static String loggedInUsername;

    private static final AtomicBoolean nextScreenReady = new AtomicBoolean(false);

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

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Player getCurrentPlayer() {
        return App.getCurrentGame().getPlayerByUsername(loggedInUsername);
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String loggedInUsername) {
        App.loggedInUsername = loggedInUsername;
    }

    public static boolean isNextScreenReady() {
        return nextScreenReady.get();
    }

    public static void setNextScreenReady(boolean nextScreenReady) {
        App.nextScreenReady.set(nextScreenReady);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
