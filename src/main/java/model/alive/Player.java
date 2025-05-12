package model.alive;

import model.Refrigerator;
import model.Skill;
import model.User;
import model.enums.SkillType;
import model.items.crafting.Artisan;
import model.items.crafting.ProducerArtisan;
import model.items.plants.Plant;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.items.tools.Tool;
import model.map.Location;
import model.map.Tile;

import java.util.ArrayList;
import java.util.HashMap;



public class Player extends Human {
    private static final int MAXIMUM_ENERGY = 200;

    private User controllingUser;
    private int energy;
    private int money;
    private BackPack backpack = new BackPack();
    private Refrigerator refrigerator = new Refrigerator();
    private Location currentLocation;
    private final HashMap<Plant,Tile> Plants = new HashMap<>();
    
    private final ArrayList<Recipe> learnedFoodRecipes = new ArrayList<>(){{
        add(Recipe.foodRecipes.get("Fried Egg Recipe"));
    }};

    private final ArrayList<Recipe> learnedCraftingRecipes = new ArrayList<>(){{
//        add(Recipe.craftRecipes.get(""));
    }};

    private final ArrayList<ProducerArtisan> placedArtisans = new ArrayList<>();

    private final HashMap<String,Animal> animals = new HashMap<>();
    private final HashMap<SkillType,Skill> skills;
    private Tool equippedTool;

    public Player(User controllingUser) {
        this.controllingUser = controllingUser;
        this.skills = new HashMap<>();
        skills.put(SkillType.FARMING,new Skill(SkillType.FARMING));
        skills.put(SkillType.FORAGING,new Skill(SkillType.FORAGING));
        skills.put(SkillType.MINING,new Skill(SkillType.MINING));
        skills.put(SkillType.FISHING,new Skill(SkillType.FISHING));
        // TODO
    }

    public HashMap<SkillType, Skill> getSkills() {
        return skills;
    }

    public User getControllingUser() {
        return controllingUser;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMoney() {
        return money;
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

    public ArrayList<ProducerArtisan> getPlacedArtisans() {
        return placedArtisans;
    }

    public HashMap<String, Animal> getAnimals() {
        return animals;
    }



    public void setControllingUser(User controllingUser) {
        this.controllingUser = controllingUser;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public boolean decreaseMoney(int money) {
        if(this.money < money) {
            return false;
        }
        this.money -= money;
        return true;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
