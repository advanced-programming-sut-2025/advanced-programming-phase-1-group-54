package model.alive;

import model.Skill;
import model.User;
import model.enums.SkillType;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.items.tools.Tool;
import model.map.Tile;

import java.util.ArrayList;


public class Player extends Character {
    private final int MAXIMUM_ENERGY = 100;

    private User controllingUser;
    private int energy;
    private BackPack backpack;
    private Tile currentTile;
    
    private ArrayList<Recipe> learnedRecipes;
    
    

    private final Skill[] skills = new Skill[SkillType.values().length];

    private Tool equippedTool;

    public Skill getSkill(SkillType skillType) {
        return skills[skillType.ordinal()];
    }

    public User getControllingUser() {
        return controllingUser;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isGhash() {
        return energy <= 0;
    }

    public BackPack getBackpack() {
        return backpack;
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setControllingUser(User controllingUser) {
        this.controllingUser = controllingUser;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void decreaseEnergy(int amount) {
        energy -= amount;
    }

    public void increaseEnergy(int amount) {
        energy += amount;
    }

    public void setBackpack(BackPack backpack) {
        this.backpack = backpack;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }

    public void addSkillXP(SkillType skillType, int amount) {
        skills[SkillType.FARMING.ordinal()].addXP(amount);
    }
}
