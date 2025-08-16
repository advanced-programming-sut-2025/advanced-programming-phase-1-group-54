package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.LobbyInfo;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.server.app.App;
import io.github.stardewmini.server.app.ServerApp;
import io.github.stardewmini.server.model.Lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyController {
    public static Message createLobby(String requester, String name, String password, boolean invisible) {
        int id = (int) (10000000 + Math.random() * 90000000);

        Lobby lobby = new Lobby(name, id, password, !invisible);
        User user = App.getUserByUsername(requester);
        lobby.addUser(user);
        App.addLobby(lobby);
        ServerApp.getConnectionByUsername(requester).setLobbyId(id);

        LobbyInfo lobbyInfo = lobby.getLobbyInfo();
        HashMap<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("name", lobbyInfo.name());
        body.put("id", lobbyInfo.id());
        body.put("isPrivate", lobbyInfo.isPrivate());
        return new Message(body, Message.Type.response);
    }

    public static Result joinLobby(String requester, int lobbyId, String password) {
        Lobby lobby = App.getLobbyById(lobbyId);
        User user = App.getUserByUsername(requester);

        System.out.println(requester);
        System.out.println(lobbyId);
        System.out.println(password);

        if (!lobby.getPassword().equals(password)) {
            return new Result(false, "Wrong Password");
        }

        ServerApp.getConnectionByUsername(requester).setLobbyId(lobbyId);
        lobby.addUser(user);
        lobby.setLastJoinedTime(System.currentTimeMillis());
        return new Result(true, "Joined Lobby");
    }

    public static Result leaveLobby(String requester, int lobbyId) {
        Lobby lobby = App.getLobbyById(lobbyId);
        User user = App.getUserByUsername(requester);

        lobby.removeUser(user);
        if (lobby.isEmpty()) {
            App.removeLobby(lobby);
        }
        return new Result(true, "Joined Lobby");
    }
}
