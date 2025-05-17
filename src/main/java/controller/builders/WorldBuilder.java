package controller.builders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Quest;
import model.lives.NPC;
import model.map.*;
import model.map.Shops.*;

import java.util.ArrayList;

public class WorldBuilder {
    private static final Location[] farmLocation = {
            new Location(0, 0),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), 0),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), World.getNumberOfColumns() - Farm.getNumberOfColumns()),
            new Location(0, World.getNumberOfColumns() - Farm.getNumberOfColumns())
    };

    private static Farm[] playerFarms = new Farm[4];

    static Location getFarmLocation(int i) {
        return farmLocation[i];
    }

    public static void reset() {
        playerFarms = new Farm[4];
    }

    public static void setPlayerFarms(Farm[] playerFarms) {
        System.arraycopy(playerFarms, 0, WorldBuilder.playerFarms, 0, playerFarms.length);
    }

    public static World getResult() {
        Tile[][] tiles = new Tile[World.getNumberOfRows()][World.getNumberOfColumns()];

        // put farms in corners\

        for (int t = 0; t < 4; t++) {
            if (playerFarms[t] != null) {
                for (int i = 0; i < Farm.getNumberOfRows(); i++) {
                    for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                        Location location = new Location(i, j);
                        tiles[farmLocation[t].row() + i][farmLocation[t].column() + j] = playerFarms[t].getTileAt(location);
                    }
                }
            }
        }

        // All Other Tiles
        for (int i = 0; i < World.getNumberOfRows(); i++) {
            for (int j = 0; j < World.getNumberOfColumns(); j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile();
                }
                tiles[i][j].setLocation(new Location(i, j));
            }
        }

      //  ArrayList<Shop> shops = buildShops();
      //  ArrayList<NPCHouse> NPCHouses = buildNPCHouses();
        World world = new World(playerFarms, null, null, new Map(World.getNumberOfRows(), World.getNumberOfColumns(), tiles));
        WorldBuilder.reset();
        return world;
    }


    private static final Area[] npcHouseAreas = {
            new Area(new Location(1, 36), new Location(4, 41)),
            new Area(new Location(36, 1), new Location(41, 4)),
            new Area(new Location(66, 36), new Location(69, 41)),
            new Area(new Location(36, 66), new Location(41, 69)),
            new Area(new Location(50, 50), new Location(54, 54)),
    };


    public static ArrayList<NPCHouse> buildNPCHouses() {
        ArrayList<NPCHouse> npcHouses = new ArrayList<>();
        NPC npc = new NPC("Artisan","Sebastian");

        npc.getFavoriteItems().add("Wool");
        npc.getFavoriteItems().add("Pumpkin pie");
        npc.getFavoriteItems().add("Pizza");


        Quest quest = new Quest(npc,"Iron Ore",50,"Diamond",2,true);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc,"Pumpkin pie",1,"Coin",5000,false);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc,"Stone",150,"Quartz",50,false);
        npc.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc, npcHouseAreas[0]));

        NPC npc1 = new NPC("Miner","Abigail");

        npc1.getFavoriteItems().add("Stone");
        npc1.getFavoriteItems().add("Iron Ore");
        npc1.getFavoriteItems().add("Coffee");

        quest = new Quest(npc1,"Gold Bar",1,"friendShip",1,true);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1,"Pumpkin",1,"Coin",500,false);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1,"Wheat",50,"Iridium Sprinkler",1,false);
        npc1.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc1, npcHouseAreas[1]));



        NPC npc2 = new NPC("Baker","Harvey");

        npc2.getFavoriteItems().add("Coffee");
        npc2.getFavoriteItems().add("Pickle");
        npc2.getFavoriteItems().add("Wine");

        quest = new Quest(npc2,"Apple Tree",12,"Coin",750,true);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2,"Salmon",1,"friendShip",1,false);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2,"Grape Wine",1,"Salad",5,false);
        npc2.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc2, npcHouseAreas[2]));



        NPC npc3 = new NPC("Chef","Lia");

        npc3.getFavoriteItems().add("Salad");
        npc3.getFavoriteItems().add("Coffee");
        npc3.getFavoriteItems().add("Wine");

        quest = new Quest(npc3,"Wood",50,"Coin",500,true);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3,"Salmon",1,"Salmon Dinner Recipe",1,false);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3,"Wood",200,"Deluxe Scarecrow",3,false);
        npc3.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc3, npcHouseAreas[3]));


        NPC npc4 = new NPC("Blacksmith","Robin");

        npc4.getFavoriteItems().add("Spaghetti");
        npc4.getFavoriteItems().add("Wood");
        npc4.getFavoriteItems().add("Iron Bar");

        quest = new Quest(npc4,"Wood",80,"Coin",1000,true);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4,"Iron Bar",10,"Bee House",3,false);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4,"Wood",1000,"Coin",25000,false);
        npc4.getAllQuests().add(quest);

        npcHouses.add(new NPCHouse(npc4, npcHouseAreas[4]));
        return npcHouses;
    }


//    private static final Area[] shopAreas = {
//            new Area(new Location(), new Location()),
//            new Area(new Location(), new Location()),
//            new Area(new Location(), new Location()),
//            new Area(new Location(), new Location()),
//            new Area(new Location(), new Location()),
//    };


//    private static ArrayList<Shop> buildShops() {
//        ArrayList<Shop> shops = new ArrayList<>();
//        //creating blacksmith shop
//        NPC human = new NPC("Shop Keeper","Clint");
//        BlackSmithShop blackSmithShop = new BlackSmithShop(human);
//        shops.add(blackSmithShop);
//        JSONArray jsonArray = loadJsonArray("BlacksmithStock.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            blackSmithShop.getStock().add(shopItem);
//        }
//        jsonArray = loadJsonArray("BlacksmithUpgradeTools.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            String hashmapString = jsonArray.getJSONObject(i).getString("HashmapString");
//            int hashmapInt = jsonArray.getJSONObject(i).getInt("HashmapInt");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            BlackSmithShop.UpgradeToolBlackSmith upgradesToolsBlacsmithShop = new BlackSmithShop.UpgradeToolBlackSmith(name, price, hashmapString, hashmapInt);
//            blackSmithShop.getUpgradeTools().add(upgradesToolsBlacsmithShop);
//        }
//
//
//        //creating JojoMart shop
//        NPC human1 = new NPC("Shop keeper","Morris");
//        JojoMartShop jojoMartShop = new JojoMartShop(human1);
//        shops.add(jojoMartShop);
//        jsonArray = loadJsonArray("JojoMartPermanent.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            jojoMartShop.getPermanentStock().add(shopItem);
//        }
//        jsonArray = loadJsonArray("JojoMartFall.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            jojoMartShop.getFallStock().add(shopItem);
//        }
//        jsonArray = loadJsonArray("JojomartSpring.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            jojoMartShop.getSpringStock().add(shopItem);
//        }
//
//        jsonArray = loadJsonArray("JojomartSummer.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            jojoMartShop.getSummerStock().add(shopItem);
//        }
//
//        jsonArray = loadJsonArray("JojoMartWinter.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            jojoMartShop.getWinterStock().add(shopItem);
//        }
//
//
//        //creating Pierre General Shop
//        NPC human2 = new NPC("Shop keeper","Pierre");
//        PierreGeneralShop pierreGeneralShop = new PierreGeneralShop(human2);
//        shops.add(pierreGeneralShop);
//        jsonArray = loadJsonArray("PierreYearRound.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            pierreGeneralShop.getYearRoundStock().add(shopItem);
//        }
//        jsonArray = loadJsonArray("PierreBackpacks.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            boolean isPurchaseable = jsonArray.getJSONObject(i).getBoolean("isPurchaseAble");
//            String isAvailable = jsonArray.getJSONObject(i).getString("StringAvailable");
//            PierreGeneralShop.BackPacksItems backPacksItems = new PierreGeneralShop.BackPacksItems(isPurchaseable, name, price, description, isAvailable);
//            pierreGeneralShop.getBackPacks().add(backPacksItems);
//        }
//        jsonArray = loadJsonArray("PierreSpring.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
//            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
//            pierreGeneralShop.getSpringStock().add(seasonalStockItems);
//        }
//        jsonArray = loadJsonArray("PierreSummer.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
//            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
//            pierreGeneralShop.getSummerStock().add(seasonalStockItems);
//        }
//        jsonArray = loadJsonArray("PierreFall.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
//            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name, 0, description, priceInSeason, priceOutOfSeason);
//            pierreGeneralShop.getFallStock().add(seasonalStockItems);
//        }
//
//
//        //creating Carpenter’s Shop
//        NPC human3 = new NPC("Shop keeper","Robin");
//        CarpenterShop carpenterShop = new CarpenterShop(human3);
//        shops.add(carpenterShop);
//        jsonArray = loadJsonArray("CarpanterPermanent.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            carpenterShop.getPermanentStock().add(shopItem);
//        }
//        jsonArray = loadJsonArray("CarpanterFarmBuilding.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            int xsize = jsonArray.getJSONObject(i).getInt("XSize");
//            int ysize = jsonArray.getJSONObject(i).getInt("YSize");
//            int wood = jsonArray.getJSONObject(i).getInt("Wood");
//            int stone = jsonArray.getJSONObject(i).getInt("Stone");
//            CarpenterShop.ItemsinCarpenterShop itemsinCarpenterShop = new CarpenterShop.ItemsinCarpenterShop(xsize, ysize, name, count, price, description, wood, stone);
//            carpenterShop.getFarmBuildings().add(itemsinCarpenterShop);
//        }
//
//
//        //creating Fish Shop
//        NPC human4 = new NPC("Shop keeper","Willy");
//        FishShop fishShop = new FishShop(human4);
//        shops.add(fishShop);
//        jsonArray = loadJsonArray("FishStock.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            int fishinglevel = jsonArray.getJSONObject(i).getInt("FishingSkillRequired");
//            FishShop.StockInShop stockInShop = new FishShop.StockInShop(fishinglevel, name, false, price, description);
//            fishShop.getStockInShop().add(stockInShop);
//        }
//
//
//        //crating Marnie’s Ranch
//        NPC human5 = new NPC("Shop keeper","Marnie");
//        MarnieRanch marnieRanch = new MarnieRanch(human5);
//        shops.add(marnieRanch);
//        jsonArray = loadJsonArray("MarnieRanchInventory.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            marnieRanch.getShopInventory().add(shopItem);
//        }
//        jsonArray = loadJsonArray("MarnieRanchLivesStock.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            String buildingRequired = jsonArray.getJSONObject(i).getString("BuildingRequired");
//            MarnieRanch.ItemsInMarnieRanch itemsInShops = new MarnieRanch.ItemsInMarnieRanch(buildingRequired, count, name, price, description);
//            marnieRanch.getLiveStock().add(itemsInShops);
//        }
//
//
//        //creating :The Stardrop Saloon
//        NPC human6 = new NPC("Shop keeper","Gus");
//        TheStardropSaloonShop theStardropSaloonShop = new TheStardropSaloonShop(human6);
//        shops.add(theStardropSaloonShop);
//        jsonArray = DataBaseController.loadJsonArray("TheStarDropSaloon.json");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String name = jsonArray.getJSONObject(i).getString("Name");
//            int price = jsonArray.getJSONObject(i).getInt("Price");
//            String description = jsonArray.getJSONObject(i).getString("Description");
//            int count = jsonArray.getJSONObject(i).getInt("Count");
//            ShopItem shopItem = new ShopItem(name, false, count, price, description);
//            theStardropSaloonShop.getPermanentStock().add(shopItem);
//        }
//        return shops;
//    }

}
