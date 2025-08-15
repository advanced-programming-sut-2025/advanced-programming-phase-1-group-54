package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.enums.Direction;

import java.util.HashMap;

public class ClientGameController {
    public static Message createUseTool(Direction direction) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "use_tool");
        body.put("direction", direction);
        return new Message(body, Message.Type.update);
    }

    public static Message createCooking(String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "cooking");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createCrafting(String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "crafting");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createBuy(String itemName, String name, int price, String locationString) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "buy");
        body.put("itemName", itemName);
        body.put("name", name);
        body.put("price", price);
        body.put("locationString", locationString);
        return new Message(body, Message.Type.command);
    }

    public static Message createPlanting(String seedName, String directionString) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "planting");
        body.put("itemName", seedName);
        body.put("name", directionString);
        return new Message(body, Message.Type.command);
    }
}
