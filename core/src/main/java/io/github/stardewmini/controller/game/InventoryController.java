package io.github.stardewmini.controller.game;

import io.github.stardewmini.model.App;
import io.github.stardewmini.model.enums.SkillType;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.lives.Skill;

public class InventoryController {

    public static String skills(){
        StringBuilder output  = new StringBuilder();

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for(SkillType type : SkillType.values()){
            Skill skill = currentPlayer.getSkill(type);
            output.append(type.name()).append(" Level : ").append(skill.getLevel())
                .append(" XP needed for next Level : ").append(skill.getXpNeededForLevelUp()).append("\n");
        }
        return output.toString();
    }



}
