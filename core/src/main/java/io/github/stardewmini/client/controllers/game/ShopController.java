package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.client.app.GameApp;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.AnimalHousePrototype;
import io.github.stardewmini.common.model.map.Location;
//import io.github.stardewmini.client.controllers.game.CheatController;

public class ShopController {
    public static void showItems(Table scrollTable, Table table, TextButton buyButton, TextField number,Label itemLabel
    ,Label priceLabel,TextField nameField,TextField locationField) {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        int inRow = 0;
        int price;
        for(String animal : Animal.getAnimalsList()){
            price = Animal.getAnimal(animal).getSellPrice();
            Image image = new Image(gameAssetManager.getProducedAnimal(animal));
            if(animal.equals("Pig")){
                image.setColor(1,1,1,0.4f);
            }
            else {
                int finalPrice = price;
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        table.clear();
                        image.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f);
                        table.add(image).size(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f).row();
                        itemLabel.setText(animal);
                        table.add(itemLabel).expand().pad(10);
                        table.row();
                        priceLabel.setText(finalPrice + "coin");
                        table.add(priceLabel).expand().pad(10);
                        table.row();
                        table.add(number).expand().pad(10);
                        table.row();
                        table.add(nameField).expand().pad(10);
                        table.row();
                        table.add(locationField).expand().pad(10);
                        table.row();
                        table.add(buyButton).expand().pad(10);
                    }
                });
            }
            Label label = new Label(animal + " : " + price + "Coin", gameAssetManager.getSkin());
            Table itemTable = new Table();
            image.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f);
            itemTable.add(image).size(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f).row();
            label.setFontScale(0.5f);
            itemTable.add(label);
            scrollTable.add(itemTable).expand().pad(30);
            inRow++;
            if(inRow == 4){
                scrollTable.row();
                inRow = 0;
            }
        }
        scrollTable.row();
        inRow = 0;
        for(String animalHouse : AnimalHousePrototype.getAnimalHouseList()){
            price = AnimalHousePrototype.getAnimalHousePrototype(animalHouse).getSize() *  100;
            Image image = new Image(gameAssetManager.getBuilding(animalHouse));
            if(animalHouse.equals("Big Barn")){
                image.setColor(1,1,1,0.4f);
            }
            else {
                int finalPrice1 = price;
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        table.clear();
                        image.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f);
                        table.add(image).size(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f).row();
                        itemLabel.setText(animalHouse);
                        table.add(itemLabel).expand().pad(10);
                        table.row();
                        priceLabel.setText(finalPrice1 + "coin");
                        table.add(priceLabel).expand().pad(10);
                        table.row();
                        table.add(number).expand().pad(10);
                        table.row();
                        table.add(nameField).expand().pad(10);
                        table.row();
                        table.add(locationField).expand().pad(10);
                        table.row();
                        table.add(buyButton).expand().pad(10);
                    }
                });
            }
            Label label = new Label(animalHouse + " : " + price + "Coin", gameAssetManager.getSkin());
            Table itemTable = new Table();
            image.setSize(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f);
            itemTable.add(image).size(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/8f).row();
            label.setFontScale(0.5f);
            itemTable.add(label);
            scrollTable.add(itemTable).expand().pad(30);
            inRow++;
            if(inRow == 4){
                scrollTable.row();
                inRow = 0;
            }
        }
    }

    public static Result buy(String itemName, String name, int price,String locationString) {
        int x;
        int y;
        String[] locationParts = locationString.split(",");
        try{
            x = Integer.parseInt(locationParts[0]);
            y = Integer.parseInt(locationParts[1]);
        } catch (Exception e) {
            return new Result(false, "only enter location in X,Y format");
        }
        Location location = new Location(x,y);
        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        if(player.getMoney() < price){
            return new Result(false, "You don't have " + price +"coin");
        }

        Result result;
        if(Animal.getAnimal(itemName) != null) {
            // TODO
            Animal animal = Animal.getAnimal(itemName);
//            result = CheatController.addAnimal(animal,name,location);
        }
        else {
//            result = CheatController.addBuilding(itemName,location);
        }

//        if(result.success()){
//            player.decreaseMoney(price);
//        }
//        return  result;
        return null;
    }
}
