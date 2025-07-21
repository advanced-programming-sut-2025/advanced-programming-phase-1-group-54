package controller.Game;

import model.App;
import model.Game;
import model.Result;
import model.enums.Feature;
import model.enums.Symbol;
import model.enums.Weather;
import model.items.Item;
import model.lives.Animal;
import model.lives.Player;
import model.map.*;

public class CheatController {
    public static Result advanceTime(int x) {
        Game game = App.getCurrentGame();
        game.getDateTime().increaseHour(x);
        return new Result(true, "it's now " + game.getDateTime().toString());
    }

    public static Result advanceDate(int x) {
        Game game = App.getCurrentGame();
        game.getDateTime().increaseDay(x);
        return new Result(true, "it's now " + game.getDateTime().toString());
    }

    public static Result thunderStrike(Location location) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
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

    public static Result setWeather(String weatherType) {
        Weather weather = Weather.fromString(weatherType);
        App.getCurrentGame().getWorld().setTomorrowWeather(weather);
        return new Result(true, "You changed the future!");
    }

    public static Result setEnergy(int value) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.setUnlimitedEnergy(false);
        player.setEnergy(value);
        return new Result(true, "You suddenly feel weird, as if you're energy has changed!");
    }

    public static Result setUnlimitedEnergy() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.setUnlimitedEnergy(true);
        return new Result(true, "You suddenly feel unstoppable!");
    }

    public static Result setAnimalFriendship(String animalName, int amount) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "You don't have any animal named " + animalName);
        }

        animal.setFriendshipLevel(amount);
        return new Result(1,"Now " + animal + "'s friendship level is around " + animal.getFriendshipLevel());
    }

    public static Result addMoney(int money) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.increaseMoney(money);
        return new Result(true, "You are richer than before!");
    }

    public static Result addItem(String itemName, int count) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = CommonGameController.findItem(itemName);
        if(item == null) {
            return new Result(-1, "Doesn't exist any item named " + itemName);
        }
        return ToolsController.addToBackPack(player.getBackpack(), item, count);
    }

    public static Result addBuilding(String buildingName, Location location) {
        AnimalHousePrototype prototype = AnimalHousePrototype.getAnimalHousePrototype(buildingName);
        int numberOfRows;
        int numberOfColumns;
        boolean isWell = buildingName.equals("Well");
        boolean isShippingBin = buildingName.equals("Shipping Bin");
        if (prototype == null) {
            if (isWell) {
                numberOfRows = 3;
                numberOfColumns = 3;
            } else if (isShippingBin) {
                numberOfRows = 1;
                numberOfColumns = 1;
            } else {
                return new Result(false, "ERROR: No building found.");
            }
        }
        else {
            numberOfRows = prototype.getNumberOfRows();
            numberOfColumns = prototype.getNumberOfColumns();
        }

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(location);
        if (farm == null) {
            return new Result(false, "You can only build this in a farm.");
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                Tile tile = farm.getTileAt(location.add(new Location(i, j)));
                if (tile == null || tile.getThingOnTile() != null) {
                    return new Result(false, "You can't build this at that location");
                }
            }
        }

        Building building;
        if (prototype != null) {
            building = new AnimalHouse(prototype, location);
        }
        else {
            building = new GenericWall(new Area(location, location.add(new Location(numberOfRows, numberOfColumns))),
                    (isWell? Symbol.WELL : Symbol.SELLING));
        }

        for (int i = 0; i < building.getNumberOfRows(); i++) {
            for (int j = 0; j < building.getNumberOfColumns(); j++) {
                Tile tile = farm.getTileAt(location.add(new Location(i, j)));
                tile.setThingOnTile(building);
                if (isWell) tile.addFeature(Feature.WATER);
                if (isShippingBin) tile.addFeature(Feature.SELLING);
            }
        }

        return new Result(true, buildingName + " built!");
    }
}
