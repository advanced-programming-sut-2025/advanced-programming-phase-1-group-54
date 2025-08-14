package io.github.stardewmini.server.controllers;

import io.github.stardewmini.common.Message;

public class GameCommandController {
    public static Message handleCommand(String username, Message message) {
        String command = message.getFromBody("command");

        switch (command) {
            case "use_tool":
                return handleUseTool(username, message);
            case "throw_in_trash":
                return handleThrowInTrash(username, message);
            case "cooking":
                return handleCooking(username, message);
            case "crafting":
                return handleCrafting(username, message);


            default:
                return null;

                // Todo
        }
    }

    private static Message handleUseTool(String username, Message message) {
        // todo
        return null;
    }

    private static Message handleThrowInTrash(String username, Message message) {
        // todo
        return null;
    }

    private static Message handleCooking(String username, Message message) {
        // todo
        return null;
    }

    private static Message handleCrafting(String username, Message message) {
        // todo
        return null;
    }
}
