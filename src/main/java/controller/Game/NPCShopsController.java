package controller.Game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.AnimalHouse;
import model.App;
import model.Game;
import model.Result;
import model.Shops.*;
import model.alive.Animal;
import model.alive.Human;
import model.alive.NPC;
import model.alive.Player;
import model.enums.BackPackLevel;
import model.enums.SkillType;
import model.enums.ToolLevel;
import model.items.Item;
import model.items.ItemsInShops;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.items.tools.FishingPole;
import model.items.tools.MilkPail;
import model.items.tools.Shear;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NPCShopsController {
    // TODO add upperleft and lowerright locations of buildings to each shop

    static {
        //creating blacksmith shop
        Human human = new NPC();
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
        Human human1 = new NPC();
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
        Human human2 = new NPC();
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
        Human human3 = new NPC();
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
        Human human4 = new NPC();
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
        Human human5 = new NPC();
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
        Human human6 = new NPC();
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

    public static Result buySomthingFromMarnie(String itemName, int count,String name) {
        for (ItemsInShops item : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getShopInventory()){
            if (item.getName().equals(itemName)){
                return buyItemsInShopsProduct(item,count);
            }
        }
        for (MarnieRanch.ItemsInMarineRanch item : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getLivesTock()){
            if (item.getName().equals(itemName)){
                return buyLivesStockInMarine(item,count,name);
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

        if(item.getName().equals("Milk Pail")){
            MilkPail milkPail = new MilkPail();
            //TODO player.tools.add
        }

        else if(item.getName().equals("Shears")){
            Shear shear = new Shear();
            //TODO player.tools.add
        }
        else if(item.getName().contains(" (Recipe)")){
            String temp = item.getName().split("(Recipe)")[0];
            Recipe temp1 = Recipe.craftRecipes.get(temp);
            App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes().add(temp1);
        }
        else if (item.getName().contains("Recipe")){
            String temp = item.getName().split("Recipe")[0];
            Recipe temp1 = Recipe.foodRecipes.get(temp);
            App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes().add(temp1);
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
    public static Result buyUpgradeToolsInBlackSmith(BlackSmithShop.upgradesToolsBlacsmithShop item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if (CommonGameController.numberOfItemInBackPack(item.getIngredientString()) < item.getIngredientsInt()){
            return new Result(false,"Not enough " + item.getIngredientString());
        }
        Item temp = CommonGameController.findItem(item.getIngredientString());
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(temp, count) == false) {
            return new Result(false, "Not enough " + item.getIngredientString());
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
        //trashcan handle
        if (item.getName().contains("Trash")){
            if(item.getName().contains("Copper")){
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.COPPER);
            }
            if(item.getName().contains("Steel")){
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.IRON);
            }
            if(item.getName().contains("Gold")){
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.GOLD);
            }
            if(item.getName().contains("Iridium")){
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.IRIDIUM);
            }
        }
        //handle Tool
        if (item.getName().contains("Tool")){
            if(item.getName().contains("Copper")){
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.COPPER);
            }
            if(item.getName().contains("Steel")){
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.IRON);
            }
            if(item.getName().contains("Gold")){
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.GOLD);
            }
            if(item.getName().contains("Iridium")){
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.IRIDIUM);
            }
        }
        if (item.getCount() != -1){
            item.setCount(item.getCount()-1);
        }
        return new Result(true,"item purchased");
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
        Item wood = CommonGameController.findItem("Wood");
        Item stone = CommonGameController.findItem("Stone");
        if(item.getCount() < count){
            return new Result(false,"Not enough product.");
        }
        if(App.getCurrentGame().getCurrentPlayer().getMoney() < item.getPrice() * count){
            return new Result(false,"Not enough money.");
        }
        if(CommonGameController.numberOfItemInBackPack("Wood") < item.getWood() ){
            return new Result(false,"not enough wood");
        }
        if(CommonGameController.numberOfItemInBackPack("Stone") < item.getStone()){
            return new Result(false,"not enough stone");
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(count * item.getPrice());
        if (item.getCount() != -1){
            item.setCount(item.getCount()-1);
        }
        //TODO ADD to player.getAnimnalHouse
        return null;
    }
    public static Result buyStockFromFishShop(FishShop.StockInShop item, int count){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if (App.getCurrentGame().getCurrentPlayer().getSkills().get(SkillType.FISHING).getLevel() < item.getPurchaseable()){
            return new Result(false, "fishing skill required");
        }
        if (item.getName().contains("Recipe")){
            Recipe recipe = Recipe.craftRecipes.get("Fish Smoker");
            App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes().add(recipe);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true,"Item purchased");
        }
        else if (item.getName().contains("Rod") || item.getName().contains("Pole")){
            FishingPole pole = new FishingPole();
            if(item.getName().contains("Bamboo")){
                pole.setToolLevel(ToolLevel.BAMBOO);
            }
            if(item.getName().contains("Training")){
                pole.setToolLevel(ToolLevel.TRAINING);
            }
            if(item.getName().contains("Fiberglass")){
                pole.setToolLevel(ToolLevel.FIBERGLASS);
            }
            if(item.getName().contains("Iridium")){
                pole.setToolLevel(ToolLevel.IRIDIUM);
            }
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
            //TODO player.tools.add
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
    public static  Result buyLivesStockInMarine(MarnieRanch.ItemsInMarineRanch item, int count,String name){
        if (count > item.getCount() && item.getCount() != -1){
            return new Result(false,"Not enough product.");
        }
        if(count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()){
            return new Result(false,"Not enough money.");
        }
        if(App.getCurrentGame().getCurrentPlayer().getAnimals().get(name) != null){
            return new Result(false,"this name is for another animal");
        }
        //TODO check Animal house exist
        App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
        if (item.getCount() != -1) {
            item.setCount(item.getCount() - 1);
        }
        //Animal temp = Animal.animals.get(item.getName());
        //Animal animal = temp.clone();
        //TODO App.getplayer.AnimalHouses.add animal
        return new Result(true,"animal purchased");
    }
}
