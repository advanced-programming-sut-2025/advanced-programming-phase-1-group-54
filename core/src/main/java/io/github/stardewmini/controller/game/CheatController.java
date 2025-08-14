package io.github.stardewmini.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Game;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.enums.Feature;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.enums.Weather;
import io.github.stardewmini.model.items.Item;
import io.github.stardewmini.model.lives.Animal;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.map.*;

public class CheatController {
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

    public static Result thunderStrike(String locationString) {
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

    public static Result setEnergy(String valueString) {
        int value;
        try {
            value = Integer.parseInt(valueString);
        } catch (Exception e){
            return new Result(false,"enter only numbers");
        }
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

    public static Result addMoney(String string) {
        int money;
        try{
            money = Integer.parseInt(string);
        }catch (Exception e){
            return new Result(false,"only enter numbers");
        }
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.increaseMoney(money);
        return new Result(true, "You are richer than before!");
    }

    public static Result addItem(String itemName, String countString) {
        int count;
        try{
            count = Integer.parseInt(countString);
        }catch (Exception e){
            return new Result(false,"only enter numbers");
        }
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
            AnimalHouse animalHouse = new AnimalHouse(prototype, location);
            App.getCurrentGame().getCurrentPlayer().getFarm().getAnimalHouses().add(animalHouse);
            building = animalHouse;
        }
        else {
            building = new GenericWall(new Area(location, location.add(new Location(numberOfRows, numberOfColumns))),
                    null);

            // TODO texture
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

    public static Result addAnimal(Animal animal, String name,Location location) {
        if (animal == null) {
            return new Result(false, "No such animal.");
        }

        boolean temp = false;
        for (AnimalHouse animalHouse : App.getCurrentGame().getCurrentPlayer().getFarm().getAnimalHouses()) {
            for (String acceptedAnimalName : animalHouse.getAnimals()) {
                if (acceptedAnimalName.equals(animal.getAnimalName())) {
                    temp = true;
                    break;
                }
            }
        }
        if (!temp) {
            return new Result(false, "building required");
        }

        Result result = AnimalController.moveAnimal(animal,location);
        if(! result.success()){
            return result;
        }

        int i = 0;
        while (App.getCurrentGame().getCurrentPlayer().getAnimals().get(name + i) != null) {
            i++;
        }
        name = name + i;
        animal.setName(name);
        animal.setOwner(App.getCurrentGame().getCurrentPlayer());
        App.getCurrentGame().getCurrentPlayer().getAnimals().put(name, animal);
        App.getCurrentGame().getDateTime().addDailyUpdateListener(animal);
        return new Result(true, "animal purchased");
    }

    public static Result addToFarm(Image image , String itemName, String name, int price, OrthographicCamera camera) {
        // todo change image to correct image
        image = new Image(GameAssetManager.getInstance().getStar());
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.getMoney() <= price){
            return new Result(false, "You can't have " + price +"coin");
        }
        player.decreaseMoney(price);

        Location location = App.getCurrentGame().getCurrentPlayer().getFarm().getLocation();
        Main.getBatch().begin();
        if(Animal.getAnimal(itemName) != null) {
            Animal animal = Animal.getAnimal(itemName);
            while(! ((Gdx.input.isKeyPressed(Input.Keys.ENTER) && AnimalController.moveAnimal(animal,location).success()))){
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    location = location.add(new Location(0, 1));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                    location = location.add(new Location(0, -1));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    location = location.add(new Location(1, 0));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    location = location.add(new Location(-1, 0));
                }
                if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                    return new Result(false, "you gave up");
                }
                image.setPosition(location.row(), location.column());
                image.draw(Main.getBatch(),0.5f);
                camera.position.set(location.row() * Tile.getSize(),location.column() * Tile.getSize(),0);
                // todo add image to tile sprite
            }
//            addAnimal(animal,name);
            return new Result(true, "animal purchased");
        }
        else {
            while(! ((Gdx.input.isKeyPressed(Input.Keys.ENTER) && addBuilding(itemName,location).success()))){
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    location = location.add(new Location(0, 1));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                    location = location.add(new Location(0, -1));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    location = location.add(new Location(1, 0));
                }
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    location = location.add(new Location(-1, 0));
                }
                if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                    return new Result(false, "you gave up");
                }
                image.setPosition(location.row(), location.column());
                image.draw(Main.getBatch(),0.5f);
                camera.position.set(location.row() * Tile.getSize(),location.column() * Tile.getSize(),0);
            }
            return new Result(true, "building purchased");
        }
    }
}
