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

    public static Message createAdvanceDate(int amount) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "advance_date");
        body.put("amount", amount);
        return new Message(body, Message.Type.update);
    }

    public static Message createSetEnergy(String requester,int amount) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "set_energy");
        body.put("amount", amount);
        body.put("requester", requester);
        return new Message(body, Message.Type.update);
    }

    public static Message createThunderStrike(String location) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "thunder_strike");
        body.put("location", location);
        return new Message(body, Message.Type.update);
    }

    public static Message createSelectEmojis(String requester, String emoji) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "select_emojis");
        body.put("requester", requester);
        body.put("emoji", emoji);
        return new Message(body, Message.Type.update);
    }

    public static Message createTalk(String requester, String receiver,String message) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "talk");
        body.put("requester", requester);
        body.put("receiver", receiver);
        body.put("message", message);
        return new Message(body, Message.Type.update);
    }

    public static Message createSellAnimal(String requester, String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "sell_animal");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createGetAnimalProduce(String requester, String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "get_animal_produce");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createMoveAnimal(String requester, String name,String location) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "move_animal");
        body.put("requester", requester);
        body.put("name", name);
        body.put("location", location);
        return new Message(body, Message.Type.update);
    }

    public static Message createPet(String requester, String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "pet");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createFeedAnimal(String requester, String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "feed_animal");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createQuestFinish(String requester,String questNumber, String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "quest_finish");
        body.put("requester", requester);
        body.put("questNumber", questNumber);
        body.put("npcName", npcName);
        return new Message(body, Message.Type.update);
    }

    public static Message createGiftNpc(String requester, String npcName,String itemName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "gift_npc");
        body.put("requester", requester);
        body.put("npcName", npcName);
        body.put("itemName", itemName);
        return new Message(body, Message.Type.update);
    }

    public static Message createMeetsNPC(String requester, String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "meets_npc");
        body.put("requester", requester);
        body.put("npcName", npcName);
        return new Message(body, Message.Type.update);
    }

    public static Message createTrashCan(String requester, String trashItem,String trashNumber) {
        HashMap<String,Object> body = new HashMap<>();
        body.put("update", "trash_can");
        body.put("requester", requester);
        body.put("trashItem", trashItem);
        body.put("trashNumber", trashNumber);
        return new Message(body, Message.Type.update);
    }

    public static Message createEquipTool(String requester, String toolName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "equip_tool");
        body.put("requester", requester);
        body.put("toolName", toolName);
        return new Message(body, Message.Type.update);
    }

    public static Message createPlanting(String requester, String itemName,String direction) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "planting");
        body.put("requester", requester);
        body.put("itemName", itemName);
        body.put("direction", direction);
        return new Message(body, Message.Type.update);
    }

    public static Message createBuy(String requester, String itemName,String name,String price,String location) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "buy");
        body.put("requester", requester);
        body.put("itemName", itemName);
        body.put("name", name);
        body.put("price", price);
        body.put("locationString", location);
        return new Message(body, Message.Type.update);
    }

    public static Message createCrafting(String requester, String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "crafting");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createCooking(String requester,String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "cooking");
        body.put("requester", requester);
        body.put("name", name);
        return new Message(body, Message.Type.update);
    }

    public static Message createUseTool(String requester, String direction) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "use_tool");
        body.put("requester", requester);
        body.put("direction", direction);
        return new Message(body, Message.Type.update);
    }

    public static Message createTag(String requester, String username) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "tag");
        body.put("requester", requester);
        body.put("username", username);
        return new Message(body, Message.Type.update);
    }

}
