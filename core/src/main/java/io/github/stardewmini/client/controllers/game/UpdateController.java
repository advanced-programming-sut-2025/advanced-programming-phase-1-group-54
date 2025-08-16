package io.github.stardewmini.client.controllers.game;

import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.enums.*;
import io.github.stardewmini.common.model.items.Food;
import io.github.stardewmini.common.model.items.Item;
import io.github.stardewmini.common.model.items.Material;
import io.github.stardewmini.common.model.items.crafting.Artisan;
import io.github.stardewmini.common.model.items.crafting.FeatureArtisan;
import io.github.stardewmini.common.model.items.crafting.ProducerArtisan;
import io.github.stardewmini.common.model.items.plants.*;
import io.github.stardewmini.common.model.items.recipes.Recipe;
import io.github.stardewmini.common.model.items.tools.BackPack;
import io.github.stardewmini.common.model.items.tools.Tool;
import io.github.stardewmini.common.model.items.tools.TrashCan;
import io.github.stardewmini.common.model.items.tools.WateringCan;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.*;
import io.github.stardewmini.common.model.relationships.NPCFriendship;
import io.github.stardewmini.common.model.relationships.Relationship;
import io.github.stardewmini.common.model.relationships.Talk;
import io.github.stardewmini.client.app.App;

public class UpdateController {

    public static Result setEnergy(String requester, String valueString) {
        int value;
        try {
            value = Integer.parseInt(valueString);
        } catch (Exception e){
            return new Result(false,"enter only numbers");
        }
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.setUnlimitedEnergy(false);
        player.setEnergy(value);
        return new Result(true, "You suddenly feel weird, as if you're energy has changed!");
    }

    public static Result thunderStrike(String requester, String locationString) {
        int x,y;
        Location location;
        try{
            String[] locParts = locationString.split(",");
            x = Integer.parseInt(locParts[0]);
            y = Integer.parseInt(locParts[1]);
            location = new Location(y,x);
        }catch (Exception e){
            return new Result(false,"enter location in X,Y format");
        }

        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);
        Farm farm = game.getWorld().getFarmAt(player.getCurrentLocation());

        if (farm == null) {
            return new Result(false, "you must be in a farm to call Thor!");
        }

        if (farm.getTileAt(location.delta(farm.getLocation())) == null) {
            return new Result(false, "Thor will only cast thunder on a location in the farm you're standing!");
        }

        farm.thunderStrike(location);
        return new Result(true, "Thor is satisfied!");
    }

    public static Result advanceDate(String string) {
        int x;
        try{
            x = Integer.parseInt(string);
        }catch (Exception e){
            return new Result(false,"only enter numbers");
        }
        Game game = App.getCurrentGame();
        game.getDateTime().increaseDay(x);
        return new Result(true, "it's now " + game.getDateTime().toString());
    }

    public static Result advanceTime(String string) {
        int x;
        try{
            x = Integer.parseInt(string);
        }catch (Exception e){
            return new Result(false,"only enter numbers");
        }
        Game game = App.getCurrentGame();
        game.getDateTime().increaseHour(x);
        return new Result(true, "it's now " + game.getDateTime().toString());
    }


    public static Result showEmojis(String requester, String emoji){
        Player player = io.github.stardewmini.client.app.App.getCurrentGame().getPlayerByUsername(requester);
        player.setEmojiName(emoji);
        player.setEmojiTime(0);
        return new Result(true,"Emoji" + emoji +" showed");
    }

    public static Result talk(String requester, String username, String message) {
        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);
        Player otherPlayer = game.getPlayerByUsername(username);
        if (otherPlayer == null) {
            return new Result(false, "no such user was found");
        }
        Relationship relationship = game.getRelationship(player, otherPlayer);

        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "user not found");
        }

//        if (!MapController.isNear(player.getCurrentLocation(), otherPlayer))
//            return new Result(false, "you must be next to each other to talk to each other!");

        Talk talk = new Talk(App.getCurrentGame().getPlayerByUsername(requester), message, new DateTime(App.getCurrentGame().getDateTime()));
        relationship.getTalkHistory().add(talk);
        if (relationship.getTalkDailyCount() == 0) {
            relationship.increaseXP(20);
        }
        relationship.increaseTalkDailyCount();
        return new Result(true, "message sent");
    }


    private static void deleteAnimalFromFarm(String requester, Animal animal) {
        if(animal.getLocation() != null ) {
            Tile pastTile = App.getCurrentGame().getPlayerByUsername(requester).getFarm().getTileAt(animal.getLocation());
            if(pastTile.getThingOnTile() instanceof AnimalHouse pastAnimalHouse){
                pastAnimalHouse.decreaseNumberOfAnimals(1);
                pastTile = pastTile.getTop();
                if(! animal.isGoneOut()){
                    animal.setEatTime(0);
                }
            }
            pastTile.setThingOnTile(null);
        }
    }

    public static Result sellAnimal(String requester, String animalName) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        player.getAnimals().remove(animal.getName());
        deleteAnimalFromFarm(requester, animal);
        int price = (int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000 + 0.3));
        player.increaseMoney((int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000) + 0.3));

        return new Result(1,"You sold " + animal.getName() + " for " + price + " money");
    }

    public static Result getAnimalProduce(String requester, String animalName) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        if(animal.getProduce() == null) {
            return new Result(-1, animal + " doesn't have any produce");
        }

        boolean enoughEnergy = true;
        if(animal.getName().equals("Cow") || animal.getName().equals("Goat")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.MILK_PAIL)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }
        else if(animal.getName().equals("Sheep")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.SHEAR)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }

        if(! player.getBackpack().addItem(animal.getProduce(),1)){
            return new Result(-1, "Backpack is full");
        }

        animal.setProduce(null);
        animal.increaseFriendshipLevel(5);
        player.getSkill(SkillType.FARMING).addXP(5);
        if(enoughEnergy){
            return new Result(1,"You got produce from " + animal.getName());
        }
        else {
            return new Result(1,"You got produce from " );
//                + animal.getName()  + ". " + CommonGameController.passOut(requester).message());
        }

    }

    public static Result moveAnimal(String requester, Animal animal, Location location) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);
        Location locationInFarm = location.delta(farm.getLocation());
        Tile tile = farm.getTileAt(locationInFarm);

        if(tile == null) {
            return new Result(-1, "location is not in your farm");
        }

        if(animal.getLocation() != null && animal.getLocation().distance(location) > 5){
            return new Result(false,"location is too far");
        }

        if(tile.getThingOnTile() == null){
            if(animal.getLocation() == null){
                animal.setX(locationInFarm.column() * Tile.getSize());
                animal.setY(locationInFarm.row() * Tile.getSize());
            }
            deleteAnimalFromFarm(requester, animal);
            tile.setThingOnTile(animal);
            animal.setLocation(locationInFarm);
            animal.setGoneOut(true);
        }
        else if(tile.getThingOnTile() instanceof AnimalHouse animalHouse){
            if(animalHouse.getSize() > animalHouse.getNumberOfAnimals() && tile.getTop().getThingOnTile() == null){
                if(animal.getLocation() == null){
                    animal.setX(locationInFarm.column() * Tile.getSize());
                    animal.setY(locationInFarm.row() * Tile.getSize());
                }
                deleteAnimalFromFarm(requester, animal);
                animalHouse.increaseNumberOfAnimals(1);
                animal.setLocation(locationInFarm);
                tile.getTop().setThingOnTile(animal);
            }
            else{
                return new Result(false, "Sorry, but there is no room for " + animal);
            }
        }
        else{
            return new Result(false, "Sorry, but there is no space on the tile");
        }

        animal.setWalkTime(0);
        return new Result(1,animal + " was moved successfully");
    }

    public static Result pet(String requester, String name) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Animal animal = player.getAnimals().get(name);
//        Animal animal = player.getAnimals().get(animalName);
//        if(animal == null) {
//            return new Result(-1, "You don't have any animal named " + animalName);
//        }
//        if(! MapController.isNear(player.getCurrentLocation(),animal)){
//            return new Result(-1, animal + " is not near you");
//        }
        animal.increaseFriendshipLevel(15);
        animal.setCaressed(true);
        animal.setPetTime(0);
        return new Result(1,animal + " slightly likes you more!");
    }

    public static Result feedAnimal(String requester, String animalName) {

        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        Item item = CommonGameController.findItem("Hay");
        if(! player.getBackpack().removeItem(item,1)){
            return new Result(-1, "You don't have any Hay in backpack");
        }

        animal.setFed(true);
        animal.setEatTime(0);
        return new Result(1,animal + " was fed successfully");
    }

    public static Result questFinish(String requester, String number, String npcName) {
        NPC npc = null;
        for(NPCHouse npcHouse : App.getCurrentGame().getWorld().getNpcHouses()){
            if(npcHouse.getNpc().getName().equals(npcName)){
                npc = npcHouse.getNpc();
            }
        }
        if(npc == null) {
            return new Result(false, "NPC not found");
        }
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
            Recipe recipe = Recipe.foodRecipes.get("Salmon Dinner Recipe");
            App.getCurrentGame().getPlayerByUsername(requester).getLearnedFoodRecipes().add(recipe);
        } else {
            Item temp = CommonGameController.findItem(quest.getReward());
            App.getCurrentGame().getPlayerByUsername(requester).getBackpack().addItem(temp, quest.getRewardCount());
        }
        quest.setCompleted(true);
        return new Result(true, "quest finished");
    }

    public static Result giftNpc(String requester, String npcName, String itemName) {
        NPC npc = null;
        for(NPCHouse npcHouse : App.getCurrentGame().getWorld().getNpcHouses()){
            if(npcHouse.getNpc().getName().equals(npcName)){
                npc = npcHouse.getNpc();
            }
        }
        if(npc == null) {
            return new Result(false, "NPC not found");
        }
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

    public static Result meetsNpc(String requester, String npcName) {
        NPC npc = getNPCByName(npcName);

        if (npc == null) {
            return new Result(false, "NPC not found");
        }

        Player player = App.getCurrentGame().getPlayerByUsername(requester);
//        if (!MapController.isNear(player.getCurrentLocation(), npc)) {
//            return new Result(false,
//                String.format("you should be next to %s to meet them.",
//                    npcName));
//        }

        NPCFriendship npcFriendship = getNPCFriendship(requester, npcName);
        if (npcFriendship.getDailyTalkTime() == 0) {
            npcFriendship.increaseXP(20);
            npcFriendship.increaseDailyTalkTime();
        }
        return new Result(true, "meets npc successfully");
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

    public static Result throwInTrash(String requester, String itemName, String numberString) {
        Integer number;
        try{
            number = Integer.parseInt(numberString);
        }catch (Exception e){
            return new Result(false, "only enter numbers in number box");
        }

        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);

        BackPack backPack = player.getBackpack();
        TrashCan trashCan = player.getTrashCan();

        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "Item not found");
        }
        if(number == null){
            number = backPack.getNumberOfItemInBackPack().get(item);
        }
        if (backPack.getNumberOfItemInBackPack().get(item) < number) {
            return new Result(false, String.format("You don't have %d of %s in backpack", number, itemName));
        }

        backPack.removeItem(item, number);
        int money = trashCan.getMoneyFromTrashCan(item, number);
        player.increaseMoney(money);

        if (money == 0)
            return new Result(true, String.format("%d of %s were thrown in trash", number, itemName));

        return new Result(true, String.format("%d of %s were thrown in trash, and you gained %d coins!", number, itemName, money));
    }

    public static Result equipTool(String requester, String toolName) {
        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);

        ToolType toolType = ToolType.fromString(toolName);
        if (toolType == null)
            return new Result(false, "no tool with this name can be equipped");

        player.setEquippedTool(player.getTool(toolType));

        if (player.getEquippedTool() == null) {
            return new Result(false, "you don't have this equipment");
        }

        return new Result(false, "tool equipped!");
    }

    public static Result useTool(String requester, Direction direction) {
        if (direction == null)
            return new Result(false, "invalid direction");

        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);
        Farm farm = game.getWorld().getFarmAt(player.getCurrentLocation());

        if (farm == null) {
            return new Result(false, "You must be in a farm to use your tools");
        }

        Location currentLocation = player.getCurrentLocation().delta(farm.getLocation());
        Location location = currentLocation.getLocationAt(direction).delta(farm.getLocation());
//        System.out.println(currentLocation);
//        System.out.println(location);
        Tile playerTile = farm.getTileAt(currentLocation);
        Tile tile = farm.getTileAt(location);

        if (playerTile == null) {
            return new Result(false, "you are not in your farm");
        }

        if (tile == null) {
            return new Result(false, "this tile doesn't exist in your farm");
        }

        if (playerTile.getThingOnTile() != null && playerTile.getThingOnTile() instanceof Building playerBuilding) {
            if (tile.getThingOnTile() == null || !tile.getThingOnTile().equals(playerBuilding)) {
                return new Result(false, "you should go out of building to use this tool on this tile");
            }
        }

        Tool equippedTool = player.getEquippedTool();
        if (equippedTool == null) {
            return new Result(false, "you don't have any tool equipped");
        }

        Result useToolDetail = useToolDetail(requester, direction);
        int energyNeeded = equippedTool.getEnergyNeededPerUse();

        if (!useToolDetail.success() && (equippedTool.getToolType() == ToolType.AXE
            || equippedTool.getToolType() == ToolType.PICKAXE)) {
            energyNeeded--;
        }

        boolean enoughEnergy = player.checkEnergy(energyNeeded, equippedTool.getSkillType());

        String message = String.format("tool was used, and you %s\n%s",
            (useToolDetail.success() ? "succeeded" : "failed"), useToolDetail.message());

        if (!enoughEnergy) {
            Result passOut = CommonGameController.passOut(requester);
            message += "\n" + passOut.message();
        }

        return new Result(useToolDetail.success(), message);
    }

    private static Result useToolDetail(String requester, Direction direction) {
        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);
        World world = game.getWorld();

        Location location = player.getCurrentLocation().getLocationAt(direction);
        Tile tile = world.getTileAt(location);

        BackPack backpack = player.getBackpack();
        Tool tool = player.getEquippedTool();

        switch (tool.getToolType()) {
            case HOE:
                return useHoe(tile);
            case WATERING_CAN:
                return useWateringCan(requester, tool, tile, location);
            case SCYTHE:
                return harvestPlant(requester, direction);
            case AXE:
                return useAxe(player, backpack, tile);
            case PICKAXE:
                return usePickaxe(player, tool, backpack, tile);
            default:
                return new Result(true, "nothing happened");
        }
    }

    private static Result useHoe(Tile tile) {
        if (tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        if (tile.getThingOnTile() != null) {
            if (tile.getThingOnTile() instanceof Building) {
                return new Result(false, "you can't plow a building");
            }

            return new Result(false, "there is something on this tile, you can't plow it");
        }

        if (tile.hasFeature(Feature.PLOWED))
            return new Result(true, "This tile has already been plowed");

        tile.addFeature(Feature.PLOWED);
        tile.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("Plowed Floor"));
        return new Result(true, "This tile has been plowed");
    }

    private static Result useWateringCan(String requester, Tool tool, Tile tile, Location location) {
        WateringCan wateringCan = (WateringCan) tool;

        if (tile.hasFeature(Feature.WATER)) {
            wateringCan.increaseWater();
            return new Result(true, "water increased by 1");
        }

        if (wateringCan.getCurrentWater() > 0) {
            wateringCan.decreaseWater();
            return giveWater(requester, location);
        }

        return new Result(false, "watering can is empty");
    }

    private static Result useAxe(Player player, BackPack backpack, Tile tile) {
        if (tile.getThingOnTile() instanceof Tree tree) {
            player.getSkill(SkillType.FORAGING).addXP(10);
            Result woodAdded = addToBackPack(backpack, Material.getMaterial("Wood"), 1);
            Result seedsAdded = addToBackPack(backpack, Seed.getSeed(tree.getSource()), 2);

            String message = "you cut " + tree.getName();
            if (woodAdded.success())
                message += "\n" + woodAdded.message();

            if (seedsAdded.success())
                message += "\n" + seedsAdded.message();

            return new Result(true, message);
        }
        if (tile.getThingOnTile() instanceof Material material) {
            if (material.getName().equals("Wood")) {
                player.getSkill(SkillType.FORAGING).addXP(10);
                Result woodAdded = addToBackPack(backpack, Material.getMaterial("Wood"), 1);


                String message = "you cut some " + material.getName();
                if (woodAdded.success())
                    message += "\n" + woodAdded.message();

                return new Result(true, message);
            }

            return new Result(false, "you can't cut this with an axe");
        }

        return new Result(true, "nothing happened");
    }

    private static Result usePickaxe(Player player, Tool tool, BackPack backpack, Tile tile) {
        World world = App.getCurrentGame().getWorld();

        Result result = null;

        if (tile.hasFeature(Feature.PLOWED)) {
            tile.removeFeature(Feature.PLOWED);
        }

        if (tile.getThingOnTile() instanceof Material rock) {
            switch (rock.getName()) {
                case "Wood":
                    player.getSkill(SkillType.MINING).addXP(10);
                    new Result(true, "item on tile destroyed");
                case "Stone", "Coal", "Copper Ore":
                    break;
                case "Iron Ore":
                    if (tool.getToolLevel() == ToolLevel.NORMAL) {
                        player.getSkill(SkillType.MINING).addXP(10);
                        return new Result(false, "Iron Ore can be mined with Copper or higher pickaxe");
                    }
                    break;
                case "Iridium Ore":
                    if (tool.getToolLevel() == ToolLevel.NORMAL ||
                        tool.getToolLevel() == ToolLevel.COPPER ||
                        tool.getToolLevel() == ToolLevel.IRON) {
                        player.getSkill(SkillType.MINING).addXP(10);
                        return new Result(false, "Iridium Ore can be mined with Gold or higher pickaxe");
                    }
                    break;
                default:
                    if (tool.getToolLevel() == ToolLevel.NORMAL ||
                        tool.getToolLevel() == ToolLevel.COPPER) {
                        player.getSkill(SkillType.MINING).addXP(10);
                        return new Result(false, rock.getName() + " can be mined with Iron or higher pickaxe");
                    }
                    break;
            }

            result = addToBackPack(player.getBackpack(), rock, 1);

            if (player.getSkill(SkillType.MINING).getLevel() >= 2) {
                Material material = Material.getForagingMaterial(App.getCurrentGame().getRng());
                Result materialAddedResult = addToBackPack(backpack, material, 1);
                result = new Result(true, result.message() + "\n" + materialAddedResult.message());
            }
        }

        boolean itemDestroyed = CommonGameController.deleteThingOnTile(player.getName(), tile, world.getFarmAt(player.getCurrentLocation()));

        if (result != null) {
            return result;
        }

        if (itemDestroyed)
            return new Result(true, "item on tile destroyed");

        return new Result(true, "nothing happened!");
    }

    public static Result howMuchWater(String requester) {
        Game game = App.getCurrentGame();
        Player player = game.getPlayerByUsername(requester);
        WateringCan wateringCan = (WateringCan) player.getTool(ToolType.WATERING_CAN);

        return new Result(true, "water: " + wateringCan.getCurrentWater());
    }

    static Result addToBackPack(BackPack backPack, Item item, int number) {
        if (backPack.addItem(item, number)) {
            return new Result(true, number + " of " + item.getName() + " added to backpack");
        }

        return new Result(false, "you gained " + number + " of " + item.getName() + ", but your backpack is full");
    }

    public static Result giveWater(String requester, Location location) {
        Game game = App.getCurrentGame();
        Farm farm = game.getWorld().getFarmAt(game.getPlayerByUsername(requester).getCurrentLocation());
        Tile tile = farm.getTileAt(location.delta(farm.getLocation()));

        if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        if (!(tile.getThingOnTile() instanceof Plant plant)) {
            return new Result(true, "You wasted some water");
        }

        plant.setWatered(true);

        if (plant instanceof Crop crop && crop.getGiantDirection() != null) {
            for(int i = 0 ; i < 3;i++){
                location = location.getLocationAt(crop.getGiantDirection());
                crop = (Crop) game.getWorld().getTileAt(location).getThingOnTile();
                crop.setWatered(true);
            }
        }

        return new Result(true, "You watered the plant on this tile");
    }

    public static Result planting(String requester, String seedName, String directionString) {
        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        } catch (Exception e) {
            return new Result(false, "enter correct direction");
        }
        if (direction == null)
            return new Result(false, "invalid direction");

        Seed seed;
        if (seedName.equals("Mixed Seeds")) {
            Result result = plantingSeeds(requester, Seed.getMixedSeed(App.getCurrentGame().getRng(),
                App.getCurrentGame().getDateTime().getSeason()), direction);
            if (result.success()) {
                if (!App.getCurrentGame().getPlayerByUsername(requester).getBackpack().
                    removeItem(Seed.getSeed("Mixed Seeds"), 1)) {
                    return new Result(-1, "you don't enough " + seedName);
                }
            }
            return result;
        } else {
            seed = Seed.getSeed(seedName);
            if (seed == null) {
                return new Result(-1, "seed does not exist");
            }
            Result result = plantingSeeds(requester, seed, direction);
            if (result.success()) {
                if (!App.getCurrentGame().getPlayerByUsername(requester).getBackpack().removeItem(seed, 1)) {
                    return new Result(-1, "you don't enough " + seedName);
                }
            }
            return result;
        }

    }

    private static Result plantingSeeds(String requester, Seed seed, Direction direction) {

        Player currentPlayer = App.getCurrentGame().getPlayerByUsername(requester);

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(currentPlayer.getCurrentLocation());
        if (farm == null) {
            return new Result(-1, "You are not in any farm");
        }

        Location location = currentPlayer.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation());
        Tile tile = farm.getTileAt(location);

        if (tile == null) {
            return new Result(-1, "tile is not in this farm");
        }

        boolean inGreenHouse = false;
        if (tile.getThingOnTile() instanceof GreenHouse) {
            inGreenHouse = true;
            tile = tile.getTop();
        }

        if (!tile.getFeatures().contains(Feature.PLOWED)) {
            return new Result(-1, "tile has not plowed");
        }

        if (tile.getThingOnTile() != null) {
            return new Result(-1, "tile already is full");
        }

        Tree tree = Tree.getTree(seed.getPlant());
        if (tree != null) {
            if(! inGreenHouse){
                if(! tree.containSeason(App.getCurrentGame().getDateTime().getSeason())) {
                    return new Result(-1, "You can't plant this plant in this season");
                }
            }
            if (tile.hasFeature(Feature.SPEED_FERTILIZE)) {
                tree.nextDayUpdate();
                tile.removeFeature(Feature.SPEED_FERTILIZE);
            }
            if (tile.hasFeature(Feature.WATER_FERTILIZE)) {
                tree.setFertilized(true);
                tile.removeFeature(Feature.WATER_FERTILIZE);
            }
            tile.setThingOnTile(tree);
            App.getCurrentGame().getDateTime().addDailyUpdateListener(tree);
        }

        Crop crop = Crop.getCrop(seed.getPlant());
        if (crop != null) {
            if(! inGreenHouse){
                if(! crop.containSeason(App.getCurrentGame().getDateTime().getSeason())) {
                    return new Result(-1, "You can't plant this plant in this season");
                }
            }
            if (tile.hasFeature(Feature.SPEED_FERTILIZE)) {
                crop.nextDayUpdate();
                tile.removeFeature(Feature.SPEED_FERTILIZE);
            }
            if (tile.hasFeature(Feature.WATER_FERTILIZE)) {
                crop.setFertilized(true);
                tile.removeFeature(Feature.WATER_FERTILIZE);
            }
            if (!cropCanBeGiant(crop, location)) {
                tile.setThingOnTile(crop);
                App.getCurrentGame().getDateTime().addDailyUpdateListener(crop);
            }
        }

        tile.removeFeature(Feature.PLOWED);

        return new Result(1, "Planted seed successfully");
    }

    private static boolean cropCanBeGiant(Crop crop, Location location) {
        if (!crop.isGiantPossible())
            return false;

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(location);

        Tile tile = farm.getTileAt(location);
        Tile upTile = farm.getTileAt(location.getLocationAt(Direction.UP));
        Tile downTile = farm.getTileAt(location.getLocationAt(Direction.DOWN));
        Tile leftTile = farm.getTileAt(location.getLocationAt(Direction.LEFT));
        Tile rightTile = farm.getTileAt(location.getLocationAt(Direction.RIGHT));
        Tile up_LeftTile = farm.getTileAt(location.getLocationAt(Direction.UP_LEFT));
        Tile down_LeftTile = farm.getTileAt(location.getLocationAt(Direction.DOWN_LEFT));
        Tile up_RightTile = farm.getTileAt(location.getLocationAt(Direction.UP_RIGHT));
        Tile down_RightTile = farm.getTileAt(location.getLocationAt(Direction.DOWN_RIGHT));

        Crop upCrop;
        Crop downCrop;
        Crop leftCrop;
        Crop rightCrop;
        Crop upLeftCrop;
        Crop downLeftCrop;
        Crop upRightCrop;
        Crop downRightCrop;

        if ((upCrop = checkCropOnTile(crop, upTile)) != null) {

            if ((leftCrop = checkCropOnTile(crop, leftTile)) != null) {

                if ((upLeftCrop = checkCropOnTile(crop, up_LeftTile)) != null) {

                    crop = compareCropsGrowth(crop, upCrop, leftCrop, upLeftCrop).clone();
                    upCrop = crop.clone();
                    leftCrop = crop.clone();
                    upLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.LEFT);
                    upCrop.setGiantDirection(Direction.DOWN);
                    leftCrop.setGiantDirection(Direction.UP);
                    upLeftCrop.setGiantDirection(Direction.RIGHT);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    leftTile.setThingOnTile(leftCrop);
                    up_LeftTile.setThingOnTile(upLeftCrop);

                    App.getCurrentGame().getDateTime().addDailyUpdateListener(crop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(upCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(leftCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(upLeftCrop);

                    return true;

                }

            } else if ((rightCrop = checkCropOnTile(crop, rightTile)) != null) {

                if ((upRightCrop = checkCropOnTile(crop, up_RightTile)) != null) {

                    crop = compareCropsGrowth(crop, upCrop, rightCrop, upRightCrop).clone();
                    upCrop = crop.clone();
                    rightCrop = crop.clone();
                    upRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.UP);
                    upCrop.setGiantDirection(Direction.RIGHT);
                    rightCrop.setGiantDirection(Direction.LEFT);
                    upRightCrop.setGiantDirection(Direction.DOWN);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    rightTile.setThingOnTile(rightCrop);
                    up_RightTile.setThingOnTile(upRightCrop);

                    App.getCurrentGame().getDateTime().addDailyUpdateListener(crop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(upCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(rightCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(upRightCrop);

                    return true;
                }

            }

        } else if ((downCrop = checkCropOnTile(crop, downTile)) != null) {

            if ((leftCrop = checkCropOnTile(crop, leftTile)) != null) {

                if ((downLeftCrop = checkCropOnTile(crop, down_LeftTile)) != null) {

                    crop = compareCropsGrowth(crop, downCrop, leftCrop, downLeftCrop).clone();
                    downCrop = crop.clone();
                    leftCrop = crop.clone();
                    downLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.DOWN);
                    downCrop.setGiantDirection(Direction.LEFT);
                    leftCrop.setGiantDirection(Direction.RIGHT);
                    downLeftCrop.setGiantDirection(Direction.UP);

                    tile.setThingOnTile(crop);
                    downTile.setThingOnTile(downCrop);
                    leftTile.setThingOnTile(leftCrop);
                    down_LeftTile.setThingOnTile(downLeftCrop);

                    App.getCurrentGame().getDateTime().addDailyUpdateListener(crop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(downCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(leftCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(downLeftCrop);

                    return true;
                }

            } else if ((rightCrop = checkCropOnTile(crop, rightTile)) != null) {

                if ((downRightCrop = checkCropOnTile(crop, down_RightTile)) != null) {

                    crop = compareCropsGrowth(crop, downCrop, rightCrop, downRightCrop).clone();
                    downCrop = crop.clone();
                    rightCrop = crop.clone();
                    downRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.RIGHT);
                    downCrop.setGiantDirection(Direction.UP);
                    rightCrop.setGiantDirection(Direction.DOWN);
                    downRightCrop.setGiantDirection(Direction.LEFT);

                    tile.setThingOnTile(crop);
                    downTile.setThingOnTile(downCrop);
                    rightTile.setThingOnTile(rightCrop);
                    down_RightTile.setThingOnTile(downRightCrop);


                    App.getCurrentGame().getDateTime().addDailyUpdateListener(crop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(downCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(rightCrop);
                    App.getCurrentGame().getDateTime().addDailyUpdateListener(downRightCrop);

                    return true;
                }

            }

        }
        return false;

    }

    private static Crop checkCropOnTile(Crop crop, Tile tile) {
        if (tile != null && tile.getThingOnTile() != null &&
            tile.getThingOnTile() instanceof Crop upCrop && upCrop.getName().equals(crop.getName())) {
            return upCrop;
        }
        return null;
    }

    private static Crop compareCropsGrowth(Crop crop1, Crop crop2, Crop crop3, Crop crop4) {

        Crop[] crops = {crop1, crop2, crop3, crop4};

        int[] growth = {0, 0, 0, 0};
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i <= crops[j].getCurrentStage(); i++) {
                growth[j] += crop1.getStages()[i];
            }
            growth[j] += crop1.getDaysInCurrentStage();
        }

        for(int i = 1;i < 4;i++){
            if(growth[0] < growth[i]){
                crops[0] = crops[i];
            }
        }

        return crops[0];

    }

    private static int getUntilHarvest(Plant plant) {
        int untilHarvest;
        if (plant.isFruitIsRipen()) {
            untilHarvest = 0;
        } else if (plant.getCurrentStage() >= plant.getMaxStages()) {
            untilHarvest = plant.getRegrowthTime() - plant.getDaysInCurrentStage();
        } else {
            untilHarvest = plant.getTotalHarvestTime() - plant.getDaysInCurrentStage();
            int [] stages = plant.getStages();
            for(int i = 0; i < plant.getCurrentStage(); i++) {
                untilHarvest -= stages[i];
            }
        }
        return untilHarvest;
    }

    static Result harvestPlant(String requester, Direction direction) {
        Game game = App.getCurrentGame();
        Location location = game.getPlayerByUsername(requester).getCurrentLocation().getLocationAt(direction);
        Farm farm = game.getWorld().getFarmAt(game.getPlayerByUsername(requester).getCurrentLocation());
        Tile tile = farm.getTileAt(location.delta(farm.getLocation()));

        if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        Placeable placeable = tile.getThingOnTile();
        if (placeable == null) {
            return new Result(-1, "Does not exist any plant on the tile");
        }

        if (placeable instanceof Fruit fruit) {
            Result addedToBackPack = addToBackPack(game.getPlayerByUsername(requester).
                getBackpack(), fruit, 1);
            if (addedToBackPack.success()) {
                game.getPlayerByUsername(requester).getSkill(SkillType.FARMING).addXP(5);
                tile.setThingOnTile(null);
            }
            return addedToBackPack;
        }

        if (placeable instanceof Tree tree) {
            if (tree.isFruitIsRipen()) {
                Result addedToBackPack = addToBackPack(game.getPlayerByUsername(requester).getBackpack(),
                    Fruit.getFruit(tree.getFruit()), 1);

                if (addedToBackPack.success()) {
                    game.getPlayerByUsername(requester).getSkill(SkillType.FARMING).addXP(5);
                    tree.setFruitIsRipen(false);
                    tree.getSprite().setRegion(GameAssetManager.getInstance().getTrees(tree.getName(),
                        game.getDateTime().getSeason().toString()));
                }

                return addedToBackPack;
            }
            return new Result(-1, "fruit has not ripen");

        }

        if (placeable instanceof Crop crop) {
            if (crop.isFruitIsRipen()) {
                if (crop.getGiantDirection() != null) {
                    Result addedToBackPack = addToBackPack(game.getPlayerByUsername(requester).getBackpack(),
                        Fruit.getFruit(crop.getFruit()), 10);

                    if (addedToBackPack.success()) {
                        game.getPlayerByUsername(requester).getSkill(SkillType.FARMING).addXP(5);
                        crop.setFruitIsRipen(false);
                        if (crop.isOneTime()) {
                            tile.setThingOnTile(null);
                        }

                        for (int i = 0; i < 3; i++) {
                            location = location.getLocationAt(crop.getGiantDirection());
                            tile = game.getWorld().getTileAt(location);
                            crop = (Crop) tile.getThingOnTile();
                            crop.setFruitIsRipen(false);
                            if (crop.isOneTime()) {
                                tile.setThingOnTile(null);
                            }
                        }
                    }

                    return addedToBackPack;
                } else {
                    Result addedToBackPack = addToBackPack(game.getPlayerByUsername(requester).getBackpack(),
                        Fruit.getFruit(crop.getFruit()), 1);
                    if (addedToBackPack.success()) {
                        game.getPlayerByUsername(requester).getSkill(SkillType.FARMING).addXP(5);
                        crop.setFruitIsRipen(false);
                        if (crop.isOneTime()) {
                            tile.setThingOnTile(null);
                        }
                        else {
                            crop.getSprite().setRegion(GameAssetManager.getInstance().getCrops(crop.getName(),
                                "" + (crop.getMaxStages() + 1)));
                        }
                    }

                    return addedToBackPack;
                }
            }
            return new Result(-1, "fruit has not ripen");
        }

        return new Result(-1, "Does not exist any plant on the tile");

    }

    public static Result buy(String requester, String itemName, String name, String priceString,String locationString) {
        int x;
        int y;
        String[] locationParts = locationString.split(",");
        try{
            x = Integer.parseInt(locationParts[0]);
            y = Integer.parseInt(locationParts[1]);
        } catch (Exception e) {
            return new Result(false, "only enter location in X,Y format");
        }
        int price;
        try{
            price = Integer.parseInt(priceString);
        }catch (Exception e) {
            return new Result(false, "only enter correct price");
        }
        Location location = new Location(x,y);
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        if(player.getMoney() < price){
            return new Result(false, "You don't have " + price +"coin");
        }

        Result result;
        if(Animal.getAnimal(itemName) != null){
            Animal animal = Animal.getAnimal(itemName);
            result = CheatController.addAnimal(requester, animal,name,location);
        }
        else {
            result = CheatController.addBuilding(requester, itemName,location);
        }

        if(result.success()){
            player.decreaseMoney(price);
        }
        return  result;
    }

    public static Result crafting(String requester, String artisanName){

        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in the Cabin");
        }



        if(! player.checkEnergy(2,null)){
            return new Result(-1,"you don't have enough energy");
        }

        Artisan artisan = ProducerArtisan.getProducerArtisan(artisanName);
        if(artisan == null){
            artisan = FeatureArtisan.getFeatureArtisan(artisanName);
            if(artisan == null){
                return new Result(-1,"Artisan dose not exist");
            }
        }


        Recipe recipe = Recipe.craftRecipes.get(artisan.getRecipeName());
        if(!player.getLearnedCraftingRecipes().contains(recipe)){
            return new Result(-1,"You doesn't have the crafting recipe");
        }


        for(String ingredient : recipe.getIngredientsNames()){
            if(CommonGameController.numberOfItemInBackPack(requester, ingredient) < recipe.getIngredientsNumber().get(ingredient)){
                return new Result(-1,"You do not have the enough ingredients");
            }
        }

        for(String ingredient : recipe.getIngredientsNames()){
            CommonGameController.removeItemFromBackPack(requester, ingredient, recipe.getIngredientsNumber().get(ingredient));
        }

        if(artisanName.equals("Mystic Tree Seeds")){
            if(! player.getBackpack().addItem(Seed.getSeed("Mystic Tree Seeds"),1)){
                return new Result(-1,"Backpack is full");
            }
        }
        else{
            if(! player.getBackpack().addItem(artisan,1)){
                return new Result(-1,"Backpack is full");
            }
        }

        player.decreaseEnergy(2,null);

        return new Result(1,artisanName + " crafted successfully");

    }

    public static Result cooking(String requester, String foodName){

        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Food food = Food.getFood(foodName);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        boolean passOut = false;
        if(! player.checkEnergy(3, null)){
            passOut = true;
        }

        if(food == null){
            return new Result(-1,foodName + " doesn't exist");
        }

        Recipe recipe = Recipe.foodRecipes.get(food.getName() + " Recipe");
        if(! player.getLearnedFoodRecipes().contains(recipe)){
            return new Result(-1,"You don't have enough recipe");
        }

        for(String ingredient : recipe.getIngredientsNames()){
            if(CommonGameController.numberOfItemInBackPack(requester,ingredient) < recipe.getIngredientsNumber().get(ingredient)){
                return new Result(-1,"You do not have enough ingredients");
            }
        }

        if(! player.getBackpack().addItem(food, 1)){
            return new Result(-1,"Backpack is full");
        }

        for(String ingredient : recipe.getIngredientsNames()){
            CommonGameController.removeItemFromBackPack(requester,ingredient,recipe.getIngredientsNumber().get(ingredient));
        }

        player.decreaseEnergy(3,null);

        if(passOut){
            return new Result(1,foodName + " cooked successfully. " +
                CommonGameController.passOut(requester).message());
        }

        return new Result(1,foodName + " cooked successfully");

    }

    public static Result walk(String requester, String dyString,String dxString){
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        World world = App.getCurrentGame().getWorld();
        int dx,dy;
        try{
            dx = Integer.parseInt(dxString);
            dy = Integer.parseInt(dyString);
        }catch(NumberFormatException e){
            return new Result(-1,"Invalid dx/dx");
        }

        Tile currentTile = world.getTileAt(player.getCurrentLocation());
        Tile targetTile = world.getTileAt(player.getCurrentLocation().add(new Location(dy, dx)));
        if (targetTile != null && targetTile.isWalkable()) {
            if (player.tryMove(dx, dy)) {
                currentTile.getTop().setThingOnTile(null);
                targetTile.getTop().setThingOnTile(player);
            }
        }
        return new Result(1," walks successfully");
    }

}
