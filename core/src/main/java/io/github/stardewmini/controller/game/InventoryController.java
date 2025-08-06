package io.github.stardewmini.controller.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.SkillType;
import io.github.stardewmini.model.items.Item;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.lives.Skill;

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
            table.add(new Image(gameAssetManager.getItem(item.getName()))).row();
            table.add(new Label(inventory.get(item).toString(),gameAssetManager.getSkin()));
            output.add(table).pad(10);
            i++;
            if(i == 4){
                output.row();
            }
        }
        return output;
    }

}
