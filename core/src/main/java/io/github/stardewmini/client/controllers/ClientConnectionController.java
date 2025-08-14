package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.Message;

import java.util.HashMap;

public class ClientConnectionController {
    public static Message handleCommand(Message message) {
        String command = message.getFromBody("command");
        return null; // TODO
    }

    public static void handleUpdate(Message message) {
        // TODO
    }

    public static Message createLogin(String username, String password) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "login");
        body.put("username", username);
        body.put("password", password);
        return new Message(body, Message.Type.command);
    }

    public static Message createRegister() {
        // TODO
        return null;
    }
}
