package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.Message;

import java.util.HashMap;

public class UpdateController {
    public static void chooseMap(int lobbyId) {

    }

    public static Message createAdvanceTime(int amount) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "advance_time");
        body.put("amount", amount);
        return new Message(body, Message.Type.update);
    }
}
