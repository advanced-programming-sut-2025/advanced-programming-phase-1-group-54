package model.alive;

import controller.Game.PlantsController;
import model.DailyUpdate;
import model.Refrigerator;
import model.Skill;
import model.User;
import model.enums.FishingPoleLevel;
import model.enums.SkillType;
import model.enums.Symbol;
import model.enums.ToolType;
import model.items.crafting.ProducerArtisan;
import model.items.plants.Plant;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.items.tools.FishingPole;
import model.items.tools.Tool;
import model.items.tools.TrashCan;
import model.map.Farm;
import model.map.Location;
import model.map.Tile;
import model.relationships.Gift;

import java.util.ArrayList;
import java.util.HashMap;


public class Player extends Human implements DailyUpdate {
    private static final int MAXIMUM_ENERGY = 200;

    public static int getMaximumEnergy() {
        return MAXIMUM_ENERGY;
    }

    private int money;

    private final User controllingUser;
    private final Farm farm;

    private int energy;
    private boolean unlimitedEnergy;
    private int heartBroken;

    private final BackPack backpack = new BackPack();
    private final TrashCan trashCan = new TrashCan();

    private final Tool[] tools = new Tool[ToolType.values().length];
    private final FishingPole[] fishingPoles = new FishingPole[FishingPoleLevel.values().length];

    private Tool equippedTool;

    private Location currentLocation;
    private final HashMap<Plant, Tile> plants = new HashMap<>();

    private final ArrayList<Recipe> learnedFoodRecipes = new ArrayList<>() {{
        add(Recipe.foodRecipes.get("Fried Egg Recipe"));
    }};


    private final ArrayList<Recipe> learnedCraftingRecipes = new ArrayList<>() {{
        add(Recipe.craftRecipes.get("Furnace Recipe"));
        add(Recipe.craftRecipes.get("Scarecrow Recipe"));
        add(Recipe.craftRecipes.get("Mayonnaise Machine Recipe"));
    }};

    private final ArrayList<Gift> receivedGifts = new ArrayList<>();
    private final ArrayList<Player> askedForMarriage = new ArrayList<>();
    private Player partner = null;

    private final ArrayList<ProducerArtisan> placedArtisans = new ArrayList<>();

    private final HashMap<String, Animal> animals = new HashMap<>();
    private final HashMap<SkillType, Skill> skills;

    private SkillType buffSkill;
    private int buffHours;


    private boolean isInGiftList;

    public Player(User controllingUser, Farm farm) {
        this.controllingUser = controllingUser;
        this.farm = farm;
        this.money = 0;
        this.isInGiftList = false;
        this.skills = new HashMap<>();
        this.heartBroken = 0;
        this.energy = MAXIMUM_ENERGY;
        skills.put(SkillType.FARMING, new Skill(SkillType.FARMING));
        skills.put(SkillType.FORAGING, new Skill(SkillType.FORAGING));
        skills.put(SkillType.MINING, new Skill(SkillType.MINING));
        skills.put(SkillType.FISHING, new Skill(SkillType.FISHING));
        // TODO
    }

    public Farm getFarm() {
        return farm;
    }

    public Tool getTool(ToolType toolType) {
        return tools[toolType.ordinal()];
    }

    public void setTool(ToolType toolType, Tool tool) {
        tools[toolType.ordinal()] = tool;
    }

    public FishingPole getFishingPole(FishingPoleLevel fishingPoleLevel) {
        return fishingPoles[fishingPoleLevel.ordinal()];
    }

    public void setFishingPole(FishingPoleLevel fishingPoleLevel, FishingPole fishingPole) {
        fishingPoles[fishingPoleLevel.ordinal()] = fishingPole;
    }

    public int getMoney() {
        return money;
    }

    public void spentMoney(int spent) {
        money -= spent;
        if (partner != null) {
            partner.setMoney(money);
        }
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

    public TrashCan getTrashCan() {
        return trashCan;
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
        return plants;
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

    public Player getPartner() {
        return partner;
    }

    public void setPartner(Player partner) {
        this.partner = partner;
    }

    public void setEnergy(int energy) {
        if (energy <= MAXIMUM_ENERGY)
            this.energy = energy;
    }

    public void increaseMoney(int money) {
        if (partner != null) {
            partner.increaseMoney(money);
        }
        this.money += money;
    }

    public boolean decreaseMoney(int money) {
        if (this.money < money) {
            return false;
        }
        this.money -= money;
        if (partner != null) {
            partner.decreaseMoney(money);
        }
        return true;
    }

    public void setMoney(int money) {
        if (partner != null) {
            partner.setMoney(money);
        }
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

    public int getHeartBroken() {
        return heartBroken;
    }

    public void setHeartBroken(int heartBroken) {
        this.heartBroken = heartBroken;
    }

    public void decreaseHeartBroken() {
        heartBroken--;
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

    public Refrigerator getRefrigerator() {
        return getFarm().getCabin().getRefrigerator();
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }


    public ArrayList<Player> getAskedForMarriage() {
        return askedForMarriage;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isInGiftList() {
        return isInGiftList;
    }

    public void setInGiftList(boolean inGiftList) {
        isInGiftList = inGiftList;
    }

    @Override
    public void nextDayUpdate() {
        for (Animal animal : animals.values()) {
            animal.nextDayUpdate();
        }

        for (Plant plant : plants.keySet()) {
            if (plant.isDead()) {
                plants.remove(plant);
            }
        }

        PlantsController.foragingCrop(this);
        PlantsController.foragingSeed(this);
        PlantsController.foragingMaterial(this);

        if (this.isFallen())
            energy = 75 * MAXIMUM_ENERGY / 100;
        else {
            energy = MAXIMUM_ENERGY;
        }

        if (heartBroken > 0) {
            energy /= 2;
            decreaseHeartBroken();
        }
    }


    public boolean checkEnergy(int energyAmount, SkillType skillType) {
        if (this.unlimitedEnergy) {
            return true;
        } else if (skillType == null) {
            return this.energy >= energyAmount;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY) {
            return this.energy >= energyAmount - 2;
        } else if (this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY) {
            return this.energy >= energyAmount - 1;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())) {
            return this.energy >= energyAmount - 1;
        } else {
            return this.energy >= energyAmount;
        }
    }

    public void decreaseEnergy(int energyAmount, SkillType skillType) {
        if (this.unlimitedEnergy) {
            return;
        } else if (skillType == null) {
            this.energy -= energyAmount;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY) {
            this.energy -= energyAmount - 2;
        } else if (this.getSkills().get(skillType).getLevel() == MAXIMUM_ENERGY) {
            this.energy -= energyAmount - 1;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())) {
            this.energy -= energyAmount - 1;
        } else {
            this.energy -= energyAmount;
        }
    }


    @Override
    public Symbol getSymbol() {
        return Symbol.PLAYER;
    }
}
