package controller.Game;

import model.App;
import model.Game;
import model.Result;
import model.enums.*;
import model.items.tools.Tool;
import model.lives.Animal;
import model.items.Item;
import model.items.ShopItem;
import model.items.UniqueItem;
import model.items.recipes.Recipe;
import model.items.tools.FishingPole;
import model.lives.Player;
import model.map.*;
import model.map.Shops.*;

import java.util.ArrayList;

public class ShopsController {
    // HANDLE FISHING POLE AND RECIPE ONE BUY ?

    public static Result purchaseItem(String itemName, int count) {
        Game game = App.getCurrentGame();
        World world = game.getWorld();
        Player player = App.getCurrentGame().getCurrentPlayer();

        Tile playerTile = world.getTileAt(player.getCurrentLocation());

        if (!(playerTile.getThingOnTile() instanceof Shop shop)) {
            return new Result(false, "You are not in a shop");
        }

        if (shop.getOwner().getName().equals("Clint")) {
            return buySomethingFromBlacksmith(itemName, count);
        }
        if (shop.getOwner().getName().equals("Morris")) {
            return buySomethingFromJojaMart(itemName, count);
        }
        if (shop.getOwner().getName().equals("Pierre")) {
            return buySomethingFromPierre(itemName, count);
        }
        if (shop.getOwner().getName().equals("Robin")) {
            return buySomethingFromCarpenter(itemName, count);
        }
        if (shop.getOwner().getName().equals("Willy")) {
            return buySomethingFromFishShop(itemName, count);
        }
        if (shop.getOwner().getName().equals("Marnie")) {
            return buySomethingFromMarnie(itemName, count);
        }
        if (shop.getOwner().getName().equals("Gus")) {
            return buySomethingFromStardrop(itemName, count);
        }

        return new Result(false, "ERROR: shop was not found!");
    }

    public static Result upgradeTool(String toolName) {
        Game game = App.getCurrentGame();
        World world = game.getWorld();
        Player player = game.getCurrentPlayer();

        if (!(world.getTileAt(player.getCurrentLocation()).getThingOnTile() instanceof BlackSmithShop)) {
            return new Result(false, "You can only upgrade your tools at the blacksmith");
        }

        ToolLevel currentLevel;
        boolean isUpgradeable;
        String upgradeType;

        if (toolName.equals("trash can")) {
            currentLevel = player.getTrashCan().getToolLevel();
            isUpgradeable = player.getTrashCan().isUpgradable();
            upgradeType = "Trash Can";
        } else {
            ToolType type = ToolType.fromString(toolName);
            if (type == null) {
                return new Result(false, "No such tool exists");
            }

            Tool tool = player.getTool(type);
            if (tool == null) {
                return new Result(false, "You don't have this tool");
            }

            currentLevel = player.getTool(type).getToolLevel();
            isUpgradeable = player.getTool(type).isUpgradable();
            upgradeType = "Tool";
        }

        if (!isUpgradeable) {
            return new Result(false, "You can't upgrade this tool");
        }

        ToolLevel nextLevel = ToolLevel.values()[currentLevel.ordinal() + 1];

        String itemName = nextLevel.toString() + " " + upgradeType;

        for (BlackSmithShop.UpgradeToolBlackSmith item : ((BlackSmithShop) (App.getCurrentGame().getWorld().getShops().get(0))).getUpgradeTools()) {
            if (item.getName().equals(toolName)) {
                return buyUpgradeToolsInBlackSmith(item, 1);
            }
        }

        return new Result(false, "ERROR: wrong upgrade name");
    }

    public static Result buildAnimalHouse(String buildingName, Location location) {
        for (CarpenterShop.ItemsinCarpenterShop item : ((CarpenterShop) (App.getCurrentGame().getWorld().getShops().get(3))).getFarmBuildings()) {
            if (item.getName().equals(buildingName)) {
                return buyFarmBuilding(item, location);
            }
        }

        return new Result(false, "No building with this name was found");
    }

    public static Result buyAnimal(String animalName, String name) {
        for (MarnieRanch.ItemsInMarnieRanch item : ((MarnieRanch) (App.getCurrentGame().getWorld().getShops().get(5))).getLiveStock()) {
            if (item.getName().equals(animalName)) {
                return buyLivesStockInMarnie(item, name);
            }
        }

        return new Result(false, "No such animal exists");
    }

    public static Result showAvailableProducts() {
        ArrayList<String> temp;
        if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof BlackSmithShop) {
            temp = ShopsController.showProductsInBlackSmith(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch) {
            temp = ShopsController.showProductsMarineRanch(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof CarpenterShop) {
            temp = ShopsController.showCarpenterShop(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof JojoMartShop) {
            temp = ShopsController.showJojaMart(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof PierreGeneralShop) {
            temp = ShopsController.showPierreGeneralShop(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof FishShop) {
            temp = ShopsController.showFishShop(true);
        } else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof TheStardropSaloonShop) {
            temp = ShopsController.showProductsStardropSaloon(true);
        } else {
            return new Result(false, "You must be in a shop to use this command");
        }

        StringBuilder message = new StringBuilder();
        for (String st : temp) {
            message.append(st).append("\n");
        }

        return new Result(true, message.toString().trim());
    }

    public static Result showAllProducts() {
        ArrayList<String> temp;
        if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof BlackSmithShop) {
            temp = ShopsController.showProductsInBlackSmith(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch) {
            temp = ShopsController.showProductsMarineRanch(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof CarpenterShop) {
            temp = ShopsController.showCarpenterShop(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof JojoMartShop) {
            temp = ShopsController.showJojaMart(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof PierreGeneralShop) {
            temp = ShopsController.showPierreGeneralShop(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof FishShop) {
            temp = ShopsController.showFishShop(false);
        }
        else if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof TheStardropSaloonShop) {
            temp = ShopsController.showProductsStardropSaloon(false);
        } else {
            return new Result(false, "You must be in a shop to use this command");
        }

        StringBuilder message = new StringBuilder();
        for (String st : temp) {
            message.append(st).append("\n");
        }

        return new Result(true, message.toString().trim());
    }

    private static Result buySomethingFromBlacksmith(String itemName, int count) {
        for (ShopItem item : ((BlackSmithShop) (App.getCurrentGame().getWorld().getShops().get(0))).getStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }

        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromJojaMart(String itemName, int count) {
        for (ShopItem item : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(1))).getPermanentStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        for (ShopItem item : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(1))).getSpringStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        for (ShopItem item : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(1))).getSummerStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        for (ShopItem item : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(1))).getFallStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        for (ShopItem item : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(1))).getWinterStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromPierre(String itemName, int count) {
        for (ShopItem item : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(2))).getYearRoundStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        for (PierreGeneralShop.BackPacksItems item : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(2))).getBackPacks()) {
            if (item.getName().equals(itemName)) {
                return buyBackPacks(item, count);
            }
        }
        for (PierreGeneralShop.SeasonalStockItems item : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(2))).getSpringStock()) {
            if (item.getName().equals(itemName)) {
                return buySeasonalItems(item, count, "spring");
            }
        }

        for (PierreGeneralShop.SeasonalStockItems item : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(2))).getSummerStock()) {
            if (item.getName().equals(itemName)) {
                return buySeasonalItems(item, count, "summer");
            }
        }
        for (PierreGeneralShop.SeasonalStockItems item : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(2))).getFallStock()) {
            if (item.getName().equals(itemName)) {
                return buySeasonalItems(item, count, "fall");
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromCarpenter(String itemName, int count) {
        for (ShopItem item : ((CarpenterShop) (App.getCurrentGame().getWorld().getShops().get(3))).getPermanentStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromFishShop(String itemName, int count) {
        for (FishShop.StockInShop item : ((FishShop) (App.getCurrentGame().getWorld().getShops().get(4))).getStockInShop()) {
            if (item.getName().equals(itemName)) {
                return buyStockFromFishShop(item, count);
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromMarnie(String itemName, int count) {
        for (ShopItem item : ((MarnieRanch) (App.getCurrentGame().getWorld().getShops().get(5))).getShopInventory()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buySomethingFromStardrop(String itemName, int count) {
        for (ShopItem item : ((MarnieRanch) (App.getCurrentGame().getWorld().getShops().get(6))).getLiveStock()) {
            if (item.getName().equals(itemName)) {
                return buyItemsInShopsProduct(item, count);
            }
        }
        return new Result(false, "No product with this name was found.");
    }

    private static Result buyItemsInShopsProduct(ShopItem item, int count) {
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }

        if (item.getName().equals("Milk Pail")) {
            Tool milkPail = new Tool(ToolType.MILK_PAIL);
            App.getCurrentGame().getCurrentPlayer().setTool(ToolType.MILK_PAIL, milkPail);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
        } else if (item.getName().equals("Shears")) {
            Tool shear = new Tool(ToolType.SHEAR);
            App.getCurrentGame().getCurrentPlayer().setTool(ToolType.SHEAR, shear);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");

        } else if (item.getName().contains(" (Recipe)")) {
            String temp = item.getName().split("\\(Recipe\\)")[0];
            Recipe temp1 = Recipe.craftRecipes.get(temp);
            App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes().add(temp1);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");

        } else if (item.getName().contains(" Recipe")) {
            String temp = item.getName().split(" Recipe")[0];
            Recipe temp1 = Recipe.foodRecipes.get(temp);
            App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes().add(temp1);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");

        } else {
            Item temp = CommonGameController.findItem(item.getName());
            if (temp == null) {
                UniqueItem uniqueItem = UniqueItem.getUniqueItem(item.getName());
                if (App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(uniqueItem, count) == false) {
                    return new Result(false, "No space in Backpack.");
                }
                App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
                if (item.getCount() != -1) {
                    item.setCount(item.getCount() - 1);
                }
                return new Result(true, "Item purchased");
            } else {
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
    }

    private static Result buyUpgradeToolsInBlackSmith(BlackSmithShop.UpgradeToolBlackSmith item, int count) {
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }
        if (CommonGameController.numberOfItemInBackPack(item.getIngredientString()) < item.getIngredientsInt()) {
            return new Result(false, "Not enough " + item.getIngredientString());
        }
        Item temp = CommonGameController.findItem(item.getIngredientString());
        if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(temp, count)) {
            return new Result(false, "Not enough " + item.getIngredientString());
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
        //trashcan handle
        if (item.getName().contains("Trash")) {
            if (item.getName().contains("Copper")) {
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.COPPER);
            }
            if (item.getName().contains("Steel")) {
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.IRON);
            }
            if (item.getName().contains("Gold")) {
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.GOLD);
            }
            if (item.getName().contains("Iridium")) {
                App.getCurrentGame().getCurrentPlayer().getTrashCan().setToolLevel(ToolLevel.IRIDIUM);
            }
        }
        //handle Tool
        if (item.getName().contains("Tool")) {
            if (item.getName().contains("Copper")) {
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.COPPER);
            }
            if (item.getName().contains("Steel")) {
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.IRON);
            }
            if (item.getName().contains("Gold")) {
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.GOLD);
            }
            if (item.getName().contains("Iridium")) {
                App.getCurrentGame().getCurrentPlayer().getEquippedTool().setToolLevel(ToolLevel.IRIDIUM);
            }
        }
        if (item.getCount() != -1) {
            item.setCount(item.getCount() - 1);
        }
        return new Result(true, "item purchased");
    }

    private static Result buyBackPacks(PierreGeneralShop.BackPacksItems item, int count) {
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }
        if (item.getName().equals("Deluxe Pack") && App.getCurrentGame().getCurrentPlayer().getBackpack().getLevel().equals(BackPackLevel.DELUX)) {
            return new Result(false, "You already have this one");
        }
        if (item.getName().equals("Large Pack") && App.getCurrentGame().getCurrentPlayer().getBackpack().getLevel().equals(BackPackLevel.LARGE)) {
            return new Result(false, "You already have this one");
        }
        if (!item.isPurchaseAble() && App.getCurrentGame().getCurrentPlayer().getBackpack().getLevel().equals(BackPackLevel.NORMAL)) {
            return new Result(false, "You should buy Large Backpack first.");
        }
        App.getCurrentGame().getCurrentPlayer().getBackpack().upgrade();
        App.getCurrentGame().getCurrentPlayer().spentMoney(count * item.getPrice());
        item.setCount(item.getCount() - 1);
        return new Result(true, "God willing, wear it in joy.");
    }

    private static Result buySeasonalItems(PierreGeneralShop.SeasonalStockItems item, int count, String season) {
        int price = 0;
        if (App.getCurrentGame().getDateTime().getSeason().toString().toLowerCase().equals(season)) {
            price = item.getPriceInSeason();
        } else {
            price = item.getPriceOutOfSeason();
        }
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * price > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }
        Item temp = CommonGameController.findItem(item.getName());
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp, count) == false) {
            return new Result(false, "No space in Backpack.");
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(price * item.getCount());
        if (item.getCount() != -1) {
            item.setCount(item.getCount() - 1);
        }
        return new Result(true, "Item purchased");
    }

    private static Result buyFarmBuilding(CarpenterShop.ItemsinCarpenterShop item, Location location) {
        Item wood = CommonGameController.findItem("Wood");
        Item stone = CommonGameController.findItem("Stone");
        int count = 1;

        if (item.getCount() < count) {
            return new Result(false, "Not enough product.");
        }
        if (App.getCurrentGame().getCurrentPlayer().getMoney() < item.getPrice() * count) {
            return new Result(false, "Not enough money.");
        }
        if (CommonGameController.numberOfItemInBackPack("Wood") < item.getWood()) {
            return new Result(false, "not enough wood");
        }
        if (CommonGameController.numberOfItemInBackPack("Stone") < item.getStone()) {
            return new Result(false, "not enough stone");
        }

        AnimalHousePrototype prototype = AnimalHousePrototype.getAnimalHousePrototype(item.getName());
        if (prototype == null) {
            return new Result(false, "No animal house found.");
        }

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(location);
        if (farm == null) {
            return new Result(false, "You can only build this in a farm.");
        }

        if (App.getCurrentGame().getWorld()
                .getFarmAt(location.add(new Location(prototype.getNumberOfRows(), prototype.getNumberOfColumns())))
                == null) {

            return new Result(false, "You can't build this at that location");
        }

        App.getCurrentGame().getCurrentPlayer().spentMoney(count * item.getPrice());
        if (item.getCount() != -1) {
            item.setCount(item.getCount() - 1);
        }
        AnimalHouse animalHouse = new AnimalHouse(prototype, location);


        for (int i = 0; i < animalHouse.getNumberOfRows(); i++) {
            for (int j = 0; j < animalHouse.getNumberOfColumns(); j++) {
                farm.getTileAt(location.add(new Location(i, j))).setThingOnTile(animalHouse);
            }
        }

        return new Result(true, item.getName() + " built!");
    }

    private static Result buyStockFromFishShop(FishShop.StockInShop item, int count) {
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }
        if (App.getCurrentGame().getCurrentPlayer().getSkill(SkillType.FISHING).getLevel() < item.getPurchaseable()) {
            return new Result(false, "fishing skill required");
        }
        if (item.getName().contains("Recipe")) {
            Recipe recipe = Recipe.craftRecipes.get("Fish Smoker");
            App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes().add(recipe);
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
        } else if (item.getName().contains("Rod") || item.getName().contains("Pole")) {
            FishingPoleType poleLevel;
            if (item.getName().contains("Bamboo")) {
                poleLevel = FishingPoleType.BAMBOO;
            } else if (item.getName().contains("Training")) {
                poleLevel = FishingPoleType.TRAINING;
            } else if (item.getName().contains("Fiberglass")) {
                poleLevel = FishingPoleType.FIBERGLASS;
            } else if (item.getName().contains("Iridium")) {
                poleLevel = FishingPoleType.IRIDIUM;
            } else {
                return null;
            }

            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            App.getCurrentGame().getCurrentPlayer().setFishingPole(poleLevel, new FishingPole(poleLevel));
            return new Result(true, "Item purchased");
        } else {
            Item temp = CommonGameController.findItem(item.getName());
            if (!App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(temp, count)) {
                return new Result(false, "No space in Backpack.");
            }
            App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
            if (item.getCount() != -1) {
                item.setCount(item.getCount() - 1);
            }
            return new Result(true, "Item purchased");
        }
    }

    private static Result buyLivesStockInMarnie(MarnieRanch.ItemsInMarnieRanch item, String name) {
        int count = 1;
        if (count > item.getCount() && item.getCount() != -1) {
            return new Result(false, "Not enough product.");
        }
        if (count * item.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
            return new Result(false, "Not enough money.");
        }
        if (App.getCurrentGame().getCurrentPlayer().getAnimals().get(name) != null) {
            return new Result(false, "this name is for another animal");
        }
        boolean temp = false;
        for (AnimalHouse animalHouse : App.getCurrentGame().getCurrentPlayer().getFarm().getAnimalHouses()) {
            if (animalHouse.getName().equals(item.getBuildingRequired())) {
                temp = true;
            }
        }
        if (!temp) {
            return new Result(false, "building required");
        }
        App.getCurrentGame().getCurrentPlayer().spentMoney(item.getPrice() * item.getCount());
        if (item.getCount() != -1) {
            item.setCount(item.getCount() - 1);
        }
        Animal animal = Animal.getAnimal(item.getName());
        App.getCurrentGame().getCurrentPlayer().getAnimals().put(name, animal);
        return new Result(true, "animal purchased");
    }

    private static ArrayList<String> showProductsInBlackSmith(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((BlackSmithShop) (App.getCurrentGame().getWorld().getShops().get(0))).getStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Stock");
        products.add("Name           Ingredient                             Price           Daily Limit");
        for (BlackSmithShop.UpgradeToolBlackSmith items : ((BlackSmithShop) (App.getCurrentGame().getWorld().getShops().get(0))).getUpgradeTools()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getIngredientString() + ": " + items.getIngredientsInt() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getIngredientString() + ": " + items.getIngredientsInt() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getIngredientString() + ": " + items.getIngredientsInt() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showProductsMarineRanch(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Shop Inventory");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((MarnieRanch) (App.getCurrentGame().getWorld().getShops().get(1))).getShopInventory()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Lives Stock");
        products.add("Name           Description                             Price          Building required           Daily Limit");
        for (MarnieRanch.ItemsInMarnieRanch items : ((MarnieRanch) (App.getCurrentGame().getWorld().getShops().get(1))).getLiveStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "    " + items.getBuildingRequired() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "    " + items.getBuildingRequired() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "    " + items.getBuildingRequired() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showProductsStardropSaloon(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Permanent Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((TheStardropSaloonShop) (App.getCurrentGame().getWorld().getShops().get(2))).getPermanentStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showCarpenterShop(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Permanent Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((CarpenterShop) (App.getCurrentGame().getWorld().getShops().get(3))).getPermanentStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Farm Buildings");
        products.add("Name           Description                       Price         size       Daily Limit");
        for (CarpenterShop.ItemsinCarpenterShop items : ((CarpenterShop) (App.getCurrentGame().getWorld().getShops().get(3))).getFarmBuildings()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + " stone: " + items.getStone() + " wood:  " + items.getWood() + "   " + items.getxSize() + "X" + items.getySize() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + " stone: " + items.getStone() + " wood:  " + items.getWood() + "   " + items.getxSize() + "X" + items.getySize() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + " stone: " + items.getStone() + " wood:  " + items.getWood() + "   " + items.getxSize() + "X" + items.getySize() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showJojaMart(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Permanent Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((CarpenterShop) (App.getCurrentGame().getWorld().getShops().get(4))).getPermanentStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Spring Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(4))).getSpringStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Summer Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(4))).getSummerStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Fall Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(4))).getFallStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Winter Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((JojoMartShop) (App.getCurrentGame().getWorld().getShops().get(4))).getWinterStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showPierreGeneralShop(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Year-Round Stock");
        products.add("Name           Description                             Price           Daily Limit");
        for (ShopItem items : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(5))).getYearRoundStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("backPacks");
        products.add("Name           Description                             Price          Available           Daily Limit");
        for (PierreGeneralShop.BackPacksItems items : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(5))).getBackPacks()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getIsAvailable() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getIsAvailable() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getIsAvailable() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Spring Stock");
        products.add("Name           Description                             Price In/Out           Daily Limit");
        for (PierreGeneralShop.SeasonalStockItems items : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(5))).getSpringStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Summer Stock");
        products.add("Name           Description                             Price In/Out           Daily Limit");
        for (PierreGeneralShop.SeasonalStockItems items : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(5))).getSummerStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                    }
                }
            }
        }
        products.add("Fall Stock");
        products.add("Name           Description                             Price In/Out           Daily Limit");
        for (PierreGeneralShop.SeasonalStockItems items : ((PierreGeneralShop) (App.getCurrentGame().getWorld().getShops().get(5))).getFallStock()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPriceInSeason() + "/" + items.getPriceOutOfSeason() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }

    private static ArrayList<String> showFishShop(Boolean available) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Stock");
        products.add("Name           Description                             Price         Fishing Skill         Daily Limit");
        for (FishShop.StockInShop items : ((FishShop) (App.getCurrentGame().getWorld().getShops().get(6))).getStockInShop()) {
            if (items.getCount() == -1) {
                products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getPurchaseable() + "   unlimited");
            } else {
                if (!available) {
                    products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getPurchaseable() + "   " + items.getCount());
                } else {
                    if (items.getCount() > 0) {
                        products.add(items.getName() + "    " + items.getDescription() + "    " + items.getPrice() + "      " + items.getPurchaseable() + "   " + items.getCount());
                    }
                }
            }
        }
        return products;
    }
}
