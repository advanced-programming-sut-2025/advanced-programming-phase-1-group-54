package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.view.*;
import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.enums.ProduceQuality;
import io.github.stardewmini.common.model.items.*;
import io.github.stardewmini.common.model.items.crafting.FeatureArtisan;
import io.github.stardewmini.common.model.items.crafting.Produce;
import io.github.stardewmini.common.model.items.crafting.ProducerArtisan;
import io.github.stardewmini.common.model.items.plants.Crop;
import io.github.stardewmini.common.model.items.plants.Fruit;
import io.github.stardewmini.common.model.items.plants.Seed;
import io.github.stardewmini.common.model.items.tools.BackPack;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.*;

public class CommonGameController {
    public static void draw(SpriteBatch batch, Stage stage, OrthographicCamera camera, Window[] windows) {
        MapController.draw(batch,stage,camera);
        PlayerController.draw(batch);
        AnimalController.draw(batch);
        NpcController.draw(batch,windows);
    }

    public static void update(float delta, OrthographicCamera camera) {
        PlayerController.update(delta, camera);
        AnimalController.update(delta);
        NpcController.update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Main.getInstance().setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            Main.getInstance().setScreen(new CookingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
            Main.getInstance().setScreen(new CraftingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.V)){
            Main.getInstance().setScreen(new CheatMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            Main.getInstance().setScreen(new PlantingMenu(GameAssetManager.getInstance().getSkin()));
        }
        // TODO update game each frame.
    }

    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera,Window[] windows) {
        MapController.mouseClick(screenX, screenY, camera, windows);
        ToolsController.mouseClick(screenX, screenY, camera);
    }

    public static String updateDateTime(SpriteBatch batch){
        StringBuilder output = new StringBuilder();
        DateTime dateTime = App.getCurrentGame().getDateTime();
        output.append("Hour : ").append(dateTime.getHour()).append("\nday : ").append(dateTime.getDay()).
            append("\nyear : ").append(dateTime.getYear()).append("\nweekDay : ").append(dateTime.getWeekDay()).
            append("\nseason : ").append(dateTime.getSeason()).append("\nweather : ").
            append(App.getCurrentGame().getCurrentWeather()).append("\nEnergy : ").
            append(App.getCurrentPlayer().getEnergy());

        if(dateTime.getHour() >= 18){
            batch.setColor(0.7f, 0.7f, 0.7f, 1);
        }
        else{
            batch.setColor(1, 1, 1, 1);
        }

        return output.toString();
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

    static int numberOfItemInBackPack(String requester, String ItemName) {
        Player player = io.github.stardewmini.server.app.App.getCurrentGame().getPlayerByUsername(requester);
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

    static void removeItemFromBackPack(String requester, String ItemName, int number) {
        Player player = io.github.stardewmini.server.app.App.getCurrentGame().getPlayerByUsername(requester);
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
                removeItemFromInventory(requester, produce, amount);
                number -= amount;
                removeItemFromInventory(requester, Produce.getProduce("Large " + ItemName), number);
            } else {
                removeItemFromInventory(requester, produce, number);
            }
        } else if (ItemName.equals("Oil")) {
            Produce produce = Produce.getProduce("Corn " + ItemName);
            amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce, 0);
            amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce, 0);
            if (number > amount) {
                removeItemFromInventory(requester, produce, amount);
                number -= amount;
                produce = Produce.getProduce("Sunflower Seed " + ItemName);
                amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce, 0);
                amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce, 0);
                if (number > amount) {
                    removeItemFromInventory(requester, produce, amount);
                    number -= amount;
                    removeItemFromInventory(requester, Produce.getProduce("Sunflower " + ItemName), number);
                } else {
                    removeItemFromInventory(requester, produce, number);
                }
            } else {
                removeItemFromInventory(requester, produce, number);
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

    static boolean removeItemFromInventory(String requester, Item item, int number) {

        Player player = io.github.stardewmini.server.app.App.getCurrentGame().getPlayerByUsername(requester);
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

    static Result passOut(String requester) {
        io.github.stardewmini.server.app.App.getCurrentGame().getPlayerByUsername(requester).setEnergy(0);
        return new Result(true, "you passed out!\n");
    }

    static boolean deleteThingOnTile(String requester, Tile tile, Farm farm) {
        Player player = io.github.stardewmini.server.app.App.getCurrentGame().getPlayerByUsername(requester);

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
            io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeHourUpdateListener(producerArtisan);
        } else if (tile.getThingOnTile() instanceof Crop crop && crop.getGiantDirection() != null) {
            tile.setThingOnTile(null);
            io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeDailyUpdateListener(crop);
            for (int i = 0; i < 3; i++) {
                tile = io.github.stardewmini.server.app.App.getCurrentGame().getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection()));
                crop = (Crop) tile.getThingOnTile();
                tile.setThingOnTile(null);
                io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeDailyUpdateListener(crop);
            }
        } else if (tile.getThingOnTile() instanceof Building) {
            return deleteThingOnTile(requester, tile.getTop(), farm);
        } else if (!(tile.getThingOnTile() instanceof Animal)) {
            if (tile.getThingOnTile() instanceof DailyUpdate dailyUpdate) {
                io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeDailyUpdateListener(dailyUpdate);
            }
            if (tile.getThingOnTile() instanceof HourUpdate hourUpdate) {
                io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeHourUpdateListener(hourUpdate);
            }
            if (tile.getThingOnTile() instanceof HourCheck hourCheck) {
                io.github.stardewmini.server.app.App.getCurrentGame().getDateTime().removeHourCheckListener(hourCheck);
            }
            tile.setThingOnTile(null);
        } else {
            return false;
        }
        return true;
    }
}
