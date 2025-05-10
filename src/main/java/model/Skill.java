package model;

import model.alive.Player;
import model.enums.SkillType;
import model.items.recipes.Recipe;

public class Skill {
    private SkillType skillType;
    private int xp;
    private int level;

    public void addXP(int amount) {
        this.xp += amount;
        Player player = App.getCurrentGame().getCurrentPlayer();
        while (this.level != 4 && this.xp > 100 * level + 50) {
            for(String recipeName : skillType.getCraftingRecipesRelease()[level]){
                player.getLearnedCraftingRecipes().add(Recipe.craftRecipes.get(recipeName));
            }
            this.level++;
        }
    }
}
