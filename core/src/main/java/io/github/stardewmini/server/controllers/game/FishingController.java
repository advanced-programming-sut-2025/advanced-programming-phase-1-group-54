package io.github.stardewmini.server.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.common.model.FishingGame;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.FishingPoleType;
import io.github.stardewmini.common.model.enums.ProduceQuality;
import io.github.stardewmini.common.model.enums.SkillType;
import io.github.stardewmini.common.model.items.Fish;
import io.github.stardewmini.common.model.items.tools.FishingPole;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.lives.Skill;
import io.github.stardewmini.common.model.map.Farm;
import io.github.stardewmini.common.model.map.GenericWall;
import io.github.stardewmini.client.view.FishingMenu;

public class FishingController {

    public static Result fishing(String fishingPoleName, boolean perfect) {
        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        Farm farm = GameApp.getCurrentGame().getWorld().getFarmAt(player.getCurrentLocation());

        if (farm == null) {
            return new Result(1, "You aren't in any farm");
        }

        boolean isNearLake = false;
        for (GenericWall lake : farm.getLakes()) {
            if (MapController.isNear(player.getCurrentLocation(), lake)) {
                isNearLake = true;
            }
        }

        if (!isNearLake) {
            return new Result(false, "you must be next to a lake to start fishing");
        }



//        if (fishingPole == null) {
//            return new Result(false, "you don't have " + fishingPoleName + " fishing pole");
//        }
        return null;
    }

    public static void startFishing(String fishingPoleName,boolean perfect) {
        Player player = GameApp.getCurrentGame().getCurrentPlayer();

        FishingPoleType fishingPoleType = FishingPoleType.fromString(fishingPoleName);
        FishingPole fishingPole = player.getFishingPole(fishingPoleType);

        int energyNeeded = fishingPole.getEnergyNeededPerUse();
        player.decreaseEnergy(energyNeeded, SkillType.FISHING);

        int skillLevel = player.getSkill(SkillType.FISHING).getLevel();
        double weatherFactor = GameApp.getCurrentGame().getCurrentWeather().getFishingFactor();

        double poleFactor = fishingPole.getPoleFactor();

        ProduceQuality quality = ProduceQuality.
            giveQuality(Math.random() * (skillLevel + 2) * poleFactor / (7 - weatherFactor));

        Fish fish;
        if (fishingPoleType == FishingPoleType.TRAINING) {
            fish = Fish.getCheapestSeasonFish(GameApp.getCurrentGame().getDateTime().getSeason());
        } else {
            fish = Fish.getSeasonFish(GameApp.getCurrentGame().getDateTime().getSeason(),
                skillLevel == Skill.getMaxSkillLevel());
        }
        fish.setQuality(quality);

        FishingGame fishingGame = new FishingGame(fish,FishingGame.random.nextInt(0,5));
        GameApp.getCurrentGame().getFishingGames().put(player, fishingGame);

        Main.getInstance().setScreen(new FishingMenu(GameAssetManager.getInstance().getSkin(),
            "Movement level : " + fishingGame.getFishType(),fish.getQuality() + " " +  fish.getName()));

    }

    public static Result winFishing(boolean perfect) {
        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        Fish fish = GameApp.getCurrentGame().getFishingGames().get(player).getFish();
        if(perfect) {
            if(fish.getQuality() == ProduceQuality.SILVER){
                fish.setQuality(ProduceQuality.GOLD);
            }
            else if(fish.getQuality() == ProduceQuality.GOLD){
                fish.setQuality(ProduceQuality.IRIDIUM);
            }
        }
        return ToolsController.addToBackPack(player.getBackpack(), fish, 1);
    }

    public static void handle(ShapeRenderer shapeRenderer, ShapeRenderer mapShapeRenderer, ProgressBar bar) {
        FishingGame game = GameApp.getCurrentGame().getFishingGames().get(GameApp.getCurrentGame().getCurrentPlayer());
        Rectangle greenPart = game.getGreenPart();
        Rectangle fish = game.getFishRectangle();
        Rectangle map = game.getMap();

        if(greenPart.y + greenPart.height >= FishingGame.max){
            game.setGreenPartDirection(false);
        }
        else if(greenPart.y <= FishingGame.min){
            game.setGreenPartDirection(true);
        }

        int rand;
        if(game.getFishType() == 0){
            rand = FishingGame.random.nextInt(0, 3);
            if(rand == 0){
                fish.y = fish.y + 3;
            }
            else if(rand != 1){
                fish.y = fish.y - 3;
            }

        }
        else if(game.getFishType() == 1){
            rand = FishingGame.random.nextInt(0, 4);
            if(rand == 0){
                fish.y = fish.y + 3;
            }
            else if(rand == 1){
                fish.y = fish.y - 3;
            }
            else if(rand == 2){
                fish.y = fish.y + 3 * game.getLastMoveDirection();
            }

        }
        else if(game.getFishType() == 2){
            rand = FishingGame.random.nextInt(0, 3);
            if(rand == 0){
                fish.y = fish.y + 3;
            }
            else if(rand != 1){
                fish.y = fish.y - 5;
            }

        }
        else if(game.getFishType() == 3){
            rand = FishingGame.random.nextInt(0, 3);
            if(rand == 0){
                fish.y = fish.y + 5;
            }
            else if(rand != 1){
                fish.y = fish.y - 3;
            }

        }
        else if(game.getFishType() == 4){
            rand = FishingGame.random.nextInt(0, 3);
            if(rand == 0){
                fish.y = fish.y + 5;
            }
            else if(rand != 1){
                fish.y = fish.y - 5;
            }
        }

        fish.y = Math.max(fish.y, FishingGame.min);
        fish.y = Math.min(fish.y, FishingGame.max - fish.height);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(greenPart.x, greenPart.y, greenPart.width, greenPart.height);
        shapeRenderer.end();

        mapShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mapShapeRenderer.setColor(Color.RED);
        mapShapeRenderer.rect(map.x, map.y, map.width, map.height);
        mapShapeRenderer.end();

        Main.getBatch().begin();
        Main.getBatch().draw(GameAssetManager.getInstance().getFishes("Salmon"),fish.x,fish.y,
            fish.width,fish.height);
        if(true){
            Main.getBatch().draw(GameAssetManager.getInstance().getStar(), fish.x,fish.y,
                fish.width/2, fish.height/2);
        }
        Main.getBatch().end();

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && greenPart.y + greenPart.height < FishingGame.max){
            greenPart.y++;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && greenPart.y > FishingGame.min){
            greenPart.y--;
        }

        if(fish.overlaps(greenPart)){
            bar.setValue(bar.getValue() + 1);
        }
        else{
            bar.setValue(bar.getValue() - 1);
            game.setPerfect(false);
        }

        if(bar.getMaxValue() == bar.getValue()){
            // todo  write fishingPoleName
            winFishing(game.isPerfect());
            // todo back to game
        }

        if(bar.getMinValue() == bar.getValue()){
            // todo sout you have lost && back to game
        }

    }

}
