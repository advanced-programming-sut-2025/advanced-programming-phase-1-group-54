package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.Message;

public class ClientConnectionController {
    public static Message handleCommand(Message message) {
        String command = message.getFromBody("command");
        return null; // TODO
    }

    public static void handleUpdate(Message message) {
    }
}
