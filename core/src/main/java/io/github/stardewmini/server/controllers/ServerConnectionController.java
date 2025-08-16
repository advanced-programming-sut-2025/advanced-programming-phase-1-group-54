package io.github.stardewmini.server.controllers;


import io.github.stardewmini.Main;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.LobbyInfo;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.Gender;
import io.github.stardewmini.server.app.App;
import io.github.stardewmini.server.app.ServerApp;
import io.github.stardewmini.server.controllers.game.*;
import io.github.stardewmini.server.model.Lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            case "host_lobby":
                return handleCreateLobby(username, message);
            case "join_lobby":
                return handleJoinLobby(username, message);
            case "leave_lobby":
                return handleLeaveLobby(username, message);
            case "refresh_lobby_list":
                return handleRefreshLobbyList();
            case "find_lobby":
                return handleFindLobby(message);
            case "start_game":
                return handleStartGame(username, message);


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


            case "game_usernames":
                return handleGameUsers(username, message);
            case "talk":
                return handleTalk(username, message);
            case "talk_history":
                return handleTalkHistory(username, message);

            case "get_emojis":
                return handleGetEmojis(username, message);
            case "add_emojis":
                return handleAddEmojis(username, message);
            case "remove_emojis":
                return handleRemoveEmojis(username, message);
            case "select_emojis":
                return handleSelectEmojis(username, message);


            case "advance_time":
                return handleAdvanceTime(username, message);
            case "advance_date":
                return handleAdvanceDate(username, message);
            case "thunder_strike":
                return handleThunderStrike(username, message);
            case "set_energy":
                return handleSetEnergy(username, message);
            case "move_player":
                return handleMovePlayer(username, message);

            default:
                return null;
//                return GameCommandController.handleCommand(username, message);
        }
    }

    private static Message handleStartGame(String username, Message message) {
        int lobbyId = message.getIntFromBody("id");
        if (!App.getLobbyById(lobbyId).getAdmin().getUsername().equals(username)) {
            return makeResponseFrom(new Result(false, "Only admin can start game."));
        }

        UpdateController.chooseMap(lobbyId);
        return makeResponseFrom(new Result(true, "Starting Game ..."));
    }

    public static void handleUpdate(String username, Message message) {
        // TODO
    }

    private static Message handleFindLobby(Message message) {
        HashMap<String, Object> body = new HashMap<>();
        Lobby foundLobby = App.getLobbyById(message.getIntFromBody("id"));
        List<Map<String, Object>> lobbyInfos = new ArrayList<>();

        if (foundLobby.isVisible()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", foundLobby.getName());
            map.put("id", foundLobby.getId());
            map.put("isPrivate", foundLobby.isPrivate());
            lobbyInfos.add(map);
        }

        body.put("lobbies", lobbyInfos);
        return new Message(body, Message.Type.response);
    }

    private static Message handleRefreshLobbyList() {
        List<Lobby> lobbies = App.getLobbies();
        HashMap<String, Object> body = new HashMap<>();
        List<Map<String, Object>> lobbyInfos = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            if (lobby.isVisible()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", lobby.getName());
                map.put("id", lobby.getId());
                map.put("isPrivate", lobby.isPrivate());
                lobbyInfos.add(map);
            }
        }
        body.put("lobbies", lobbyInfos);
        return new Message(body, Message.Type.response);
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
            Gender.valueOf(message.getFromBody("gender"))
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

    private static Message handleCreateLobby(String username, Message message) {
        return LobbyController.createLobby(
            username,
            message.getFromBody("name"),
            message.getFromBody("password"),
            message.getBooleanFromBody("invisible"));
    }

    private static Message handleJoinLobby(String username, Message message) {
        Result result = LobbyController.joinLobby(
            username,
            message.getFromBody("id"),
            message.getFromBody("password"));
        return makeResponseFrom(result);
    }

    private static Message handleLeaveLobby(String username, Message message) {
        Result result = LobbyController.leaveLobby(
            username,
            message.getFromBody("id"));
        return makeResponseFrom(result);
    }

    private static Message handleUseTool(String username, Message message) {
        Result result = ToolsController.useTool(
            username,
            message.getFromBody("direction")
        );
        ServerApp.addDiff(UpdateController.createUseTool(username, message.getFromBody("direction")));
        return makeResponseFrom(result);
    }

    private static Message handleCooking(String username, Message message) {
        Result result = FoodController.cooking(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createCooking(username, message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handleCrafting(String username, Message message) {
        Result result = CraftingController.crafting(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createCrafting(username, message.getFromBody("name")));
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
        ServerApp.addDiff(UpdateController.createBuy(
            username,
            message.getFromBody("itemName"),
            message.getFromBody("name"),
            message.getFromBody("price"),
            message.getFromBody("locationString")));
        return makeResponseFrom(result);
    }

    private static Message handlePlanting(String username, Message message) {
        Result result = PlantsController.planting(
            username,
            message.getFromBody("itemName"),
            message.getFromBody("direction")
        );
        ServerApp.addDiff(UpdateController.createPlanting(username,
            message.getFromBody("itemName"),
            message.getFromBody("direction")));
        return makeResponseFrom(result);
    }

    private static Message handleEquipTool(String username, Message message) {
        Result result = ToolsController.equipTool(
            username,
            message.getFromBody("toolName")
        );
        ServerApp.addDiff(UpdateController.createEquipTool(username,
            message.getFromBody("toolName")));
        return makeResponseFrom(result);
    }

    private static Message handleTrashCan(String username, Message message) {
        Result result = ToolsController.throwInTrash(
            username,
            message.getFromBody("trashItem"),
            message.getFromBody("trashNumber")
        );
        ServerApp.addDiff(UpdateController.createTrashCan(username,
            message.getFromBody("trashItem"),
            message.getFromBody("trashNumber")));
        return makeResponseFrom(result);
    }

    private static Message handleMeetsNPC(String username, Message message) {
        Result result = NpcController.meetsNpc(
            username,
            message.getFromBody("NPCname")

        );
        ServerApp.addDiff(UpdateController.createMeetsNPC(username,
            message.getFromBody("NPCname")));
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
        ServerApp.addDiff(UpdateController.createGiftNpc(username,
            message.getFromBody("npcName"),
            message.getFromBody("itemName")));
        return makeResponseFrom(result);
    }

    private static Message handleQuestFinish(String username, Message message) {
        Result result = NpcController.questFinish(
            username,
            message.getFromBody("questNumber"),
            message.getFromBody("npcName")
        );
        ServerApp.addDiff(UpdateController.createQuestFinish(username,
            message.getFromBody("questNumber"),
            message.getFromBody("npcName")));
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
        ServerApp.addDiff(UpdateController.createFeedAnimal(username,
            message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handlePet(String username, Message message) {
        Result result = AnimalController.pet(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createPet(username,
            message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handleMoveAnimal(String username, Message message) {
        Result result = AnimalController.preMoveAnimal(
            username,
            message.getFromBody("name"),
            message.getFromBody("location")
        );
        ServerApp.addDiff(UpdateController.createMoveAnimal(username,
            message.getFromBody("name"),
            message.getFromBody("location")));
        return makeResponseFrom(result);
    }

    private static Message handleGetAnimalProduce(String username, Message message) {
        Result result = AnimalController.getAnimalProduce(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createGetAnimalProduce(username,
            message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handleSellAnimal(String username, Message message) {
        Result result = AnimalController.sellAnimal(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createSellAnimal(username,
            message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handleGameUsers(String username, Message message) {
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
        ServerApp.addDiff(UpdateController.createTalk(username,
            message.getFromBody("username"),
            message.getFromBody("talkingMessage")));
        if(result.code() == 5){
            ServerApp.addDiff(UpdateController.createTag(username,
                message.getFromBody("talkingMessage")));
        }
        return makeResponseFrom(result);
    }

    private static Message handleTalkHistory(String username, Message message) {
        Result result = FriendShipController.showTalkHistory(
            username,
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleGetEmojis(String username, Message message) {
        Result result = EmojiController.getEmojis(
            username
        );
        return makeResponseFrom(result);
    }

    private static Message handleAddEmojis(String username, Message message) {
        Result result = EmojiController.addEmojis(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleRemoveEmojis(String username, Message message) {
        Result result = EmojiController.removeEmojis(
            username,
            message.getFromBody("name")
        );
        return makeResponseFrom(result);
    }

    private static Message handleSelectEmojis(String username, Message message) {
        Result result = EmojiController.showEmojis(
            username,
            message.getFromBody("name")
        );
        ServerApp.addDiff(UpdateController.createSelectEmojis(username,
            message.getFromBody("name")));
        return makeResponseFrom(result);
    }

    private static Message handleAdvanceTime(String username, Message message) {
        Result result = CheatController.advanceTime(
            message.getFromBody("string")
        );
        ServerApp.addDiff(UpdateController.createAdvanceTime(message.getFromBody("string")));
        return makeResponseFrom(result);
    }

    private static Message handleAdvanceDate(String username, Message message) {
        Result result = CheatController.advanceDate(
            message.getFromBody("string")
        );
        ServerApp.addDiff(UpdateController.createAdvanceDate(message.getFromBody("string")));
        return makeResponseFrom(result);
    }

    private static Message handleThunderStrike(String username, Message message) {
        Result result = CheatController.thunderStrike(
            username,
            message.getFromBody("string")
        );
        ServerApp.addDiff(UpdateController.createThunderStrike(username,
            message.getFromBody("string")));
        return makeResponseFrom(result);
    }

    private static Message handleSetEnergy(String username, Message message) {
        Result result = CheatController.setEnergy(
            username,
            message.getFromBody("string")
        );
        ServerApp.addDiff(UpdateController.createSetEnergy(username,
            message.getFromBody("string")));
        return makeResponseFrom(result);
    }

    private static Message handleMovePlayer(String username, Message message) {
        Result result = PlayerController.walk(username,
            message.getFromBody("dy"),
            message.getFromBody("dx"));
        ServerApp.addDiff(UpdateController.createMovePlayer(username,
            message.getFromBody("dy"),
            message.getFromBody("dx")));
        return makeResponseFrom(result);
    }
}
