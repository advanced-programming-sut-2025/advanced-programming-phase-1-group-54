package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.Message;

import java.util.HashMap;

public class UpdateController {
    public static void chooseMap(int lobbyId) {
        // todo
    }

    public static Message createAdvanceTime(int number) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("advance_time", 1);
        return new Message(body, Message.Type.update);
    }
}
