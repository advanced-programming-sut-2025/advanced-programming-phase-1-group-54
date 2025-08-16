package io.github.stardewmini.client.controllers;

import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.game.UpdateClient;
import io.github.stardewmini.client.view.ChooseMapScreen;
import io.github.stardewmini.client.view.GameScreen;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.GameData;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.builders.GameBuilder;
import io.github.stardewmini.common.model.enums.Gender;

import java.util.HashMap;
import java.util.List;

public class ClientConnectionController {
    public static Result getResultFromResponse(Message message) {
        if (message.getType() != Message.Type.response) {
            return null;
        }
        return new Result(message.getIntFromBody("code"), message.getFromBody("message"));
    }

    public static Message handleCommand(Message message) {
        // TODO
        return null;
    }

    public static void handleUpdate(Message message) {
        String update = message.getFromBody("update");
        switch (update) {
            case "choose_map":
                handleChooseMap();
                return;
            case "start_game":
                handleCreateGame(message);
                return;

            default:
                UpdateClient.handleUpdate(message);
        }
    }

    public static void handleCreateGame(Message message) {
        List<String> playerNames = message.getFromBody("playerNames");
        List<Integer> playerFarms = message.getFromBody("playerFarms");
        long seed = message.getLongFromBody("seed");

        String[] okPlayerNames = new String[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            okPlayerNames[i] = playerNames.get(i);
        }
        int[] okPlayerFarms = new int[playerFarms.size()];
        for (int i = 0; i < playerFarms.size(); i++) {
            okPlayerFarms[i] = playerFarms.get(i);
        }

        for (String string : playerNames) {
            System.out.println(string);
        }
        for (int number : playerFarms) {
            System.out.println(number);
        }
        System.out.println(seed);


        GameData gameData = new GameData(okPlayerNames, okPlayerFarms, seed);
        GameBuilder.getInstance().reset();
        GameBuilder.getInstance().setGameData(gameData);
        Game game = GameBuilder.getInstance().getResult();
        App.setCurrentGame(game);



//        Main.getInstance().getScreen().dispose();
//        Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(), ""));
        App.setNextScreenReady(true);

    }


    private static void handleChooseMap() {

//        Main.getInstance().getScreen().dispose();
//        Main.getInstance().setScreen(new ChooseMapScreen());

        App.setNextScreenReady(true);

    }

    public static Message status() {
        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("command", "status");
        messageBody.put("ip", ClientApp.getIp());
        messageBody.put("port", ClientApp.getPort());
        return new Message(messageBody, Message.Type.response);
    }

    public static Message createLogin(String username, String password, boolean stayLoggedIn) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "login");
        body.put("username", username);
        body.put("password", password);
        body.put("stayLoggedIn", stayLoggedIn);
        return new Message(body, Message.Type.command);
    }

    public static Message createRegister(String username, String password, String confirmPassword,
                                         String nickname, String email, Gender gender) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "register");
        body.put("username", username);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        body.put("nickname", nickname);
        body.put("email", email);
        body.put("gender", gender.toString());
        return new Message(body, Message.Type.command);
    }

    public static Message createPickQuestion(int number, String answer, String confirmAnswer) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "pick_question");
        body.put("number", number);
        body.put("answer", answer);
        body.put("confirmAnswer", confirmAnswer);
        return new Message(body, Message.Type.command);
    }

    public static Message createResetRegister() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "reset_register");
        return new Message(body, Message.Type.command);
    }

    public static Message createForgetPassword(String username) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "forgot_password");
        body.put("username", username);
        return new Message(body, Message.Type.command);
    }

    public static Message createAnswer(String username, String answer) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "answer");
        body.put("username", username);
        body.put("answer", answer);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeForgottenPassword(String username, String password, String confirmPassword) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_forgotten_password");
        body.put("username", username);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeUsername(String username) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_username");
        body.put("username", username);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeEmail(String email) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_email");
        body.put("email", email);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeNickname(String nickname) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_nickname");
        body.put("nickname", nickname);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangePassword(String newPassword, String oldPassword) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_password");
        body.put("newPassword", newPassword);
        body.put("oldPassword", oldPassword);
        return new Message(body, Message.Type.command);
    }

    public static Message createUserInfo() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "user_info");
        return new Message(body, Message.Type.command);
    }

    public static Message createHostLobby(String name, String password, boolean invisible) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "host_lobby");
        body.put("name", name);
        body.put("password", password);
        body.put("invisible", invisible);
        return new Message(body, Message.Type.command);
    }

    public static Message createJoinLobby(int id, String password) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "join_lobby");
        body.put("id", id);
        body.put("password", password);
        return new Message(body, Message.Type.command);
    }

    public static Message createLeaveLobby(int id) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "leave_lobby");
        body.put("id", id);
        return new Message(body, Message.Type.command);
    }

    public static Message createRefreshLobbyList() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "refresh_lobby_list");
        return new Message(body, Message.Type.command);
    }

    public static Message createFindLobby(String idString) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "find_lobby");
        body.put("id", idString);
        return new Message(body, Message.Type.command);
    }

    public static Message createStartGame(int id) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "start_game");
        body.put("id", id);
        return new Message(body, Message.Type.command);
    }

    public static Message createRefreshLobbyMembers(int id) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "refresh_lobby_members");
        body.put("id", id);
        return new Message(body, Message.Type.command);
    }
}
