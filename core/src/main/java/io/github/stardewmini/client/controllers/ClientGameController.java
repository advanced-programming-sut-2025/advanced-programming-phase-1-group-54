package io.github.stardewmini.client.controllers;

import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.enums.Direction;
import io.github.stardewmini.common.model.map.Location;

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
        body.put("direction", directionString);
        return new Message(body, Message.Type.command);
    }

    public static Message createEquipTool(String toolName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "equip_tool");
        body.put("toolName", toolName);
        return new Message(body, Message.Type.command);
    }

    public static Message createTrashCan(String trashItem, String trashNumber) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "trash_can");
        body.put("trashItem", trashItem);
        body.put("trashNumber", trashNumber);
        return new Message(body, Message.Type.command);
    }


    public static Message createMeetsNPC(String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "meets_npc");
        body.put("NPCname", npcName);
        return new Message(body, Message.Type.command);
    }

    public static Message createQuestList(String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "quest_list");
        body.put("npcName", npcName);
        return new Message(body, Message.Type.command);
    }

    public static Message createFriendShipNpc(String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "friendship_npc");
        body.put("npcName", npcName);
        return new Message(body, Message.Type.command);
    }

    public static Message createGiftNpc(String npcName,String itemName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "gift_npc");
        body.put("npcName", npcName);
        body.put("itemName", itemName);
        return new Message(body, Message.Type.command);
    }

    public static Message createQuestFinish(String questNumber,String npcName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "quest_finish");
        body.put("questNumber", questNumber);
        body.put("npcName", npcName);
        return new Message(body, Message.Type.command);
    }


    public static Message createShowAnimal(String name){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "show_animal");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createFeedAnimal(String name){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "feed_animal");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createPet(String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "pet");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createMoveAnimal(String name, Location location){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "move_animal");
        body.put("name", name);
        body.put("location", location);
        return new Message(body, Message.Type.command);
    }

    public static Message createGetAnimalProduce(String name) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "get_animal_produce");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }

    public static Message createSellAnimal(String name){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "sell_animal");
        body.put("name", name);
        return new Message(body, Message.Type.command);
    }


    public static Message createGameUsernames(){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "game_usernames");
        return new Message(body, Message.Type.command);
    }

    public static Message createTalk(String username, String talkingMessage){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "talk");
        body.put("username", username);
        body.put("talkingMessage", talkingMessage);
        return new Message(body, Message.Type.command);
    }

    public static Message createTalkHistory(String username){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "talk_history");
        body.put("username", username);
        return new Message(body, Message.Type.command);
    }
}
