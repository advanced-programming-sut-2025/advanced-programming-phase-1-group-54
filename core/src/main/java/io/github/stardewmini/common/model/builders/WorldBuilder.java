package io.github.stardewmini.common.model.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.stardewmini.common.model.map.*;
import io.github.stardewmini.common.model.map.Shops.*;
import io.github.stardewmini.common.ResourceController;
import io.github.stardewmini.common.model.DateTime;
import io.github.stardewmini.common.model.Quest;
import io.github.stardewmini.common.model.items.ShopItem;
import io.github.stardewmini.common.model.lives.NPC;
//import org.json.JSONArray;

import java.util.ArrayList;

public class WorldBuilder {
    /* TODO
        remove json array. use gson.
        move reading and writing files (and all json) to DatabaseController.
     */

    private static WorldBuilder instance;

    private WorldBuilder() {
    }

    public static WorldBuilder getInstance() {
        if (instance == null)
            instance = new WorldBuilder();
        return instance;
    }

    private static final Location[] farmLocation = {
        new Location(0, 0),
        new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), 0),
        new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), World.getNumberOfColumns() - Farm.getNumberOfColumns()),
        new Location(0, World.getNumberOfColumns() - Farm.getNumberOfColumns())
    };

    private Farm[] playerFarms;
    private Tile[][] tiles;
    private DateTime dateTime;

    static Location getFarmLocation(int i) {
        return farmLocation[i];
    }

    public void reset() {
        playerFarms = new Farm[4];
        tiles = null;
        dateTime = null;
    }

    public void setPlayerFarms(Farm[] playerFarms) {
        this.playerFarms = new Farm[playerFarms.length];
        System.arraycopy(playerFarms, 0, this.playerFarms, 0, playerFarms.length);
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public World getResult() {
        tiles = new Tile[World.getNumberOfRows()][World.getNumberOfColumns()];

        // put farms in corners\

        for (int t = 0; t < playerFarms.length; t++) {
            for (int i = 0; i < Farm.getNumberOfRows(); i++) {
                for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                    Location location = new Location(i, j);
                    tiles[farmLocation[t].row() + i][farmLocation[t].column() + j] = playerFarms[t].getTileAt(location);
                }
            }
        }

        // All Other Tiles
        for (int i = 0; i < World.getNumberOfRows(); i++) {
            for (int j = 0; j < World.getNumberOfColumns(); j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile(new Location(i, j));
                }
                tiles[i][j].setSpritePosition(
                    j * Tile.getSize(),
                    i * Tile.getSize()
                );
            }
        }

        ArrayList<Shop> shops = buildShops();
        for (Shop shop : shops) {
            dateTime.addHourCheckListener(shop);
            shop.checkHour(dateTime.getHour());
        }

        ArrayList<NPCHouse> npcHouses = buildNPCHouses();

        for (NPCHouse npcHouse : npcHouses) {
            NPC npc = npcHouse.getNpc();
            dateTime.addDailyUpdateListener(npc);
        }

        World world = new World(playerFarms, shops, npcHouses, new Map(World.getNumberOfRows(), World.getNumberOfColumns(), tiles));

        for (int i = 0; i < World.getNumberOfRows(); i++) {
            for (int j = 0; j < World.getNumberOfColumns(); j++) {
                tiles[i][j].getTop().setSpritePosition(
                    j * Tile.getSize(),
                    i * Tile.getSize()
                );
            }
        }

        this.reset();
        return world;
    }


    private final Area[] npcHouseAreas = {
        new Area(new Location(31, 36), new Location(34, 41)),
        new Area(new Location(36, 31), new Location(42, 36)),
        new Area(new Location(46, 36), new Location(49, 41)),
        new Area(new Location(38, 46), new Location(44, 51)),
        new Area(new Location(50, 50), new Location(54, 54)),
    };


    private final Area[] shopAreas = {
        new Area(new Location(24, 40), new Location(30, 47)),
        new Area(new Location(22, 50), new Location(28, 56)),
        new Area(new Location(56, 10), new Location(62, 17)),
        new Area(new Location(44, 16), new Location(50, 22)),
        new Area(new Location(53, 70), new Location(60, 78)),
        new Area(new Location(43, 64), new Location(48, 70)),
        new Area(new Location(67, 50), new Location(75, 58)),
    };


    public ArrayList<NPCHouse> buildNPCHouses() {
        ArrayList<NPCHouse> npcHouses = new ArrayList<>();
        NPC npc = new NPC("Artisan", "Sebastian");

        npc.getFavoriteItems().add("Wool");
        npc.getFavoriteItems().add("Pumpkin pie");
        npc.getFavoriteItems().add("Pizza");


        Quest quest = new Quest(npc, "Iron Ore", 50, "Diamond", 2, true);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc, "Pumpkin pie", 1, "Coin", 5000, false);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc, "Stone", 150, "Quartz", 50, false);
        npc.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc,npcHouseAreas[0]));

        NPC npc1 = new NPC("Miner", "Abigail");

        npc1.getFavoriteItems().add("Stone");
        npc1.getFavoriteItems().add("Iron Ore");
        npc1.getFavoriteItems().add("Coffee");

        quest = new Quest(npc1, "Gold Bar", 1, "friendShip", 1, true);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1, "Pumpkin", 1, "Coin", 500, false);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1, "Wheat", 50, "Iridium Sprinkler", 1, false);
        npc1.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc1,npcHouseAreas[1]));


        NPC npc2 = new NPC("Baker", "Harvey");

        npc2.getFavoriteItems().add("Coffee");
        npc2.getFavoriteItems().add("Pickle");
        npc2.getFavoriteItems().add("Wine");

        quest = new Quest(npc2, "Apple Tree", 12, "Coin", 750, true);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2, "Salmon", 1, "friendShip", 1, false);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2, "Grape Wine", 1, "Salad", 5, false);
        npc2.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc2,npcHouseAreas[2]));


        NPC npc3 = new NPC("Chef", "Leah");

        npc3.getFavoriteItems().add("Salad");
        npc3.getFavoriteItems().add("Coffee");
        npc3.getFavoriteItems().add("Wine");

        quest = new Quest(npc3, "Wood", 50, "Coin", 500, true);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3, "Salmon", 1, "Salmon Dinner Recipe", 1, false);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3, "Wood", 200, "Deluxe Scarecrow", 3, false);
        npc3.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc3,npcHouseAreas[3]));


        NPC npc4 = new NPC("Blacksmith", "Robin");

        npc4.getFavoriteItems().add("Spaghetti");
        npc4.getFavoriteItems().add("Wood");
        npc4.getFavoriteItems().add("Iron Bar");

        quest = new Quest(npc4, "Wood", 80, "Coin", 1000, true);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4, "Iron Bar", 10, "Bee House", 3, false);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4, "Wood", 1000, "Coin", 25000, false);
        npc4.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc4,npcHouseAreas[4]));

        for (NPCHouse npcHouse : npcHouses) {
            for (int i = 0; i < npcHouse.getNumberOfRows(); i++) {
                for (int j = 0; j < npcHouse.getNumberOfColumns(); j++) {
                    tiles[npcHouse.getLocation().row() + i][npcHouse.getLocation().column() + j].setThingOnTile(npcHouse);
                }
            }
        }

        return npcHouses;
    }

    private ArrayList<Shop> buildShops() {
        ArrayList<Shop> shops = new ArrayList<>();
        //creating blacksmith shop
        NPC human = new NPC("Shop Keeper", "Clint");
        BlackSmithShop blackSmithShop = new BlackSmithShop(human, shopAreas[0]);
        shops.add(blackSmithShop);
        JsonArray jsonArray = ResourceController.loadJsonArray("BlacksmithStock.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            blackSmithShop.getStock().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("BlacksmithUpgradeTools.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            String hashmapString = jsonElement.getAsJsonObject().get("HashmapString").getAsString();
            int hashmapInt = jsonElement.getAsJsonObject().get("HashmapInt").getAsInt();
            int price = jsonElement.getAsJsonObject().get("price").getAsInt();
            BlackSmithShop.UpgradeToolBlackSmith upgradesToolsBlacsmithShop = new BlackSmithShop.UpgradeToolBlackSmith(name, price, hashmapString, hashmapInt);
            blackSmithShop.getUpgradeTools().add(upgradesToolsBlacsmithShop);
        }


        //creating JojoMart shop
        NPC human1 = new NPC("Shop keeper", "Morris");
        JojoMartShop jojoMartShop = new JojoMartShop(human1, shopAreas[1]);
        shops.add(jojoMartShop);
        jsonArray = ResourceController.loadJsonArray("JojoMartPermanent.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            jojoMartShop.getPermanentStock().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("JojoMartFall.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            jojoMartShop.getFallStock().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("JojomartSpring.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            jojoMartShop.getSpringStock().add(shopItem);
        }

        jsonArray = ResourceController.loadJsonArray("JojomartSummer.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            jojoMartShop.getSummerStock().add(shopItem);
        }

        jsonArray = ResourceController.loadJsonArray("JojoMartWinter.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            jojoMartShop.getWinterStock().add(shopItem);
        }


        //creating Pierre General Shop
        NPC human2 = new NPC("Shop keeper", "Pierre");
        PierreGeneralShop pierreGeneralShop = new PierreGeneralShop(human2, shopAreas[2]);
        shops.add(pierreGeneralShop);
        jsonArray = ResourceController.loadJsonArray("PierreYearRound.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            pierreGeneralShop.getYearRoundStock().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("PierreBackpacks.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            boolean isPurchaseable = jsonElement.getAsJsonObject().get("isPurchaseAble").getAsBoolean();
            String isAvailable = jsonElement.getAsJsonObject().get("StringAvailable").getAsString();
            PierreGeneralShop.BackPacksItems backPacksItems = new PierreGeneralShop.BackPacksItems(isPurchaseable, name, price, description, isAvailable);
            pierreGeneralShop.getBackPacks().add(backPacksItems);
        }
        jsonArray = ResourceController.loadJsonArray("PierreSpring.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int priceInSeason = jsonElement.getAsJsonObject().get("PriceInSeason").getAsInt();
            int priceOutOfSeason = jsonElement.getAsJsonObject().get("PriceOutOfSeason").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
            pierreGeneralShop.getSpringStock().add(seasonalStockItems);
        }
        jsonArray = ResourceController.loadJsonArray("PierreSummer.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int priceInSeason = jsonElement.getAsJsonObject().get("PriceInSeason").getAsInt();
            int priceOutOfSeason = jsonElement.getAsJsonObject().get("PriceOutOfSeason").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
            pierreGeneralShop.getSummerStock().add(seasonalStockItems);
        }
        jsonArray = ResourceController.loadJsonArray("PierreFall.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int priceInSeason = jsonElement.getAsJsonObject().get("PriceInSeason").getAsInt();
            int priceOutOfSeason = jsonElement.getAsJsonObject().get("PriceOutOfSeason").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
            pierreGeneralShop.getFallStock().add(seasonalStockItems);
        }


        //creating Carpenter’s Shop
        NPC human3 = new NPC("Shop keeper", "Robin");
        CarpenterShop carpenterShop = new CarpenterShop(human3, shopAreas[3]);
        shops.add(carpenterShop);
        jsonArray = ResourceController.loadJsonArray("CarpenterPermanent.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            carpenterShop.getPermanentStock().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("CarpenterFarmBuilding.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            int xsize = jsonElement.getAsJsonObject().get("XSize").getAsInt();
            int ysize = jsonElement.getAsJsonObject().get("YSize").getAsInt();
            int wood = jsonElement.getAsJsonObject().get("Wood").getAsInt();
            int stone = jsonElement.getAsJsonObject().get("Stone").getAsInt();
            CarpenterShop.ItemsinCarpenterShop itemsinCarpenterShop = new CarpenterShop.ItemsinCarpenterShop(xsize, ysize, name, count, price, description, wood, stone);
            carpenterShop.getFarmBuildings().add(itemsinCarpenterShop);
        }


        //creating Fish Shop
        NPC human4 = new NPC("Shop keeper", "Willy");
        FishShop fishShop = new FishShop(human4, shopAreas[4]);
        shops.add(fishShop);
        jsonArray = ResourceController.loadJsonArray("FishStock.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            int fishinglevel = jsonElement.getAsJsonObject().get("FishingSkillRequired").getAsInt();
            FishShop.StockInShop stockInShop = new FishShop.StockInShop(fishinglevel, name, false, price, description);
            fishShop.getStockInShop().add(stockInShop);
        }


        //crating Marnie’s Ranch
        NPC human5 = new NPC("Shop keeper", "Marnie");
        MarnieRanch marnieRanch = new MarnieRanch(human5, shopAreas[5]);
        shops.add(marnieRanch);
        jsonArray = ResourceController.loadJsonArray("MarnieRanchInventory.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            marnieRanch.getShopInventory().add(shopItem);
        }
        jsonArray = ResourceController.loadJsonArray("MarnieRanchLivesStock.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            String buildingRequired = jsonElement.getAsJsonObject().get("BuildingRequired").getAsString();
            MarnieRanch.ItemsInMarnieRanch itemsInShops = new MarnieRanch.ItemsInMarnieRanch(buildingRequired, count, name, price, description);
            marnieRanch.getLiveStock().add(itemsInShops);
        }


        //creating :The Stardrop Saloon
        NPC human6 = new NPC("Shop keeper", "Gus");
        TheStardropSaloonShop theStardropSaloonShop = new TheStardropSaloonShop(human6, shopAreas[6]);
        shops.add(theStardropSaloonShop);
        jsonArray = ResourceController.loadJsonArray("TheStarDropSaloon.json");
        for (JsonElement jsonElement : jsonArray) {
            String name = jsonElement.getAsJsonObject().get("Name").getAsString();
            int price = jsonElement.getAsJsonObject().get("Price").getAsInt();
            String description = jsonElement.getAsJsonObject().get("Description").getAsString();
            int count = jsonElement.getAsJsonObject().get("Count").getAsInt();
            ShopItem shopItem = new ShopItem(name, false, count, price, description);
            theStardropSaloonShop.getPermanentStock().add(shopItem);
        }


        for (Shop shop : shops) {
            for (int i = 0; i < shop.getNumberOfRows(); i++) {
                for (int j = 0; j < shop.getNumberOfColumns(); j++) {
                    tiles[shop.getLocation().row() + i][shop.getLocation().column() + j].setThingOnTile(shop);
                    tiles[shop.getLocation().row() + i][shop.getLocation().column() + j].setSpritePosition(
                        (shop.getLocation().column() + j) * Tile.getSize(),
                        (shop.getLocation().row() + i) * Tile.getSize()
                    );
                }
            }
        }

        return shops;
    }

}
