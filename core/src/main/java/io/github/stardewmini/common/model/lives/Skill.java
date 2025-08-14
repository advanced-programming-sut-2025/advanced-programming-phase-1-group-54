package io.github.stardewmini.common.model.lives;

import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.common.model.enums.SkillType;
import io.github.stardewmini.common.model.items.recipes.Recipe;

public class Skill {

    private final static int MAX_SKILL_LEVEL = 4;

    public static int getMaxSkillLevel() {
        return MAX_SKILL_LEVEL;
    }

    private final SkillType skillType;
    private int xp;
    private int level;

    public Skill(SkillType skillType) {
        this.skillType = skillType;
    }

    public int getLevel() {
        return level;
    }

    public int getXpNeededForLevelUp() {
        return 100 * (level + 1) + 50;
    }

    public void addXP(int amount) {
        this.xp += amount;
        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        while (this.level < 4 && this.xp >= getXpNeededForLevelUp()) {
            xp -= getXpNeededForLevelUp();
            this.level++;
            for(String recipeName : skillType.getCraftingRecipesRelease()[level]){
                player.getLearnedCraftingRecipes().add(Recipe.craftRecipes.get(recipeName));
            }
        }

        if (this.level == 4) {
            this.xp = 0;
        }
    }

    public SkillType getSkillType() {
        return skillType;
    }
}
