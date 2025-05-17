package model.map;

/*
    There is a global map in game, each player has a farm in one of four corners of map;
 */

import controller.Game.DataBaseController;
import model.DailyUpdate;
import model.HourUpdate;
import model.lives.NPC;
import model.lives.Player;
import model.enums.Direction;
import model.enums.Weather;
import model.map.Shops.*;
import org.json.JSONArray;

import java.util.ArrayList;

public class World implements DailyUpdate, HourUpdate {
    private static final int NUMBER_OF_ROWS = 100;
    private static final int NUMBER_OF_COLUMNS = 100;
    private static final int NUMBER_OF_THUNDER = 3;

    private final Farm[] playerFarms;

    private final Map map;

    private Weather currentWeather;
    private Weather tomorrowWeather;

    private final ArrayList<Shop> shops;
    private final ArrayList<NPCHouse> npcHouses;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public World(Farm[] playerFarms, ArrayList<Shop> shops, ArrayList<NPCHouse> npcHouses, Map map) {
        this.playerFarms = playerFarms;
        this.map = map;
        this.shops = shops;
        this.npcHouses = npcHouses;
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public Farm getFarm(Player player) {
        return player.getFarm();
    }

    public Farm getFarmAt(Location location) {
        for (Farm farm : playerFarms) {
            if (farm.getTileAt(location.delta(farm.getLocation())) != null) {
                return farm;
            }
        }
        return null;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public ArrayList<NPCHouse> getNpcHouses() {
        return npcHouses;
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    public void rain() {
        for (Farm farm : playerFarms) {
            farm.rain();
        }
    }

    public void thunder() {
        for (Farm farm : playerFarms) {
            for (int i = 1; i <= NUMBER_OF_THUNDER; i++) {
                farm.thunderStrike(farm.getRandomLocation());
            }
        }
    }

    public int getDistance(Location A, Location B) {
        return map.getDistance(A, B);
    }

    public ArrayList<Direction> getShortestPath(Location A, Location B) {
        return map.getShortestPath(A, B);
    }

    @Override
    public void nextDayUpdate() {
        currentWeather = tomorrowWeather;

        for (Farm farm : playerFarms) {
            farm.nextDayUpdate();
        }

        if (currentWeather == Weather.RAIN || currentWeather == Weather.STORM) {
            rain();
        }

        if (currentWeather.equals(Weather.STORM)) {
            thunder();
        }

        refillShops();
    }

    @Override
    public void nextHourUpdate() {
        for (Farm farm : playerFarms) {
            farm.nextHourUpdate();
        }
    }

    private void refillShops() {
        BlackSmithShop blackSmithShop = (BlackSmithShop) shops.get(0);
        JSONArray jsonArray = DataBaseController.loadJsonArray("BlacksmithStock.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            blackSmithShop.getStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("BlacksmithUpgradeTools.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            blackSmithShop.getUpgradeTools().get(i).setCount(1);
        }


        //creating JojoMart shop
        JojoMartShop jojoMartShop = (JojoMartShop) shops.get(1);
        jsonArray = DataBaseController.loadJsonArray("JojoMartPermanent.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            jojoMartShop.getPermanentStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("JojoMartFall.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            jojoMartShop.getFallStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("JojomartSpring.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            jojoMartShop.getSpringStock().get(i).setCount(count);
        }

        jsonArray = DataBaseController.loadJsonArray("JojomartSummer.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            jojoMartShop.getSummerStock().get(i).setCount(count);
        }

        jsonArray = DataBaseController.loadJsonArray("JojoMartWinter.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            jojoMartShop.getWinterStock().get(i).setCount(count);
        }


        //creating Pierre General Shop
        PierreGeneralShop pierreGeneralShop = (PierreGeneralShop) shops.get(2);
        jsonArray = DataBaseController.loadJsonArray("PierreYearRound.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            pierreGeneralShop.getYearRoundStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("PierreBackpacks.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            pierreGeneralShop.getBackPacks().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("PierreSpring.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            pierreGeneralShop.getSpringStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("PierreSummer.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            pierreGeneralShop.getSummerStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("PierreFall.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            pierreGeneralShop.getFallStock().get(i).setCount(count);
        }


        //creating Carpenter’s Shop
        CarpenterShop carpenterShop = (CarpenterShop) shops.get(3);
        jsonArray = DataBaseController.loadJsonArray("CarpanterPermanent.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            carpenterShop.getPermanentStock().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("CarpanterFarmBuilding.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            carpenterShop.getFarmBuildings().get(i).setCount(count);
        }


        //creating Fish Shop
        FishShop fishShop = (FishShop) (shops.get(4));
        jsonArray = DataBaseController.loadJsonArray("FishStock.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            fishShop.getStockInShop().get(i).setCount(count);
        }


        //crating Marnie’s Ranch
        MarnieRanch marnieRanch = (MarnieRanch) shops.get(5);
        jsonArray = DataBaseController.loadJsonArray("MarnieRanchInventory.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            marnieRanch.getShopInventory().get(i).setCount(count);
        }
        jsonArray = DataBaseController.loadJsonArray("MarnieRanchLivesStock.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            marnieRanch.getLiveStock().get(i).setCount(count);
        }


        //creating :The Stardrop Saloon
        jsonArray = DataBaseController.loadJsonArray("TheStarDropSaloon.json");
        TheStardropSaloonShop stardropSaloonShop = (TheStardropSaloonShop) shops.get(6);
        for (int i = 0; i < jsonArray.length(); i++) {
            int count = jsonArray.getJSONObject(i).getInt("Count");
            stardropSaloonShop.getPermanentStock().get(i).setCount(count);
        }
    }
}
