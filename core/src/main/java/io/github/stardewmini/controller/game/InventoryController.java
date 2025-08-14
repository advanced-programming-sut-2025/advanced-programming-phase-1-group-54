package io.github.stardewmini.controller.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.enums.SkillType;
import io.github.stardewmini.model.items.Item;
import io.github.stardewmini.model.items.tools.Tool;
import io.github.stardewmini.model.lives.NPC;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.lives.Skill;
import io.github.stardewmini.view.GameScreen;

import java.util.Map;

public class InventoryController {

//    public static String skills(){
//        StringBuilder output  = new StringBuilder();
//
//        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
//        for(SkillType type : SkillType.values()){
//            Skill skill = currentPlayer.getSkill(type);
//            output.append(type.name()).append(" Level : ").append(skill.getLevel())
//                .append(" XP needed for next Level : ").append(skill.getXpNeededForLevelUp()).append("\n");
//        }
//        return output.toString();
//    }

    public static String showSkill(int index){
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Skill skill = currentPlayer.getSkill(SkillType.values()[index]);
        return skill.getSkillType() + " Level : " + skill.getLevel() + " XP needed for next Level : " + skill.getXpNeededForLevelUp();
    }

    public static Table showInventory(){
        Table output = new Table();
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Map<Item,Integer> inventory = currentPlayer.getBackpack().getCopy();
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        int i = 0;
        for(Item item : inventory.keySet()){
            Table table = new Table();
            Texture texture = gameAssetManager.getItem(item.getName());
            Image image = new Image(texture);
            table.add(image).row();
            table.add(new Label(item.getName() + " " + inventory.get(item).toString(),gameAssetManager.getSkin()));
            output.add(table).pad(10);
            i++;
            if(i == 4){
                output.row();
            }
        }
        return output;
    }

    public static Table showTolls(Window window){
        Table output = new Table();
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        int i = 0;
        for(Tool tool : currentPlayer.getTools()){
            if(tool != null){
                System.out.println(tool.getToolType());
                Table table = new Table();
                Texture texture = gameAssetManager.getTool(tool.getToolType().toString(),tool.getToolLevel().toString());
                Image image = new Image(texture);
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        Result result = ToolsController.equipTool(tool.getToolType().toString());
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(gameAssetManager.getSkin(),result.message()));
                    }
                });
                table.add(image).row();
                table.add(new Label(tool.getToolLevel().toString() + " " + tool.getToolType().toString()
                    ,gameAssetManager.getSkin()));
                output.add(table).pad(10);
                i++;
                if(i == 4){
                    output.row();
                }
            }
        }
        return output;
    }
}
