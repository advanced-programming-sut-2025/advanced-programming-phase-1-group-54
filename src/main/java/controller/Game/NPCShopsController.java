package controller.Game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.App;
import model.Game;
import model.Result;
import model.Shops.*;
import model.alive.Animal;
import model.alive.Human;
import model.enums.toolsLevel.BackPackLevel;
import model.items.Item;
import model.items.ItemsInShops;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NPCShopsController {
    static {
        //creating blacksmith shop
        Human human = new Human();
        human.setName("Clint");
        BlackSmithShop blackSmithShop = new BlackSmithShop(human);
        App.getCurrentGame().getNpcShops().add(blackSmithShop);
        JSONArray jsonArray = loadJsonArray("BlacksmithStock.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            blackSmithShop.getStock().add(itemsInShops);
        }
        jsonArray = loadJsonArray("BlacksmithUpgradeTools.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            String hashmapString = jsonArray.getJSONObject(i).getString("HashmapString");
            int hashmapInt = jsonArray.getJSONObject(i).getInt("HashmapInt");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            BlackSmithShop.upgradesToolsBlacsmithShop upgradesToolsBlacsmithShop = new BlackSmithShop.upgradesToolsBlacsmithShop(name,price,hashmapString,hashmapInt);
            blackSmithShop.getUpgradeTools().add(upgradesToolsBlacsmithShop);
        }




        //creating JojoMart shop
        Human human1 = new Human();
        human1.setName("Morris");
        JojoMartShop jojoMartShop = new JojoMartShop(human);
        App.getCurrentGame().getNpcShops().add(blackSmithShop);
        jsonArray = loadJsonArray("JojoMartPermanent.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            jojoMartShop.getPermanentStock().add(itemsInShops);
        }
        jsonArray = loadJsonArray("JojoMartFall.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            jojoMartShop.getFallStock().add(itemsInShops);
        }
        jsonArray = loadJsonArray("JojomartSpring.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            jojoMartShop.getSpringStock().add(itemsInShops);
        }

        jsonArray = loadJsonArray("JojomartSummer.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            jojoMartShop.getSummerStock().add(itemsInShops);
        }

        jsonArray = loadJsonArray("JojoMartWinter.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            jojoMartShop.getWinterStock().add(itemsInShops);
        }




        //creating Pierre General Shop
        Human human2 = new Human();
        human2.setName("Pierre");
        PierreGeneralShop pierreGeneralShop = new PierreGeneralShop(human);
        App.getCurrentGame().getNpcShops().add(pierreGeneralShop);
        jsonArray = loadJsonArray("PierreYearRound.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            pierreGeneralShop.getYearRoundStock().add(itemsInShops);
        }
        jsonArray = loadJsonArray("PierreBackpacks.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            boolean isPurchaseable  = jsonArray.getJSONObject(i).getBoolean("isPurchaseAble");
            String isAvailable = jsonArray.getJSONObject(i).getString("StringAvailable");
            PierreGeneralShop.BackPacksItems backPacksItems = new PierreGeneralShop.BackPacksItems(isPurchaseable,name,price,description,isAvailable);
            pierreGeneralShop.getBackPacks().add(backPacksItems);
        }
        jsonArray = loadJsonArray("PierreSpring.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name,0,description,priceInSeason,priceOutOfSeason);
            pierreGeneralShop.getSpringStock().add(seasonalStockItems);
        }
        jsonArray = loadJsonArray("PierreSummer.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name,0,description,priceInSeason,priceOutOfSeason);
            pierreGeneralShop.getSummerStock().add(seasonalStockItems);
        }
        jsonArray = loadJsonArray("PierreFall.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int priceInSeason = jsonArray.getJSONObject(i).getInt("PriceInSeason");
            int priceOutOfSeason = jsonArray.getJSONObject(i).getInt("PriceOutOfSeason");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            PierreGeneralShop.SeasonalStockItems seasonalStockItems = new PierreGeneralShop.SeasonalStockItems(name,0,description,priceInSeason,priceOutOfSeason);
            pierreGeneralShop.getFallStock().add(seasonalStockItems);
        }




        //creating Carpenter’s Shop
        Human human3 = new Human();
        human3.setName("Robin");
        CarpenterShop carpenterShop = new CarpenterShop(human);
        App.getCurrentGame().getNpcShops().add(carpenterShop);
        jsonArray = loadJsonArray("CarpanterPermanent.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            carpenterShop.getPermanentStock().add(itemsInShops);
        }
        jsonArray = loadJsonArray("CarpanterFarmBuilding.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            int xsize = jsonArray.getJSONObject(i).getInt("XSize");
            int ysize = jsonArray.getJSONObject(i).getInt("YSize");
            int wood = jsonArray.getJSONObject(i).getInt("Wood");
            int stone = jsonArray.getJSONObject(i).getInt("Stone");
            CarpenterShop.ItemsinCarpenterShop itemsinCarpenterShop = new CarpenterShop.ItemsinCarpenterShop(xsize,ysize,name,count,price,description,wood,stone);
            carpenterShop.getFarmBuildings().add(itemsinCarpenterShop);
        }



        //creating Fish Shop
        Human human4 = new Human();
        human4.setName("Willy");
        FishShop fishShop = new FishShop(human);
        App.getCurrentGame().getNpcShops().add(fishShop);
        jsonArray = loadJsonArray("FishStock.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            int fishinglevel = jsonArray.getJSONObject(i).getInt("FishingSkillRequired");
            FishShop.StockInShop stockInShop = new FishShop.StockInShop(fishinglevel,name,false,price,description);
            fishShop.getStockInShop().add(stockInShop);
        }




        //crating Marnie’s Ranch
        Human human5 = new Human();
        human5.setName("Marnie");
        MarnieRanch marnieRanch = new MarnieRanch(human);
        App.getCurrentGame().getNpcShops().add(marnieRanch);
        jsonArray = loadJsonArray("MarnieRanchInventory.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            marnieRanch.getShopInventory().add(itemsInShops);
        }
        jsonArray = loadJsonArray("MarnieRanchLivesStock.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            String buildingRequired = jsonArray.getJSONObject(i).getString("BuildingRequired");
            MarnieRanch.ItemsInMarineRanch itemsInShops = new MarnieRanch.ItemsInMarineRanch(buildingRequired,count,name,price,description);
            marnieRanch.getLivesTock().add(itemsInShops);
        }




        //creating :The Stardrop Saloon
        Human human6 = new Human();
        human6.setName("Gus");
        TheStardropSaloonShop theStardropSaloonShop = new TheStardropSaloonShop(human);
        App.getCurrentGame().getNpcShops().add(theStardropSaloonShop);
        jsonArray = loadJsonArray("TheStarDropSaloon.json");
        for(int i = 0; i < jsonArray.length(); i++){
            String name = jsonArray.getJSONObject(i).getString("Name");
            int price = jsonArray.getJSONObject(i).getInt("Price");
            String description = jsonArray.getJSONObject(i).getString("Description");
            int count = jsonArray.getJSONObject(i).getInt("Count");
            ItemsInShops itemsInShops = new ItemsInShops(name,false,count,price,description);
            theStardropSaloonShop.getPermanentStock().add(itemsInShops);
        }

    }
    public static JSONArray loadJsonArray(String filename) {
        try {
            // Assuming file is at project root, next to src/
            String fullPath = Paths.get("").toAbsolutePath().resolve(filename).toString();
            try (InputStream inputStream = new FileInputStream(fullPath)) {
                return new JSONArray(new JSONTokener(inputStream));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Result purchaseItem(Shop shop, String itemName, int count) {
        if(shop.getOwner().getName().equals("Clint")){
            return buySomthingFromBlacksmith(itemName,count);
        }
        if(shop.getOwner().getName().equals("Morris")){
            return buySomthingFromJojaMart(itemName,count);
        }
        if(shop.getOwner().getName().equals("Pierre")){
            return buySomthingFromPierre(itemName,count);
        }
        if(shop.getOwner().getName().equals("Robin")){
            return buySomthingFromCarpenter(itemName,count);
        }
        if(shop.getOwner().getName().equals("Willy")){
            return buySomthingFromFishShop(itemName,count);
        }
        if(shop.getOwner().getName().equals("Marnie")){
            return buySomthingFromMarnie(itemName,count);
        }
        if(shop.getOwner().getName().equals("Gus")){
            return buySomthingFromStardrop(itemName,count);
        }

        //TODO
        return null;
    }
    public static Result buySomthingFromBlacksmith(String itemName, int count) {
        for(ItemsInShops item : ((BlackSmithShop)(App.getCurrentGame().getNpcShops().get(0))).getStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for (BlackSmithShop.upgradesToolsBlacsmithShop item : ((BlackSmithShop)(App.getCurrentGame().getNpcShops().get(0))).getUpgradeTools()){
            if (item.getName().equals(itemName)){
                return buyUpgradeToolsInBlackSmith(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromJojaMart(String itemName,int count) {
        for(ItemsInShops item : ((JojoMartShop)(App.getCurrentGame().getNpcShops().get(1))).getPermanentStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(ItemsInShops item : ((JojoMartShop)(App.getCurrentGame().getNpcShops().get(1))).getSpringStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(ItemsInShops item : ((JojoMartShop)(App.getCurrentGame().getNpcShops().get(1))).getSummerStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(ItemsInShops item : ((JojoMartShop)(App.getCurrentGame().getNpcShops().get(1))).getFallStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(ItemsInShops item : ((JojoMartShop)(App.getCurrentGame().getNpcShops().get(1))).getWinterStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromPierre(String itemName, int count) {
        for(ItemsInShops item : ((PierreGeneralShop)(App.getCurrentGame().getNpcShops().get(2))).getYearRoundStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(PierreGeneralShop.BackPacksItems item : ((PierreGeneralShop)(App.getCurrentGame().getNpcShops().get(2))).getBackPacks()){
            if (item.getName().equals(itemName)){
                return buyBackPacs(item,count);
            }
        }
        for (PierreGeneralShop.SeasonalStockItems item :((PierreGeneralShop)(App.getCurrentGame().getNpcShops().get(2))).getSpringStock()){
            if (item.getName().equals(itemName)){
                return buySeasonalItems(item,count,"spring");
            }
        }

        for (PierreGeneralShop.SeasonalStockItems item :((PierreGeneralShop)(App.getCurrentGame().getNpcShops().get(2))).getSummerStock()){
            if (item.getName().equals(itemName)){
                return buySeasonalItems(item,count,"summer");
            }
        }
        for (PierreGeneralShop.SeasonalStockItems item :((PierreGeneralShop)(App.getCurrentGame().getNpcShops().get(2))).getFallStock()){
            if (item.getName().equals(itemName)){
                return buySeasonalItems(item,count,"fall");
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromCarpenter(String itemName, int count) {
        for(ItemsInShops item : ((CarpenterShop)(App.getCurrentGame().getNpcShops().get(3))).getPermanentStock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for(CarpenterShop.ItemsinCarpenterShop item : ((CarpenterShop)(App.getCurrentGame().getNpcShops().get(3))).getFarmBuildings()){
            if (item.getName().equals(itemName)){
                return buyFarmBuilding(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromFishShop(String itemName, int count) {
        for (FishShop.StockInShop item : ((FishShop)(App.getCurrentGame().getNpcShops().get(4))).getStockInShop()){
            if (item.getName().equals(itemName)){
                return buyStockFromFishShop(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromMarnie(String itemName, int count) {
        for (ItemsInShops item : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getShopInventory()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for (MarnieRanch.ItemsInMarineRanch item : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getLivesTock()){
            if (item.getName().equals(itemName)){
                return buyLivesStockInMarine(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }

    public static Result buySomthingFromStardrop(String itemName, int count) {
        for (ItemsInShops item : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(6))).getLivesTock()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        return new Result(false,"No product with this name was found.");
    }
    public static Result buyItemsInShopsProduct(ItemsInShops item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if (item.getName().contains("Recipe")){
            //App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes().add(Recipe)
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true,"Item purchased");
        }
        else {
            Item temp = CommonGameController.findItem(item.getName());
            if (App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp, count) == false) {
                return new Result(false, "No space in Backpack.");
            }
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
        }
    }
    public static Result buyUpgradeToolsInBlackSmith(BlackSmithShop.upgradesToolsBlacsmithShop item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if (CommonGameController.numberOfItemInBackPack(item.getIngridientsString()) < item.getIngredientsInt()){
            return new Result(false,"Not enough " + item.getIngridientsString());
        }
        Item product = CommonGameController.findItem(item.getName());
        // what is Copper Tool??
        //TODO
        return null;
    }
    public static Result buyBackPacs(PierreGeneralShop.BackPacksItems item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if (item.getName().equals("Deluxe Pack") && App.getCurrentGame().getCurrentPlayer().getBackpack().equals(BackPackLevel.DELUX)){
            return new Result(false,"You already have this one");
        }
        if (item.getName().equals("Large Pack") && App.getCurrentGame().getCurrentPlayer().getBackpack().equals(BackPackLevel.LARGE)){
            return new Result(false,"You already have this one");
        }
        if(item.isPurchaseAble() == false && App.getCurrentGame().getCurrentPlayer().getBackpack().equals(BackPackLevel.NORMAL)){
            return new Result(false,"You should buy Large Backpack first.");
        }
        App.getCurrentGame().getCurrentPlayer().getBackpack().upgrade();
        App.getCurrentGame().getCurrentPlayer().spentMoney(count * item.getPrice());
        item.setCount(item.getCount()-1);
        return new Result(true, "God willing, wear it in joy.");
    }
    public static Result buySeasonalItems(PierreGeneralShop.SeasonalStockItems item, int count, String season){
        int price = 0;
        if (App.getCurrentGame().getDateTime().getSeason().toString().toLowerCase().equals(season)){
            price = item.getPriceInSeason();
        }
        else{
            price = item.getPriceOutOfSeason();
        }
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * price > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        Item temp = CommonGameController.findItem(item.getName());
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp,count) == false){
            return new Result(false, "No space in Backpack.");
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(price * item.getCount());
        if (item.getCount() != -1){
            item.setCount(item.getCount()-1);
        }
        return new Result(true, "Item purchased");
    }
    public static Result buyFarmBuilding(CarpenterShop.ItemsinCarpenterShop item, int count){
        //waiting to farmbuildings get finishd
        return null;
    }
    public static Result buyStockFromFishShop(FishShop.StockInShop item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }

        if (item.getName().contains("Recipe")){
            //App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes().add(Recipe)
            return new Result(true,"Item purchased");
        }
        if (item.getName().contains("Rod") || item.getName().contains("Pole")){
            // how to get fishing skill?
            // these are some tools that i didn't found in any place
        }
        else {
            Item temp = CommonGameController.findItem(item.getName());
            if (App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp, count) == false) {
                return new Result(false, "No space in Backpack.");
            }
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
        }
        return null;
    }
    public static  Result buyLivesStockInMarine(MarnieRanch.ItemsInMarineRanch item, int count){
        return null;
    }
}
