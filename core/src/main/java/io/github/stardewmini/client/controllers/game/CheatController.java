package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.Feature;
import io.github.stardewmini.common.model.enums.Weather;
import io.github.stardewmini.common.model.items.Item;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.*;
import io.github.stardewmini.server.app.App;
import io.github.stardewmini.server.controllers.game.AnimalController;
import io.github.stardewmini.server.controllers.game.CommonGameController;
import io.github.stardewmini.server.controllers.game.ToolsController;

public class CheatController {
    public static Result advanceTime(String string) {
        Message message = ClientGameController.createAdvanceTime(string);
        return ClientApp.sendRequest(message);
    }

    public static Result advanceDate(String string) {
        Message message = ClientGameController.createAdvanceDate(string);
        return ClientApp.sendRequest(message);
    }

    public static Result thunderStrike(String locationString) {
        Message message = ClientGameController.createThunderStrike(locationString);
        return ClientApp.sendRequest(message);
    }

    public static Result setWeather(String weatherType) {
        Weather weather = Weather.fromString(weatherType);
        App.getCurrentGame().getWorld().setTomorrowWeather(weather);
        return new Result(true, "You changed the future!");
    }

    public static Result setEnergy( String valueString) {
        Message message = ClientGameController.createSetEnergy(valueString);
        return ClientApp.sendRequest(message);
    }

    public static Result setUnlimitedEnergy(String requester) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.setUnlimitedEnergy(true);
        return new Result(true, "You suddenly feel unstoppable!");
    }

    public static Result setAnimalFriendship(String requester, String animalName, int amount) {
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "You don't have any animal named " + animalName);
        }

        animal.setFriendshipLevel(amount);
        return new Result(1,"Now " + animal + "'s friendship level is around " + animal.getFriendshipLevel());
    }

    public static Result addMoney(String requester, String string) {
        int money;
        try{
            money = Integer.parseInt(string);
        }catch (Exception e){
            return new Result(false,"only enter numbers");
        }
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.increaseMoney(money);
        return new Result(true, "You are richer than before!");
    }

//    public static Result addItem(String requester, String itemName, String countString) {
//        int count;
//        try{
//            count = Integer.parseInt(countString);
//        }catch (Exception e){
//            return new Result(false,"only enter numbers");
//        }
//        Player player = App.getCurrentGame().getPlayerByUsername(requester);
//        Item item = CommonGameController.findItem(itemName);
//        if(item == null) {
//            return new Result(-1, "Doesn't exist any item named " + itemName);
//        }
//        return ToolsController.addToBackPack(player.getBackpack(), item, count);
//    }

//    public static Result addBuilding(String requester, String buildingName, Location location) {
//        AnimalHousePrototype prototype = AnimalHousePrototype.getAnimalHousePrototype(buildingName);
//        int numberOfRows;
//        int numberOfColumns;
//        boolean isWell = buildingName.equals("Well");
//        boolean isShippingBin = buildingName.equals("Shipping Bin");
//        if (prototype == null) {
//            if (isWell) {
//                numberOfRows = 3;
//                numberOfColumns = 3;
//            } else if (isShippingBin) {
//                numberOfRows = 1;
//                numberOfColumns = 1;
//            } else {
//                return new Result(false, "ERROR: No building found.");
//            }
//        }
//        else {
//            numberOfRows = prototype.getNumberOfRows();
//            numberOfColumns = prototype.getNumberOfColumns();
//        }
//
//        Farm farm = App.getCurrentGame().getWorld().getFarmAt(location);
//        if (farm == null) {
//            return new Result(false, "You can only build this in a farm.");
//        }
//
//        for (int i = 0; i < numberOfRows; i++) {
//            for (int j = 0; j < numberOfColumns; j++) {
//                Tile tile = farm.getTileAt(location.add(new Location(i, j)));
//                if (tile == null || tile.getThingOnTile() != null) {
//                    return new Result(false, "You can't build this at that location");
//                }
//            }
//        }
//
//        Building building;
//        if (prototype != null) {
//            AnimalHouse animalHouse = new AnimalHouse(prototype, location);
//            App.getCurrentGame().getPlayerByUsername(requester).getFarm().getAnimalHouses().add(animalHouse);
//            building = animalHouse;
//        }
//        else {
//            building = new GenericWall(new Area(location, location.add(new Location(numberOfRows, numberOfColumns))),
//                    null);
//
//            // TODO texture
//        }
//
//        for (int i = 0; i < building.getNumberOfRows(); i++) {
//            for (int j = 0; j < building.getNumberOfColumns(); j++) {
//                Tile tile = farm.getTileAt(location.add(new Location(i, j)));
//                tile.setThingOnTile(building);
//                if (isWell) tile.addFeature(Feature.WATER);
//                if (isShippingBin) tile.addFeature(Feature.SELLING);
//            }
//        }
//
//        return new Result(true, buildingName + " built!");
//    }

    public static Result addAnimal(String requester, Animal animal, String name,Location location) {
        if (animal == null) {
            return new Result(false, "No such animal.");
        }

        boolean temp = false;
        for (AnimalHouse animalHouse : App.getCurrentGame().getPlayerByUsername(requester).getFarm().getAnimalHouses()) {
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

        Result result = io.github.stardewmini.server.controllers.game.AnimalController.moveAnimal(requester, animal,location);
        if(! result.success()){
            return result;
        }

        int i = 0;
        while (App.getCurrentGame().getPlayerByUsername(requester).getAnimals().get(name + i) != null) {
            i++;
        }
        name = name + i;
        animal.setName(name);
        animal.setOwner(App.getCurrentGame().getPlayerByUsername(requester));
        App.getCurrentGame().getPlayerByUsername(requester).getAnimals().put(name, animal);
        App.getCurrentGame().getDateTime().addDailyUpdateListener(animal);
        return new Result(true, "animal purchased");
    }

    public static Result addToFarm(String requester, Image image , String itemName, String name, int price, OrthographicCamera camera) {
        // todo change image to correct image
        image = new Image(GameAssetManager.getInstance().getStar());
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        if(player.getMoney() <= price){
            return new Result(false, "You can't have " + price +"coin");
        }
        player.decreaseMoney(price);

        Location location = App.getCurrentGame().getPlayerByUsername(requester).getFarm().getLocation();
        Main.getBatch().begin();
        if(Animal.getAnimal(itemName) != null) {
            Animal animal = Animal.getAnimal(itemName);
            while(! ((Gdx.input.isKeyPressed(Input.Keys.ENTER) && AnimalController.moveAnimal(requester, animal,location).success()))){
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
/*
            while(! ((Gdx.input.isKeyPressed(Input.Keys.ENTER) && addBuilding(requester, itemName,location).success()))){
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
*/
            return new Result(true, "building purchased");
        }
    }
}
