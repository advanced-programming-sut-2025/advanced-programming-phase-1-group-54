package controller.Game;

import model.App;
import model.Game;
import model.enums.*;
import model.items.plants.Crop;
import model.lives.Animal;
import model.map.*;
import model.map.Refrigerator;
import model.Result;
import model.lives.Player;
import model.items.*;
import model.items.crafting.Produce;
import model.items.crafting.ProducerArtisan;
import model.items.crafting.FeatureArtisan;
import model.items.plants.Fruit;
import model.items.plants.Seed;
import model.items.tools.BackPack;


public class CommonGameController {
    public static Result exitGame() {
        App.setCurrentGame(null);
        return new Result(true, "exited game");
    }

    public static Result startDeleteGameVote() {
        return null;
    }

    // TODO add functions for "force terminate"

    public static Result passOut() {
        App.getCurrentGame().getCurrentPlayer().setEnergy(0);
        return new Result(true, "you passed out!\n" + nextTurn().message());
    }

    public static Result nextTurn() {
        Game game = App.getCurrentGame();
        game.nextTurn();

        StringBuilder message = new StringBuilder(String.format("it is %s's turn",
                game.getCurrentPlayer().getName()));

        // TODO
        return new Result(true, message.toString());
    }

    private static String getNotification() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        StringBuilder messageBuilder = new StringBuilder();

        if (!player.getReceivedTrades().isEmpty()) {
            messageBuilder.append("\nYou have some trade to do");
        }
        if (!player.getReceivedGifts().isEmpty()) {
            messageBuilder.append("\nYou have some gift to open");
        }
        if (!(player.getReceivedRequests()).isEmpty()) {
            messageBuilder.append("\nYou have some marriage proposal");
        }

        return messageBuilder.toString();
    }

    public static Result showTime() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d o'clock", game.getDateTime().getHour()));
    }

    public static Result showDate() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d/%s/%d",
                game.getDateTime().getYear(), game.getDateTime().getSeason().toString().toLowerCase(), game.getDateTime().getDay()));
    }

    public static Result showDateTime() {
        return new Result(true, showDate().message() + ' ' + showTime().message());

    }

    public static Result showDayOfWeek() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getWeekDay().toString().toLowerCase());
    }

    public static Result showSeason() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getSeason().toString().toLowerCase());
    }

    public static Result showWeather() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getCurrentWeather().toString().toLowerCase());
    }

    public static Result showWeatherForecast() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getTomorrowWeather().toString().toLowerCase());
    }

    public static Result showEnergy() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        return new Result(true, String.format("you have %d energy left.", player.getEnergy()));
    }

    public static Result showCurrentTool() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        return new Result(true, player.getEquippedTool().toString());
    }

    public static Result showAvailableTools() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        StringBuilder messageBuilder = new StringBuilder();
        for (ToolType toolType : ToolType.values()) {
            messageBuilder.append(toolType.toString()).append(": ").append(player.getTool(toolType).toString());
        }
        return new Result(true, messageBuilder.toString());
    }


    static Item findItem(String ItemName) {

        Seed seed = Seed.getSeed(ItemName);
        if (seed != null) {
            return seed;
        }

        Fruit fruit = Fruit.getFruit(ItemName);
        if (fruit != null) {
            return fruit;
        }

//        Artisan artisan = Artisan.getArtisan(ItemName);
//        if(artisan != null){
//            return artisan;
//        }

        ProducerArtisan producerArtisan = ProducerArtisan.getProducerArtisan(ItemName);
        if (producerArtisan != null) {
            return producerArtisan;
        }

        FeatureArtisan featureArtisan = FeatureArtisan.getFeatureArtisan(ItemName);
        if (featureArtisan != null) {
            return featureArtisan;
        }

        Fish fish = Fish.getFish(ItemName);
        if (fish != null) {
            return fish;
        }

        Food food = Food.getFood(ItemName);
        if (food != null) {
            return food;
        }

        Material material = Material.getMaterial(ItemName);
        if (material != null) {
            return material;
        }

        Produce produce = Produce.getProduce(ItemName);
        if (produce != null) {
            return produce;
        }

        AnimalProduce animalProduce = AnimalProduce.getAnimalProduce(ItemName);
        if (animalProduce != null) {
            return animalProduce;
        }

        Fertilize fertilize = Fertilize.getFertilizer(ItemName);
        if (fertilize != null) {
            return fertilize;
        }

        UniqueItem uniqueItem = UniqueItem.getUniqueItem(ItemName);
        if (uniqueItem != null) {
            return uniqueItem;
        }

        return null;
    }

    static int numberOfItemInBackPack(String ItemName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Integer number = 0;
        if (ItemName.equals("fish")) {
            for (Fish fish : Fish.getFishesValues()) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fish, 0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fish, 0);
                }
            }
        } else if (ItemName.equals("Cheese") || ItemName.equals("Goat Cheese") || ItemName.equals("Mayonnaise")) {
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce(ItemName), 0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce(ItemName), 0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Large " + ItemName), 0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Large " + ItemName), 0);
        } else if (ItemName.equals("Oil")) {
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Corn " + ItemName), 0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Corn " + ItemName), 0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Sunflower Seed " + ItemName), 0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Sunflower Seed " + ItemName), 0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Sunflower " + ItemName), 0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Sunflower " + ItemName), 0);
        } else {
            Item item = findItem(ItemName);
            if (item == null) {
                return 0;
            } else if (item instanceof Fish fish) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fish, 0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fish, 0);
                }
            } else if (item instanceof Fruit fruit) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fruit.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fruit, 0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fruit, 0);
                }
            } else if (item instanceof AnimalProduce animalProduce) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    animalProduce.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(animalProduce, 0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                            getOrDefault(animalProduce, 0);
                }
            } else {
                number = player.getBackpack().getNumberOfItemInBackPack().getOrDefault(item, 0);
                number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(item, 0);
            }

        }

        return number;

    }

    static void removeItemFromBackPack(String ItemName, int number) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        BackPack backPack = player.getBackpack();
        Refrigerator refrigerator = player.getRefrigerator();

        int amount;
        if (ItemName.equals("fish")) {
            boolean isDone = false;
            for (Fish fish : Fish.getFishesValues()) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fish.setQuality(quality);

                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fish, 0);
                    if (amount <= number) {
                        backPack.removeItem(fish, amount);
                        number -= amount;
                    } else {
                        backPack.removeItem(fish, number);
                        number = 0;
                    }
                    if (number == 0) {
                        isDone = true;
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fish, 0);
                    if (amount <= number) {
                        refrigerator.removeItem(fish, amount);
                        number -= amount;
                    } else {
                        refrigerator.removeItem(fish, number);
                        number = 0;
                    }
                    if (number == 0) {
                        isDone = true;
                        break;
                    }
                }
                if (isDone) {
                    break;
                }
            }
        } else if (ItemName.equals("Cheese") || ItemName.equals("Goat Cheese") || ItemName.equals("Mayonnaise")) {
            Produce produce = Produce.getProduce(ItemName);
            amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce, 0);
            amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce, 0);

            if (number > amount) {
                removeItemFromInventory(produce, amount);
                number -= amount;
                removeItemFromInventory(Produce.getProduce("Large " + ItemName), number);
            } else {
                removeItemFromInventory(produce, number);
            }
        } else if (ItemName.equals("Oil")) {
            Produce produce = Produce.getProduce("Corn " + ItemName);
            amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce, 0);
            amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce, 0);
            if (number > amount) {
                removeItemFromInventory(produce, amount);
                number -= amount;
                produce = Produce.getProduce("Sunflower Seed " + ItemName);
                amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce, 0);
                amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce, 0);
                if (number > amount) {
                    removeItemFromInventory(produce, amount);
                    number -= amount;
                    removeItemFromInventory(Produce.getProduce("Sunflower " + ItemName), number);
                } else {
                    removeItemFromInventory(produce, number);
                }
            } else {
                removeItemFromInventory(produce, number);
            }
        } else {
            Item item = findItem(ItemName);
            if (item instanceof Fish fish) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fish.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fish, 0);
                    if (amount <= number) {
                        backPack.removeItem(fish, amount);
                        number -= amount;
                    } else {
                        backPack.removeItem(fish, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fish, 0);
                    if (amount <= number) {
                        refrigerator.removeItem(fish, amount);
                        number -= amount;
                    } else {
                        refrigerator.removeItem(fish, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }
                }
            } else if (item instanceof Fruit fruit) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    fruit.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fruit, 0);
                    if (amount <= number) {
                        backPack.removeItem(fruit, amount);
                        number -= amount;
                    } else {
                        backPack.removeItem(fruit, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fruit, 0);
                    if (amount <= number) {
                        refrigerator.removeItem(fruit, amount);
                        number -= amount;
                    } else {
                        refrigerator.removeItem(fruit, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }
                }
            } else if (item instanceof AnimalProduce animalProduce) {
                for (ProduceQuality quality : ProduceQuality.values()) {
                    animalProduce.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(animalProduce, 0);
                    if (amount <= number) {
                        backPack.removeItem(animalProduce, amount);
                        number -= amount;
                    } else {
                        backPack.removeItem(animalProduce, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(animalProduce, 0);
                    if (amount <= number) {
                        refrigerator.removeItem(animalProduce, amount);
                        number -= amount;
                    } else {
                        refrigerator.removeItem(animalProduce, number);
                        number = 0;
                    }

                    if (number == 0) {
                        break;
                    }
                }
            } else {
                amount = backPack.getNumberOfItemInBackPack().getOrDefault(item, 0);
                if (amount <= number) {
                    backPack.removeItem(item, amount);
                    number -= amount;
                    refrigerator.removeItem(item, number);
                }
                backPack.removeItem(item, number);
            }
        }
    }

    static boolean removeItemFromInventory(Item item, int number) {

        Player player = App.getCurrentGame().getCurrentPlayer();
        int amount = player.getBackpack().getNumberOfItemInBackPack().getOrDefault(item, 0);
        if (number - amount > player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(item, 0)) {
            return false;
        } else if (amount < number) {
            player.getBackpack().removeItem(item, amount);
            number -= amount;
            player.getRefrigerator().removeItem(item, number);
        }
        player.getBackpack().removeItem(item, number);
        return true;

    }

    static boolean deleteThingOnTile(Tile tile, Farm farm) {
        Player player = App.getCurrentGame().getCurrentPlayer();

        Location location = player.getCurrentLocation().delta(farm.getLocation());

        if (tile.getThingOnTile() instanceof FeatureArtisan featureArtisan) {
            for (int i = -featureArtisan.getRadius(); i <= featureArtisan.getRadius(); i++) {
                for (int j = -featureArtisan.getRadius(); j <= featureArtisan.getRadius(); j++) {
                    Location location1 = location.delta(new Location(location.row() + i, location.column() + j));
                    Tile tile1 = farm.getTileAt(location1);
                    if (tile1 != null) {
                        tile.getFeatures().remove(featureArtisan.getFeature());
                    }
                }
            }
        } else if (tile.getThingOnTile() instanceof ProducerArtisan producerArtisan) {
            tile.setThingOnTile(null);
            player.getPlacedArtisans().remove(producerArtisan);
        } else if (tile.getThingOnTile() instanceof Crop crop && crop.getGiantDirection() != null) {
            tile.setThingOnTile(null);
            for (int i = 0; i < 3; i++) {
                tile = App.getCurrentGame().getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection()));
                crop = (Crop) tile.getThingOnTile();
                tile.setThingOnTile(null);
            }
        } else if (tile.getThingOnTile() instanceof Building) {
            return deleteThingOnTile(tile.getTop(), farm);
        } else if (!(tile.getThingOnTile() instanceof Animal)) {
            tile.setThingOnTile(null);
        } else {
            return false;
        }
        return true;
    }

    public static Result sell(String product, int count) {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        if (!isNearShippingBin(currentPlayer.getCurrentLocation()))
            return new Result(false, "No shipping bin nearby!");


        Item item = findItem(product);

        if(item == null){
            return new Result(false, "Doesn't exist such item!");
        }

        int numberOfItem = currentPlayer.getBackpack().getNumberOfItemInBackPack().get(item);

        if(count == -1){
            count = numberOfItem;
        }
        else if(count > numberOfItem){
            return new Result(false,"You do not have enough item!");
        }

        currentPlayer.getBackpack().removeItem(item, count);

        if(item instanceof Fruit fruit){
            currentPlayer.increaseNextDayMoney((int) Math.floor(count * item.getBaseSellPrice() *
                    fruit.getQuality().getValue()));
        }
        else if(item instanceof AnimalProduce animalProduce){
            currentPlayer.increaseNextDayMoney((int) Math.floor(count * item.getBaseSellPrice() *
                    animalProduce.getQuality().getValue()));
        }
        else if(item instanceof Fish fish){
            currentPlayer.increaseNextDayMoney((int) Math.floor(count * item.getBaseSellPrice() *
                    fish.getQuality().getValue()));
        }
        else{
            if(item.getBaseSellPrice() == 0){
                return new Result(false,"You can't sell this item!");
            }
            currentPlayer.increaseNextDayMoney( count * item.getBaseSellPrice());
        }

        return new Result(true,count + " of item sold successfully");
        // TODO sell is wrong, fix this !!!

//        Fish fish = Fish.getFish(product);
//        if (fish != null) {
//            if (count != -1) {
//                if (!removeItemFromInventory(fish, count)) {
//                    return new Result(false, "not enough products");
//                }
//            } else if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fish) == 0) {
//                return new Result(false, "not enough products");
//            } else {
//                int money;
//                if (count == -1) {
//                    money = (int) (fish.getBaseSellPrice() * fish.getQuality().getValue())
//                            * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fish);
//                } else {
//                    money = (int) (fish.getBaseSellPrice() * fish.getQuality().getValue()) * count;
//                }
//                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
//                return new Result(true, "item sold successfully");
//            }
//        }
//        Fruit fruit = Fruit.getFruit(product);
//        if (fruit != null) {
//            if (count != -1) {
//                if (!removeItemFromInventory(fruit, count)) {
//                    return new Result(false, "not enough products");
//                }
//            } else if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fruit) == 0) {
//                return new Result(false, "not enough products");
//            } else {
//                int money = 0;
//                if (count == -1) {
//                    money = (int) (fruit.getBaseSellPrice() * fruit.getQuality().getValue())
//                            * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fruit);
//                } else {
//                    money = (int) (fish.getBaseSellPrice() * fruit.getQuality().getValue()) * count;
//                }
//                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
//                return new Result(true, "item sold successfully");
//            }
//        }
//
//        //Artisan artisan =
//        Food food = Food.getFood(product);
//        if (food != null) {
//            if (count != -1) {
//                if (!removeItemFromInventory(food, count)) {
//                    return new Result(false, "not enough products");
//                }
//            } else if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(food) == 0) {
//                return new Result(false, "not enough products");
//            } else {
//                int money = 0;
//                if (count == -1) {
//                    money = food.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(food);
//                } else {
//                    money = food.getBaseSellPrice() * count;
//                }
//                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
//                return new Result(true, "item sold successfully");
//            }
//        }
//        Produce produce = Produce.getProduce(product);
//        if (produce != null) {
//            if (count != -1) {
//                if (!removeItemFromInventory(produce, count)) {
//                    return new Result(false, "not enough products");
//                }
//            } else if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(produce) == 0) {
//                return new Result(false, "not enough products");
//            } else {
//                int money = 0;
//                if (count == -1) {
//                    money = produce.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(produce);
//                } else {
//                    money = produce.getBaseSellPrice() * count;
//                }
//                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
//                return new Result(true, "item sold successfully");
//            }
//        }
//        FeatureArtisan featureArtisan = FeatureArtisan.getFeatureArtisan(product);
//        if (featureArtisan != null) {
//            if (count != -1) {
//                if (!removeItemFromInventory(featureArtisan, count)) {
//                    return new Result(false, "not enough products");
//                }
//            } else if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(featureArtisan) == 0) {
//                return new Result(false, "not enough products");
//            } else {
//
//                int money = 0;
//                if (count == -1) {
//                    money = featureArtisan.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(featureArtisan);
//                } else {
//                    money = featureArtisan.getBaseSellPrice() * count;
//                }
//                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
//                return new Result(false, "item sold successfully");
//            }
//        }
//        return new Result(false, "you can't sell this product");

    }

    static Result playerNotFound() {
        return new Result(false, "there is no player with this name");
    }

    private static boolean isNearShippingBin(Location location) {
        World world = App.getCurrentGame().getWorld();
        for (Direction direction : Direction.values()) {
            Location nearLocation = location.getLocationAt(direction);
            if (world.getTileAt(nearLocation).getTop().hasFeature(Feature.SELLING)) {
                return (!(world.getTileAt(location).getThingOnTile() instanceof Building building)
                        || world.getTileAt(nearLocation).getThingOnTile().equals(building))
                        && (!(world.getTileAt(nearLocation).getThingOnTile() instanceof Building));
            }
        }
        return false;
    }

}
