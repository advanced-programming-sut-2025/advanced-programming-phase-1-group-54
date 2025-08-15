package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameData;
import io.github.stardewmini.server.app.ServerApp;

import java.util.HashMap;

public class UpdateController {
    public static void chooseMap(int lobbyId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "choose_map");
        Message message = new Message(body, Message.Type.update);

        for (ConnectionThread connectionThread : ServerApp.getConnectionsByLobbyId(lobbyId)) {
            connectionThread.sendMessage(message);
        }
    }

    public static void startGame(int lobbyId, GameData gameData) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "start_game");
        body.put("gameData", gameData);
        Message message = new Message(body, Message.Type.update);

        for (ConnectionThread connectionThread : ServerApp.getConnectionsByLobbyId(lobbyId)) {
            connectionThread.sendMessage(message);
        }
    }

    public static Message createAdvanceTime(int amount) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "advance_time");
        body.put("amount", amount);
        return new Message(body, Message.Type.update);
    }
}
