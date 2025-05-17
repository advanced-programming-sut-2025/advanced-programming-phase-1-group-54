package controller.Game;

import model.App;
import model.Quest;
import model.Result;
import model.enums.Season;
import model.enums.Weather;
import model.items.Item;
import model.items.recipes.Recipe;
import model.lives.NPC;
import model.lives.Player;
import model.map.World;
import model.relationships.NPCFriendship;
import view.game.NPCMenu;

import java.util.ArrayList;
import java.util.Random;

public class NpcController {
    private static Random rand = new Random();
    public static void resetNpcEveryDay(){
        for(Player player : App.getCurrentGame().getPlayers()){
            for (NPCFriendship npcFriendship : player.getNpcFriendships()) {
                npcFriendship.setDailyGift(0);
                npcFriendship.setDailyTalkTime(0);
            }
        }
        for(NPC npc : App.getCurrentGame().getWorld().getNpcs()){
            npc.checkCounter();
        }
    }
    public static ArrayList<String> friendShipNpcList(){
        ArrayList<String> npcList = new ArrayList<>();
        String temp = "NPC name             friendship level            friendship XP";
        npcList.add(temp);
        for (NPCFriendship npcFriendship : App.getCurrentGame().getCurrentPlayer().getNpcFriendships()){
            String s = npcFriendship.getNpc().getName() + "             " + npcFriendship.getLevel() + "         " + npcFriendship.getXP();
            npcList.add(s);
        }
        return npcList;
    }
    public static Result meetsNpc(String npcName){
        NPC npc = getNPCByName(npcName);
        //todo check distance
        if (npc == null){
            return new Result(false, "NPC not found");
        }
        NPCFriendship npcFriendship = getNPCFriendship(npcName);
        if(npcFriendship.getDailyTalkTime() == 0){
            npcFriendship.increaseXP(20);
            npcFriendship.incresmentDailyTalkTime();
        }
        int randomNum = rand.nextInt(4);
        switch (randomNum){
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
        switch (randomNum){
            case 0:
                return new Result(true, "Hey. If you’re looking for something, make it quick. I’ve got work to do.");
            case 1:
                return new Result(true,"Oh, it’s you. Just finished something new—maybe I’ll show you one day.");
            case 2:
                return new Result(true,"Glad you stopped by. You’ve got good energy. Want to hangout later?");
            case 3:
                return new Result(true,"Sometimes I feel like you’re the only one who really gets me. The world feels a little lighter when you’re around.");
        }
        return null;
    }

    private static Result getDialogByWeather() {
        switch (App.getCurrentGame().getCurrentWeather()){
            case Weather.SUNNY:
                return new Result(true, "The light today’s perfect. Good visibility makes for cleaner work");
            case Weather.RAIN:
                return new Result(true,"Rain’s got a rhythm to it. Puts me in the zone. You ever try working to the sound of rain?");
            case Weather.STORM:
                return new Result(true,"");
            case Weather.SNOW:
                return new Result(true,"Everything's quiet under the snow. Feels like the world’s holding its breath—perfect time to create.");
        }
        return null;
    }

    private static Result getDialogBySeason() {
        switch (App.getCurrentGame().getDateTime().getSeason()){
            case Season.SPRING :
                return new Result(true,"Spring brings new life—and new inspiration. I always get the itch to make something fresh.");
            case Season.SUMMER:
                return new Result(true,"Working in this heat is brutal... but nothing worth making comes easy");
            case Season.FALL:
                return new Result(true,"Fall’s my favorite season. The colors, the chill, the pumpkin pie... it all just fits.");
            case Season.WINTER:
                return new Result(true,"Winter’s rough on the hands. Wool helps, though—I keep a bundle close by");
        }
        return null;
    }

    private static Result getDialogByDayHour() {
        int hour = App.getCurrentGame().getDateTime().getHour();
        if(hour > 8 && hour < 12 ){
            return new Result(true,"Morning. My brain’s not fully online yet… but the work won’t wait.");
        }
        else if(hour > 11 && hour < 15){
            return new Result(true,"It’s already noon and I’m only halfway through my list. Guess lunch can wait.");
        }
        else if(hour > 14 && hour < 18){
            return new Result(true,"It was a productive day. Now, if I had a slice of pizza, that’d make it perfect.");
        }
        else {
            return new Result(true,"Nights are the best time to design. It’s quiet… just me and the ideas.");
        }
    }

    public static NPC getNPCByName(String npcName){
        for(NPC npc : App.getCurrentGame().getWorld().getNpcs()){
            if(npc.getName().equals(npcName)){
                return npc;
            }
        }
        return null;
    }
    public static NPCFriendship getNPCFriendship(String npcName){
        for (NPCFriendship npcFriendship : App.getCurrentGame().getCurrentPlayer().getNpcFriendships()){
            if(npcFriendship.getNpc().getName().equals(npcName)){
                return npcFriendship;
            }
        }
        return null;
    }

    public static Result giftNpc(String npcName, String itemName) {
        NPC npc = getNPCByName(npcName);
        if(npc == null){
            return new Result(false,"NPC not found");
        }
        Item item = CommonGameController.findItem(itemName);
        if(item == null){
            return new Result(false,"Item not found");
        }
        if(CommonGameController.removeItemFromInventory(item,1) == false){
            return new Result(false,"you don't have such item");
        }
        NPCFriendship npcFriendship = getNPCFriendship(npc.getName());
        if(npcFriendship.getDailyGift() == 0){
            for (String st : npc.getFavoriteItems())
                if (item.getName().contains(st)){
                    npcFriendship.increaseXP(200);
                    npcFriendship.increaseDailyGift();
                    return new Result(true,"gift sent successfully and " + npcName + " liked it" );
                }
            npcFriendship.increaseXP(50);
            npcFriendship.increaseDailyGift();
            return new Result(true,"gift sent successfully");
        }
        else{
            return new Result(true,"gift sent successfully");
        }
    }

    public static ArrayList<String> questList(NPC npc) {
        ArrayList<String> list = new ArrayList<>();
        NPCFriendship npcFriendship = NpcController.getNPCFriendship(npc.getName());
        list.add("required item: count          reward item: count");
        for(Quest quest : npc.getAllQuests()){
            if(!quest.isCompleted() && (quest.isActive() || npcFriendship.getLevel() > 1)){
                String st = quest.getRequestedItem() + ": " + quest.getRequestedItemCount() + "             " + quest.getReward() + ": " + quest.getRewardCount();
                list.add(st);
            }
        }
        if (list.size() == 1){
            list = new ArrayList<>();
            list.add("there is no quest");
            return list;
        }
        return list;
    }

    public static Result questFinish(int i) {
        NPC npc = NPCMenu.getNpc();
        NPCFriendship npcFriendship = getNPCFriendship(npc.getName());
        if (i < 1 || i > npc.getAllQuests().size()){
            return new Result(false,"choose correct index");
        }
        int count = i;
        Quest quest = null;
        for (int j = 0 ; j < npc.getAllQuests().size(); j++){

            if (!quest.isCompleted() && (npc.getAllQuests().get(j).isActive() || npcFriendship.getLevel() > 1)) {
                count--;
            }
            if (count == 0){
                quest = npc.getAllQuests().get(j);
            }
        }
        if (quest == null){
            return new Result(false,"choose correct index");
        }
        Item item = CommonGameController.findItem(quest.getRequestedItem());
        if(CommonGameController.removeItemFromInventory(item,quest.getRequestedItemCount()) == false){
            return new Result(false,"not enough item");
        }
        if (quest.getReward().equals("Coin")){
            App.getCurrentGame().getCurrentPlayer().increaseEnergy(quest.getRewardCount());
        }
        else if (quest.getReward().equals("friendShip")){
            npcFriendship.increaseXP(200);
        }
        else if(quest.getReward().equals("Salmon Dinner Recipe")){
            Recipe recipe = Recipe.foodRecipes.get("Salmon Dinner Recipe");
            App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes().add(recipe);
        }
        else{
            Item temp = CommonGameController.findItem(quest.getReward());
            App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp, quest.getRewardCount());
        }
        quest.setCompleted(true);
        return new Result(true,"quest finished");
    }
}
