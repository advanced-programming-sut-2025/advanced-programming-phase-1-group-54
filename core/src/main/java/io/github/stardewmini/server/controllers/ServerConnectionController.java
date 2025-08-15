package io.github.stardewmini.server.controllers;


import io.github.stardewmini.Main;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.server.controllers.game.*;

import java.util.HashMap;

public class ServerConnectionController {
    public static Message handleCommand(String username, String clientIp, int clientPort, Message message) {
        String command = message.getFromBody("command");
        switch (command) {
            case "register":
                return handleRegister(message);
            case "pick_question":
                return handlePickSecurityQuestion(message);
            case "reset_register":
                return handleResetUserBuilder();

            case "login":
                return handleLogin(clientIp, clientPort, message);
            case "forgot_password":
                return handleForgotPassword(message);
            case "answer":
                return handleAnswer(message);
            case "change_forgotten_password":
                return handleChangeForgottenPassword(message);

            case "change_username":
                return handleChangeUsername(username, message);
            case "change_password":
                return handleChangePassword(username, message);
            case "change_nickname":
                return handleChangeNickname(username, message);
            case "change_email":
                return handleChangeEmail(username, message);
            case "user_info":
                return handleUserInfo(username, message);


            case "use_tool":
                return handleUseTool(username, message);
            case "cooking":
                return handleCooking(username, message);
            case "crafting":
                return handleCrafting(username, message);
            case "buy":
                return handleBuy(username, message);
            case "planting":
                return handlePlanting(username, message);
            case "equip_tool":
                return handleEquipTool(username, message);
            case "trash_can":
                return handleTrashCan(username, message);

            case "meets_npc":
                return handleMeetsNPC(username, message);
            case "quest_list":
                return handleQuestList(username, message);
            case "friendship_npc":
                return handleFriendShipNpc(username, message);
            case "gift_npc":
                return handleGiftNpc(username, message);
            case "quest_finish":
                return handleQuestFinish(username, message);

            case "show_animal":
                return handleShowAnimal(username, message);
            case "feed_animal":
                return handleFeedAnimal(username, message);
            case "pet":
                return handlePet(username, message);
            case "move_animal":
                return handleMoveAnimal(username, message);
            case "get_animal_produce":
                return handleGetAnimalProduce(username, message);
            case "sell_animal":
                return handleSellAnimal(username, message);


            case"game_usernames":
                return handleGameUsers(username, message);
            case"talk":
                return handleTalk(username, message);
            case"talk_history":
                return handleTalkHistory(username, message);


            default:
                return null;
//                return GameCommandController.handleCommand(username, message);
        }
    }

    public static void handleUpdate(String username, Message message) {
        // TODO
    }

    private static Message makeResponseFrom(Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("code", result.code());
        body.put("message", result.message());
        return new Message(body, Message.Type.response);
    }

    private static Message handleRegister(Message message) {
        Result result = RegisterMenuController.register(
            message.getFromBody("username"),
            message.getFromBody("password"),
            message.getFromBody("confirmPassword"),
            message.getFromBody("nickname"),
            message.getFromBody("email"),
            message.getFromBody("gender")
        );
        return makeResponseFrom(result);
    }

    private static Message handlePickSecurityQuestion(Message message) {
        Result result = RegisterMenuController.pickQuestion(
            message.getIntFromBody("number"),
            message.getFromBody("answer"),
            message.getFromBody("confirmAnswer")
        );
        return makeResponseFrom(result);
    }

    private static Message handleResetUserBuilder() {
        Result result = RegisterMenuController.resetUserBuilder();
        return makeResponseFrom(result);
    }

    private static Message handleLogin(String clientIp, int clientPort, Message message) {
        Result result = LoginMenuController.login(
            clientIp, clientPort,
            message.getFromBody("username"),
            message.getFromBody("password"),
            message.getBooleanFromBody("stayLoggedIn")
        );
        return makeResponseFrom(result);
    }

    private static Message handleForgotPassword(Message message) {
        Result result = LoginMenuController.getSecurityQuestion(
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleAnswer(Message message) {
        Result result = LoginMenuController.answer(
            message.getFromBody("username"),
            message.getFromBody("answer")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeForgottenPassword(Message message) {
        Result result = LoginMenuController.changePassword(
            message.getFromBody("username"),
            message.getFromBody("password"),
            message.getFromBody("confirmPassword")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeUsername(String username, Message message) {
        Result result = ProfileMenuController.changeUsername(
            username,
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangePassword(String username, Message message) {
        Result result = ProfileMenuController.changePassword(
            username,
            message.getFromBody("newPassword"),
            message.getFromBody("oldPassword")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeNickname(String username, Message message) {
        Result result = ProfileMenuController.changeNickname(
            username,
            message.getFromBody("nickname")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeEmail(String username, Message message) {
        Result result = ProfileMenuController.changeEmail(
            username,
            message.getFromBody("email")
        );
        return makeResponseFrom(result);
    }

    private static Message handleUserInfo(String username, Message message) {
        Result result = ProfileMenuController.showUserInfo(username);
        return makeResponseFrom(result);
    }



    private static Message handleUseTool(String username, Message message) {
        Result result = ToolsController.useTool(
            username,
            message.getFromBody("direction")
        );
        return makeResponseFrom(result);
    }

    private static Message handleCooking(String username, Message message) {
        Result result = FoodController.cooking(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleCrafting(String username, Message message) {
        Result result = CraftingController.crafting(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleBuy(String username, Message message) {
        Result result = ShopController.buy(
            username,
            message.getFromBody("itemName"),
            message.getFromBody("name"),
            message.getFromBody("price"),
            message.getFromBody("locationString")
        );
        return makeResponseFrom(result);
    }

    private static Message handlePlanting(String username, Message message) {
        Result result = PlantsController.planting(
            username,
            message.getFromBody("itemName"),
            message.getFromBody("direction")
        );
        return makeResponseFrom(result);
    }

    private static Message handleEquipTool(String username, Message message) {
        Result result = ToolsController.equipTool(
            username,
            message.getFromBody("toolName")
        );
        return makeResponseFrom(result);
    }

    private static Message handleTrashCan(String username, Message message) {
        Result result = ToolsController.throwInTrash(
            username,
            message.getFromBody("trashItem"),
            message.getFromBody("trashNumber")
        );
        return makeResponseFrom(result);
    }

    private static Message handleMeetsNPC(String username, Message message) {
        Result result = NpcController.meetsNpc(
            username,
            message.getFromBody("NPCname")

        );
        return makeResponseFrom(result);
    }

    private static Message handleQuestList(String username, Message message) {
        Result result = NpcController.questList(
            username,
            message.getFromBody("npcName")
        );
        return makeResponseFrom(result);
    }

    private static Message handleFriendShipNpc(String username, Message message) {
        Result result = NpcController.friendShipNpc(
            username,
            message.getFromBody("npcName")
        );
        return makeResponseFrom(result);
    }

    private static Message handleGiftNpc(String username, Message message) {
        Result result = NpcController.giftNpc(
            username,
            message.getFromBody("npcName"),
            message.getFromBody("itemName")
        );
        return makeResponseFrom(result);
    }

    private static Message handleQuestFinish(String username, Message message) {
        Result result = NpcController.questFinish(
            username,
            message.getFromBody("questNumber"),
            message.getFromBody("npcName")
        );
        return makeResponseFrom(result);
    }

    private static Message handleShowAnimal(String username, Message message) {
        Result result = AnimalController.showAnimal(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleFeedAnimal(String username, Message message) {
        Result result = AnimalController.feedAnimal(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handlePet(String username, Message message) {
        Result result = AnimalController.pet(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleMoveAnimal(String username, Message message) {
        Result result = AnimalController.preMoveAnimal(
            username,
            message.getFromBody("name"),
            message.getFromBody("location")
        );
        return makeResponseFrom(result);
    }

    private static Message handleGetAnimalProduce(String username, Message message) {
        Result result = AnimalController.getAnimalProduce(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleSellAnimal(String username, Message message) {
        Result result = AnimalController.sellAnimal(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleGameUsers(String username, Message message){
        Result result = FriendShipController.showFriendships(
            username
        );
        return makeResponseFrom(result);
    }

    private static Message handleTalk(String username, Message message) {
        Result result = FriendShipController.talk(
            username,
            message.getFromBody("username"),
            message.getFromBody("talkingMessage")
        );
        return makeResponseFrom(result);
    }

    private static Message handleTalkHistory(String username, Message message) {
        Result result = FriendShipController.showTalkHistory(
            username,
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }
}
