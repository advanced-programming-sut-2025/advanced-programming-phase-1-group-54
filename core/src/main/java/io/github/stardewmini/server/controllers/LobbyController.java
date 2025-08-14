package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.User;
import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.server.model.Lobby;

import java.util.Random;

public class LobbyController {
    public static Result createLobby(String requester, String name, String password, boolean visible) {
        int id = (int) (10000000 + Math.random() * 90000000);

        Lobby lobby = new Lobby(name, id, password, visible);
        User user = GameApp.getUserByUsername(requester);
        lobby.addUser(user);
        GameApp.addLobby(lobby);
        return new Result(true, "Lobby created!");
    }

    public static Result joinLobby(String requester, int lobbyId) {
        Lobby lobby = GameApp.getLobbyById(lobbyId);
        User user = GameApp.getUserByUsername(requester);

        lobby.addUser(user);
        return new Result(true, "Joined Lobby");
    }

}
