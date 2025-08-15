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
        body.put("advance_time", number);
        return new Message(body, Message.Type.update);
    }

    public static Message createAdvanceDate(int number) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("advance_date", number);
        return new Message(body, Message.Type.update);
    }

    public static Message createEnergy(String requester,int number) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("energy", number);
        body.put("requester", requester);
        return new Message(body, Message.Type.update);
    }

}
