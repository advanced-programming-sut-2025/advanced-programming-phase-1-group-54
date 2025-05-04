package model.alive;

import model.Refrigerator;
import model.Skill;
import model.User;
import model.enums.SkillType;
import model.items.plants.Plant;
import model.items.plants.Tree;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.items.tools.Tool;
import model.map.Location;
import model.map.Tile;

import java.util.ArrayList;
import java.util.HashMap;


public class Player extends Character {
    private final int MAXIMUM_ENERGY = 100;

    private User controllingUser;
    private int energy;
    private BackPack backpack = new BackPack();
    private Refrigerator refrigerator = new Refrigerator();
    private Location currentLocation;

    private HashMap<Plant,Tile> Plants;
    
    private ArrayList<Recipe> learnedFoodRecipes;
    private ArrayList<Recipe> learnedCraftingRecipes;

    public Player(User controllingUser) {
        this.controllingUser = controllingUser;
        // TODO
    }

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

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public HashMap<Plant, Tile> getPlants() {
        return Plants;
    }

    public ArrayList<Recipe> getLearnedFoodRecipes() {
        return learnedFoodRecipes;
    }

    public ArrayList<Recipe> getLearnedCraftingRecipes() {
        return learnedCraftingRecipes;
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

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
