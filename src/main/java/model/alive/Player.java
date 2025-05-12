package model.alive;

import model.DailyUpdate;
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
import model.items.tools.TrashCan;
import model.map.Location;
import model.map.Tile;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;
import java.util.HashMap;



public class Player extends Human implements DailyUpdate {
    private static final int MAXIMUM_ENERGY = 200;

    public static int getMaximumEnergy() {
        return MAXIMUM_ENERGY;
    }

    private int money;

    private User controllingUser;

    private int energy;
    private boolean unlimitedEnergy;

    private BackPack backpack = new BackPack();
    private TrashCan trashCan = new TrashCan();


    private Refrigerator refrigerator = new Refrigerator();
    private Location currentLocation;
    private ArrayList<Gift> recivedGifts;
    private final HashMap<Plant,Tile> Plants = new HashMap<>();

    private final ArrayList<Recipe> learnedFoodRecipes = new ArrayList<>(){{
        add(Recipe.foodRecipes.get("Fried Egg Recipe"));
    }};


    private final ArrayList<Recipe> learnedCraftingRecipes = new ArrayList<>(){{
        add(Recipe.craftRecipes.get("Furnace Recipe"));
        add(Recipe.craftRecipes.get("Scarecrow Recipe"));
        add(Recipe.craftRecipes.get("Mayonnaise Machine Recipe"));
    }};


    private final ArrayList<ProducerArtisan> placedArtisans = new ArrayList<>();

    private final HashMap<String,Animal> animals = new HashMap<>();
    private final HashMap<SkillType,Skill> skills;

    private SkillType buffSkill;
    private int buffHours;
    public int getMoney() {
        return money;
    }

    /*public void setMoney(int money) {
        this.money = money;
    }*/
    public void spentMoney(int spent){
        money -= spent;
    }
    private final Skill[] skills = new Skill[SkillType.values().length];

    private Tool equippedTool;

    public Player(User controllingUser) {
        this.controllingUser = controllingUser;
        this.money = 0;
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
        if (isUnlimitedEnergy())
            return MAXIMUM_ENERGY;
        return energy;
    }


    public boolean isFallen() {
        return energy <= 0;
    }

    public BackPack getBackpack() {
        return backpack;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public SkillType getBuffSkill() {
        return buffSkill;
    }

    public int getBuffHours() {
        return buffHours;
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
        if (!unlimitedEnergy)
            energy -= amount;
    }

    public void increaseEnergy(int amount) {
        if (unlimitedEnergy)
            energy += amount;
    }

    public boolean isUnlimitedEnergy() {
        return unlimitedEnergy;
    }

    public void setUnlimitedEnergy(boolean unlimitedEnergy) {
        this.unlimitedEnergy = unlimitedEnergy;
        if (unlimitedEnergy)
            setEnergy(MAXIMUM_ENERGY);
    }

    public void setBackpack(BackPack backpack) {
        this.backpack = backpack;
    }

    public void setBuffSkill(SkillType buffSkill) {
        this.buffSkill = buffSkill;
    }

    public void decreaseBuffHours(int buffHours) {
        this.buffHours -= buffHours;
    }

    public void setBuffHours(int buffHours) {
        this.buffHours = buffHours;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }

    public ArrayList<Gift> getRecivedGifts() {
        return recivedGifts;
    }

    public void setRecivedGifts(ArrayList<Gift> recivedGifts) {
        this.recivedGifts = recivedGifts;
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

    @Override
    public void nextDayUpdate() {
        if (this.isFallen())
            energy = 75 * MAXIMUM_ENERGY / 100;
        else
            energy = MAXIMUM_ENERGY;
    }


    public boolean checkEnergy(int energyAmount,SkillType skillType) {
        if(this.unlimitedEnergy){
            return true;
        }
        else if(skillType == null){
            return this.energy >= energyAmount;
        }
        else if(this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY){
            return this.energy >= energyAmount - 2;
        }
        else if(this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY){
            return this.energy >= energyAmount - 1;
        }
        else if(this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())){
            return this.energy >= energyAmount - 1;
        }
        else{
            return this.energy >= energyAmount;
        }
    }

    public void decreaseEnergy(int energyAmount,SkillType skillType) {
        if(this.unlimitedEnergy){
            return;
        }
        else if(skillType == null){
            this.energy -= energyAmount;
        }
        else if(this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY){
            this.energy -= energyAmount - 2;
        }
        else if(this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY){
            this.energy -= energyAmount - 1;
        }
        else if(this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())){
            this.energy -= energyAmount - 1;
        }
        else{
            this.energy -= energyAmount;
        }
    }


}
