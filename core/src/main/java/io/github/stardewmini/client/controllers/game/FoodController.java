package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.client.view.GameScreen;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.items.recipes.Recipe;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Cabin;
import io.github.stardewmini.common.model.map.Tile;

import java.util.ArrayList;

public class FoodController {
    public static Result showFoodRecipes(Window window){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());

        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        ArrayList<Recipe> foodRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes();
        int inRow = 0;
        for(Recipe recipe: Recipe.foodRecipes.values()){
            Image image = new Image(gameAssetManager.getRecipe(recipe.getName()));
            if(foodRecipes.contains(recipe)){
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        String artisanName = recipe.getName();
                        Message message = ClientGameController.createCooking(artisanName.substring(0,artisanName.length() - 7));
                        Result result = ClientApp.sendRequest(message);
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(gameAssetManager.getSkin(),result.message()));
                    }
                });
            }
            else{
                image.setColor(Color.GRAY);
            }

            window.add(image).expand().pad(10);
            inRow++;
            if(inRow == 10){
                window.row();
                inRow = 0;
            }
        }
        return new Result(1,"");
    }

}
