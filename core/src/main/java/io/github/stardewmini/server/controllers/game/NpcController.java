package io.github.stardewmini.server.controllers.game;

import io.github.stardewmini.common.model.Quest;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.Season;
import io.github.stardewmini.common.model.enums.Weather;
import io.github.stardewmini.common.model.items.Item;
import io.github.stardewmini.common.model.items.recipes.Recipe;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.relationships.NPCFriendship;
import io.github.stardewmini.server.app.App;

import java.util.ArrayList;
import java.util.Random;

public class NpcController {
    private static Random rand = new Random();

    public static String friendShipNpc(String requester, NPC npc) {
        NPCFriendship friendship = getNPCFriendship(requester, npc.getName());
        return "friendship level : " + friendship.getLevel() + "friendship XP : " + friendship.getXP();
    }

    public static Result meetsNpc(String requester, String npcName) {
        NPC npc = getNPCByName(npcName);

        if (npc == null) {
            return new Result(false, "NPC not found");
        }

        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        if (!MapController.isNear(player.getCurrentLocation(), npc)) {
            return new Result(false,
                String.format("you should be next to %s to meet them.",
                    npcName));
        }

        NPCFriendship npcFriendship = getNPCFriendship(requester, npcName);
        if (npcFriendship.getDailyTalkTime() == 0) {
            npcFriendship.increaseXP(20);
            npcFriendship.increaseDailyTalkTime();
        }
        int randomNum = rand.nextInt(4);
        switch (randomNum) {
            case 0:
                return getDialogByDayHour();
            case 1:
                return getDialogBySeason();
            case 2:
                return getDialogByWeather();
            case 3:
                return getDialogByFriendShipLevel(npcFriendship);
        }
        return null;
    }

    private static Result getDialogByFriendShipLevel(NPCFriendship npcFriendship) {
        int randomNum = npcFriendship.getLevel();
        switch (randomNum) {
            case 0:
                return new Result(true, "Hey. If you're looking for something, make it quick. I've got work to do.");
            case 1:
                return new Result(true, "Oh, it’s you. Just finished something new—maybe I'll show you one day.");
            case 2:
                return new Result(true, "Glad you stopped by. You've got good energy. Want to hangout later?");
            case 3:
                return new Result(true, "Sometimes I feel like you're the only one who really gets me. The world feels a little lighter when you're around.");
        }
        return null;
    }

    private static Result getDialogByWeather() {
        switch (App.getCurrentGame().getCurrentWeather()) {
            case Weather.SUNNY:
                return new Result(true, "The light today's perfect. Good visibility makes for cleaner work");
            case Weather.RAIN:
                return new Result(true, "Rain's got a rhythm to it. Puts me in the zone. You ever try working to the sound of rain?");
            case Weather.STORM:
                return new Result(true, "it's bad Weather.");
            case Weather.SNOW:
                return new Result(true, "Everything's quiet under the snow. Feels like the world's holding its breath—perfect time to create.");
        }
        return null;
    }

    private static Result getDialogBySeason() {
        switch (App.getCurrentGame().getDateTime().getSeason()) {
            case Season.SPRING:
                return new Result(true, "Spring brings new life and new inspiration. I always get the itch to make something fresh.");
            case Season.SUMMER:
                return new Result(true, "Working in this heat is brutal... but nothing worth making comes easy");
            case Season.FALL:
                return new Result(true, "Fall’s my favorite season. The colors, the chill, the pumpkin pie... it all just fits.");
            case Season.WINTER:
                return new Result(true, "Winter’s rough on the hands. Wool helps, though—I keep a bundle close by");
        }
        return null;
    }

    private static Result getDialogByDayHour() {
        int hour = App.getCurrentGame().getDateTime().getHour();
        if (hour > 8 && hour < 12) {
            return new Result(true, "Morning. My brain's not fully online yet... but the work won't wait.");
        } else if (hour > 11 && hour < 15) {
            return new Result(true, "It's already noon and I'm only halfway through my list. Guess lunch can wait.");
        } else if (hour > 14 && hour < 18) {
            return new Result(true, "It was a productive day. Now, if I had a slice of pizza, that'd make it perfect.");
        } else {
            return new Result(true, "Nights are the best time to design. It's quiet... just me and the ideas.");
        }
    }

    public static NPC getNPCByName(String npcName) {
        for (NPC npc : App.getCurrentGame().getWorld().getNpcs()) {
            if (npc.getName().equals(npcName)) {
                return npc;
            }
        }
        return null;
    }

    public static NPCFriendship getNPCFriendship(String requester, String npcName) {
        for (NPCFriendship npcFriendship : App.getCurrentGame().getPlayerByUsername(requester).getNpcFriendships()) {
            if (npcFriendship.getNpc().getName().equals(npcName)) {
                return npcFriendship;
            }
        }
        return null;
    }

    public static Result getNPCsFriendship(String requester) {
        StringBuilder output = new StringBuilder();
        for (NPCFriendship npcFriendship : App.getCurrentGame().getPlayerByUsername(requester).getNpcFriendships()) {
            output.append(npcFriendship.getNpc().getName()).append(" Level : ").append(npcFriendship.getLevel()).
                append(" XP : ").append(npcFriendship.getXP()).append("\n");
        }
        return new Result(true, output.toString());
    }

    public static Result giftNpc(String requester, NPC npc, String itemName) {
        if (npc == null) {
            return new Result(false, "NPC not found");
        }
        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "Item not found");
        }
        if (CommonGameController.removeItemFromInventory(requester,item, 1) == false) {
            return new Result(false, "you don't have such item");
        }
        npc.setAnimationTime(0);
        NPCFriendship npcFriendship = getNPCFriendship(requester, npc.getName());
        if (npcFriendship.getDailyGift() == 0) {
            for (String st : npc.getFavoriteItems())
                if (item.getName().contains(st)) {
                    npcFriendship.increaseXP(200);
                    npcFriendship.increaseDailyGift();
                    return new Result(true, "gift sent successfully and " + npc.getName() + " liked it");
                }
            npcFriendship.increaseXP(50);
            npcFriendship.increaseDailyGift();
            return new Result(true, "gift sent successfully");
        } else {
            return new Result(true, "gift sent successfully");
        }
    }

    public static Result questList(String requester, NPC npc) {
        ArrayList<String> list = new ArrayList<>();
        NPCFriendship npcFriendship = NpcController.getNPCFriendship(requester, npc.getName());
        list.add("Quest num  req: count  rew: count");
        int questCount = npc.getAllQuests().size();
        if (questCount == 0) {
            return new Result(false, "NPC doesn't have any quest");
        }
        Quest quest = null;
        for (int i = 0; i < questCount; i++) {
            quest = npc.getAllQuests().get(i);
            if (!quest.isCompleted() && quest.isActive()) {
                String st = "Quest" + (i + 1) + "  " + quest.getRequestedItem() + ": " + quest.getRequestedItemCount() +
                    "  " + quest.getReward() + ": " + quest.getRewardCount();
                list.add(st);
            }
        }
        if (npcFriendship.getLevel() >= 1 && (!quest.isCompleted())) {
            list.add("Quest" + questCount + "  " + quest.getRequestedItem() + ": " + quest.getRequestedItemCount() +
                "         " + quest.getReward() + ": " + quest.getRewardCount());
        }
        if (list.size() == 1) {
            list = new ArrayList<>();
            list.add("there is no quest active");
        }
        return new Result(true, String.join("\n", list));
    }

    public static Result questFinish(String requester, String number, NPC npc) {
        int i;
        try {
            i = Integer.parseInt(number);
        } catch (Exception e) {
            return new Result(false, "Enter correct number");
        }
        NPCFriendship npcFriendship = getNPCFriendship(requester, npc.getName());
        if (i < 1 || i > npc.getAllQuests().size()) {
            return new Result(false, "choose correct index");
        }
//        int count = i;
        Quest quest = npc.getAllQuests().get(i - 1);
//        for (int j = 0 ; j < npc.getAllQuests().size(); j++){
//            quest = npc.getAllQuests().get(j);
//            if (!quest.isCompleted() && (quest.isActive() || npcFriendship.getLevel() > 1)) {
//                count--;
//            }
//            if (count == 0){
//                quest = npc.getAllQuests().get(j);
//            }
//        }
        if (quest == npc.getAllQuests().getLast()) {
            if (npcFriendship.getLevel() < 1 || quest.isCompleted()) {
                return new Result(false, "choose correct index");
            }
        } else if (quest == null || quest.isCompleted() || (!quest.isActive())) {
            return new Result(false, "choose correct index");
        }

        Item item = CommonGameController.findItem(quest.getRequestedItem());
        if (!CommonGameController.removeItemFromInventory(requester,item, quest.getRequestedItemCount())) {
            return new Result(false, "not enough item");
        }

        if (quest.getReward().equals("Coin")) {
            App.getCurrentGame().getPlayerByUsername(requester).increaseEnergy(quest.getRewardCount());
        } else if (quest.getReward().equals("friendShip")) {
            npcFriendship.increaseXP(200);
        } else if (quest.getReward().equals("Salmon Dinner Recipe")) {
            // TODO please don't use public fields !!!
            Recipe recipe = Recipe.foodRecipes.get("Salmon Dinner Recipe");
            App.getCurrentGame().getPlayerByUsername(requester).getLearnedFoodRecipes().add(recipe);
        } else {
            Item temp = CommonGameController.findItem(quest.getReward());
            App.getCurrentGame().getPlayerByUsername(requester).getBackpack().addItem(temp, quest.getRewardCount());
        }
        quest.setCompleted(true);
        return new Result(true, "quest finished");
    }

/*
    public static void giftAnimation(NPC npc, float delta) {
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getNPCsGifts(npc.getName());
        if(! animation.isAnimationFinished(npc.getAnimationTime())){
            npc.getSprite().setRegion(animation.getKeyFrame(npc.getAnimationTime()));
            npc.setAnimationTime(npc.getAnimationTime() + delta);
            animation.setPlayMode(Animation.PlayMode.NORMAL);
        }
    }

    public static void update(float delta) {
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ;i < npcHouses.size() ; i++){
            NPC npc = npcHouses.get(i).getNpc();
            giftAnimation(npc,delta);
            if(npc.getDialogTime() < NPC.dialogTiming){
                npc.setDialogTime(npc.getDialogTime() + delta);
            }
        }
    }

    public static void draw(SpriteBatch batch,Window[] windows) {
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ; i < npcHouses.size() ; i++){
            NPC npc = npcHouses.get(i).getNpc();
            if(npc.getDialogTime() >= NPC.dialogTiming){
                windows[i].draw(batch,1f);
            }
            else if(npc.getDialogTime() < 10){
                windows[i].draw(batch,1f);
            }
            else {
                windows[i].getTitleLabel().setText("dialog");
            }
        }
    }

    public static void fixWindows(Window[] windows){
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ; i < windows.length ; i++){
            windows[i].setPosition(npcHouses.get(i).getNpc().getLocation().column() * Tile.getSize(),
                (npcHouses.get(i).getNpc().getLocation().row() + 1) * Tile.getSize());
        }
    }
*/

}
