package controller.Game;

import model.App;
import model.Game;
import model.enums.Weather;
import model.enums.commands.Command;
import model.items.Item;
import model.lives.Animal;
import model.lives.Player;
import model.map.Farm;
import model.map.Location;
import model.Result;

public class CheatController {
    public static Result advanceTime(int x) {
        Game game = App.getCurrentGame();
        for (int i = 1; i <= x; i++)
            game.nextHourUpdate();
        return new Result(true, "it's now " + game.getDateTime().toString());
    }

    public static Result advanceDate(int x) {
        Game game = App.getCurrentGame();
        for (int i = 1; i <= x; i++)
            game.nextDayUpdate();
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
        return ToolsController.addToBackPack(player.getBackpack(), item, count);
    }
}
