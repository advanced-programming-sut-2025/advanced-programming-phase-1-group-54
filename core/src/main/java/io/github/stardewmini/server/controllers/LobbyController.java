package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.LobbyInfo;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.server.app.App;
import io.github.stardewmini.server.model.Lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyController {
    public static Result createLobby(String requester, String name, String password, boolean invisible) {
        int id = (int) (10000000 + Math.random() * 90000000);

        Lobby lobby = new Lobby(name, id, password, !invisible);
        User user = App.getUserByUsername(requester);
        lobby.addUser(user);
        App.addLobby(lobby);
        return new Result(true, "Lobby created!");
    }

    public static Result joinLobby(String requester, int lobbyId, String password) {
        Lobby lobby = App.getLobbyById(lobbyId);
        User user = App.getUserByUsername(requester);

        if (!lobby.getPassword().equals(password)) {
            return new Result(false, "Wrong Password");
        }

        lobby.addUser(user);
        return new Result(true, "Joined Lobby");
    }

    public static Result leaveLobby(String requester, int lobbyId) {
        Lobby lobby = App.getLobbyById(lobbyId);
        User user = App.getUserByUsername(requester);

        lobby.removeUser(user);
        return new Result(true, "Joined Lobby");
    }
}
