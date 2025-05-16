package model.lives;

import model.App;
import model.enums.SkillType;
import model.items.recipes.Recipe;

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
        Player player = App.getCurrentGame().getCurrentPlayer();
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
}
