package io.github.stardewmini.client.controllers.game;

import io.github.stardewmini.common.Message;

public class UpdateClient {
    public static void handleUpdate(Message message) {
        String update = message.getFromBody("update");
        switch (update) {
            case "advance_time":
                handleAdvanceTime(message);
                break;
            case "advance_date":
                handleAdvanceDate(message);
                break;
            case "set_energy":
                handleSetEnergy(message);
                break;
            case "thunder_strike":
                handleThunderStrike(message);
                break;
            case "select_emojis":
                handleSelectEmojis(message);
                break;
            case "talk":
                handleTalk(message);
                break;
            case "sell_animal":
                handleSellAnimal(message);
                break;
            case "get_animal_produce":
                handleGetAnimalProduce(message);
                break;
            case "move_animal":
                handleMoveAnimal(message);
                break;
            case "pet":
                handlePet(message);
                break;
            case "feed_animal":
                handleFeedAnimal(message);
                break;
            case "quest_finish":
                handleQuestFinish(message);
                break;
            case "gift_npc":
                handleGiftNpc(message);
                break;
            case "meets_npc":
                handleMeetsNPC(message);
                break;
            case "trash_can":
                handleTrashCan(message);
                break;
            case "equip_tool":
                handleEquipTool(message);
                break;
            case "planting":
                handlePlanting(message);
                break;
            case "buy":
                handleBuy(message);
                break;
            case "crafting":
                handleCrafting(message);
                break;
            case "cooking":
                handleCooking(message);
                break;
            case "use_tool":
                handleUseTool(message);
                break;
            case "tag":
                handleTag(message);
                break;

        }

    }

    private static void handleAdvanceTime(Message message) {
        UpdateController.advanceTime(
            message.getFromBody("amount")
        );
    }

    private static void handleAdvanceDate(Message message) {
        UpdateController.advanceDate(
            message.getFromBody("amount")
        );
    }

    private static void handleSetEnergy(Message message) {
        UpdateController.setEnergy(
            message.getFromBody("requester"),
            message.getFromBody("amount")
        );
    }

    private static void handleThunderStrike(Message message) {
        UpdateController.thunderStrike(
            message.getFromBody("requester"),
            message.getFromBody("location")
        );
    }

    private static void handleSelectEmojis(Message message) {
        UpdateController.showEmojis(
            message.getFromBody("requester"),
            message.getFromBody("emoji")
        );
    }

    private static void handleTalk(Message message) {
        UpdateController.talk(
            message.getFromBody("requester"),
            message.getFromBody("receiver"),
            message.getFromBody("message")
        );
    }

    private static void handleSellAnimal(Message message) {
        UpdateController.sellAnimal(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }

    private static void handleGetAnimalProduce(Message message) {
        UpdateController.getAnimalProduce(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }

    private static void handleMoveAnimal(Message message) {
        UpdateController.moveAnimal(
            message.getFromBody("requester"),
            message.getFromBody("name"),
            message.getFromBody("location")
        );
    }

    private static void handlePet(Message message) {
        UpdateController.pet(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }

    private static void handleFeedAnimal(Message message) {
        UpdateController.feedAnimal(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }


    private static void handleQuestFinish(Message message) {
        UpdateController.questFinish(
            message.getFromBody("requester"),
            message.getFromBody("questNumber"),
            message.getFromBody("npcName")
        );
    }

    private static void handleGiftNpc(Message message) {
        UpdateController.giftNpc(
            message.getFromBody("requester"),
            message.getFromBody("npcName"),
            message.getFromBody("itemName")
        );
    }

    private static void handleMeetsNPC(Message message) {
        UpdateController.meetsNpc(
            message.getFromBody("requester"),
            message.getFromBody("npcName")
        );
    }

    private static void handleTrashCan(Message message) {
        UpdateController.throwInTrash(
            message.getFromBody("requester"),
            message.getFromBody("trashItem"),
            message.getFromBody("trashNumber")
        );
    }

    private static void handleEquipTool(Message message) {
        UpdateController.equipTool(
            message.getFromBody("requester"),
            message.getFromBody("toolName")
        );
    }

    private static void handlePlanting(Message message) {
        UpdateController.planting(
            message.getFromBody("requester"),
            message.getFromBody("itemName"),
            message.getFromBody("direction")
        );
    }

    private static void handleBuy(Message message) {
        UpdateController.buy(
            message.getFromBody("requester"),
            message.getFromBody("itemName"),
            message.getFromBody("name"),
            message.getFromBody("price"),
            message.getFromBody("locationString")
        );
    }

    private static void handleCrafting(Message message) {
        UpdateController.crafting(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }

    private static void handleCooking(Message message) {
        UpdateController.cooking(
            message.getFromBody("requester"),
            message.getFromBody("name")
        );
    }

    private static void handleUseTool(Message message) {
        UpdateController.useTool(
            message.getFromBody("requester"),
            message.getFromBody("direction")
        );
    }

    private static void handleTag(Message message) {

    }

}
