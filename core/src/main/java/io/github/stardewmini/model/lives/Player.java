package io.github.stardewmini.model.lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.controller.game.PlayerController;
import io.github.stardewmini.model.DailyUpdate;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.HourUpdate;
import io.github.stardewmini.model.User;
import io.github.stardewmini.model.enums.*;
import io.github.stardewmini.model.items.crafting.ProducerArtisan;
import io.github.stardewmini.model.items.recipes.Recipe;
import io.github.stardewmini.model.items.tools.*;
import io.github.stardewmini.model.map.Farm;
import io.github.stardewmini.model.map.Location;
import io.github.stardewmini.model.map.Refrigerator;
import io.github.stardewmini.model.map.Tile;
import io.github.stardewmini.model.relationships.Gift;
import io.github.stardewmini.model.relationships.NPCFriendship;
import io.github.stardewmini.model.relationships.Relationship;
import io.github.stardewmini.model.relationships.Trade;

import java.util.ArrayList;
import java.util.HashMap;


public class Player extends Live implements DailyUpdate, HourUpdate {
    private static final int MAXIMUM_ENERGY = 200;

    public static int getMaximumEnergy() {
        return MAXIMUM_ENERGY;
    }


    private Sprite sprite = new Sprite(GameAssetManager.getInstance().getPlayerWalkRight().getKeyFrame(0));
    private boolean moving = false;
    private float moveSpeed = 4f;
    private float x, y;
    private float targetX, targetY;

    private int money;
    private int nextDayMoney = 0;

    private final User controllingUser;
    private final Farm farm;

    private int energy;
    private boolean unlimitedEnergy;
    private int heartBreakDaysRemaining;

    private final BackPack backpack = new BackPack();
    private final TrashCan trashCan = new TrashCan();

    private final Tool[] tools = new Tool[ToolType.values().length];
    private final FishingPole[] fishingPoles = new FishingPole[FishingPoleType.values().length];
    private final Skill[] skills = new Skill[SkillType.values().length];

    private Tool equippedTool;

    private Location currentLocation;

    private final ArrayList<NPCFriendship> npcFriendships;

    private final ArrayList<Recipe> learnedFoodRecipes = new ArrayList<>() {{
        add(Recipe.foodRecipes.get("Fried Egg Recipe"));
    }};


    private final ArrayList<Recipe> learnedCraftingRecipes = new ArrayList<>() {{
        add(Recipe.craftRecipes.get("Furnace Recipe"));
        add(Recipe.craftRecipes.get("Scarecrow Recipe"));
        add(Recipe.craftRecipes.get("Mayonnaise Machine Recipe"));
    }};

    private final ArrayList<Gift> receivedGifts = new ArrayList<>();
    private final ArrayList<Trade> receivedTrades = new ArrayList<>();
    private final ArrayList<Trade> receivedRequests = new ArrayList<>();
    private final ArrayList<Player> askedForMarriage = new ArrayList<>();
    private Relationship marriage = null;

    private final ArrayList<ProducerArtisan> placedArtisans = new ArrayList<>();

    private final HashMap<String, Animal> animals = new HashMap<>();

    private SkillType buffSkill;
    private int buffHours;

    public Player(User controllingUser, Farm farm, ArrayList<NPCFriendship> npcFriendships) {
        super(controllingUser.getUsername());
        this.controllingUser = controllingUser;
        this.farm = farm;
        this.npcFriendships = npcFriendships;
        this.money = 0;
        this.heartBreakDaysRemaining = 0;
        this.energy = MAXIMUM_ENERGY;

        for (SkillType skilltype : SkillType.values()) {
            this.skills[skilltype.ordinal()] = new Skill(skilltype);
        }

        this.setTool(ToolType.HOE, new Tool(ToolType.HOE));
        this.setTool(ToolType.PICKAXE, new Tool(ToolType.PICKAXE));
        this.setTool(ToolType.AXE, new Tool(ToolType.AXE));
        this.setTool(ToolType.SCYTHE, new Tool(ToolType.SCYTHE));
        this.setTool(ToolType.WATERING_CAN, new WateringCan());

        this.setEquippedTool(this.getTool(ToolType.HOE));

        this.setFishingPole(FishingPoleType.TRAINING, new FishingPole(FishingPoleType.TRAINING));

        this.sprite.setSize(Tile.getSize(), (int) Math.floor(Tile.getSize() * 1.7));
    }

    public User getControllingUser() {
        return controllingUser;
    }

    public Gender getGender() {
        return controllingUser.getGender();
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

    public FishingPole getFishingPole(FishingPoleType fishingPoleType) {
        return fishingPoles[fishingPoleType.ordinal()];
    }

    public void setFishingPole(FishingPoleType fishingPoleType, FishingPole fishingPole) {
        fishingPoles[fishingPoleType.ordinal()] = fishingPole;
    }

    public Skill getSkill(SkillType skillType) {
        return skills[skillType.ordinal()];
    }

    public int getEnergy() {
        if (unlimitedEnergy)
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

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public Location getCurrentLocation() {
        return currentLocation;
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
        if (marriage == null)
            return null;
        return marriage.getOtherPlayer(this);
    }

    public void setMarriage(Relationship marriage) {
        this.marriage = marriage;
    }

    public void increaseNextDayMoney(int count) {
        nextDayMoney += count;
    }

    public void setEnergy(int energy) {
        if (!unlimitedEnergy) {
            if (energy <= MAXIMUM_ENERGY)
                this.energy = energy;
        }
    }

    public int getMoney() {
        if (marriage != null)
            return marriage.getSharedMoney();
        return money;
    }


    public void increaseMoney(int money) {
        if (marriage != null)
            marriage.increaseSharedMoney(money);
        else
            this.money += money;

    }

    public void decreaseMoney(int money) {
        if (marriage != null)
            marriage.decreaseSharedMoney(money);
        else
            this.money -= money;
    }


    public void decreaseEnergy(int amount) {
        if (!unlimitedEnergy) {
            energy -= amount;
            if (energy < 0)
                energy = 0;
        }
    }

    public void increaseEnergy(int amount) {
        if (!unlimitedEnergy) {
            energy += amount;
            if (energy > MAXIMUM_ENERGY)
                energy = MAXIMUM_ENERGY;
        }
    }

    public void setUnlimitedEnergy(boolean unlimitedEnergy) {
        this.unlimitedEnergy = unlimitedEnergy;
        if (unlimitedEnergy)
            setEnergy(MAXIMUM_ENERGY);
    }

    public void setHeartBreakDaysRemaining(int heartBreakDaysRemaining) {
        this.heartBreakDaysRemaining = heartBreakDaysRemaining;
    }

    public void setBuffSkill(SkillType buffSkill) {
        this.buffSkill = buffSkill;
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

    public ArrayList<Trade> getReceivedTrades() {
        return receivedTrades;
    }

    public ArrayList<Trade> getReceivedRequests() {
        return receivedRequests;
    }

    public ArrayList<Player> getAskedForMarriage() {
        return askedForMarriage;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        x = currentLocation.column() * Tile.getSize();
        y = currentLocation.row() * Tile.getSize();
        targetX = x;
        targetY = y;
    }

    public ArrayList<NPCFriendship> getNpcFriendships() {
        return npcFriendships;
    }

    @Override
    public void nextDayUpdate() {
        if (this.isFallen()) {
            energy = 75 * MAXIMUM_ENERGY / 100;
        } else {
            energy = MAXIMUM_ENERGY;
        }

        if (heartBreakDaysRemaining > 0) {
            energy /= 2;
            heartBreakDaysRemaining--;
        }

        this.increaseMoney(nextDayMoney);
        nextDayMoney = 0;
    }


    public boolean checkEnergy(int energyAmount, SkillType skillType) {
        if (this.unlimitedEnergy) {
            return true;
        } else if (skillType == null) {
            return this.energy > energyAmount;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkill(skillType).getLevel() == MAXIMUM_ENERGY) {
            return this.energy > energyAmount - 2;
        } else if (this.getSkill(skillType).getLevel() == MAXIMUM_ENERGY) {
            return this.energy > energyAmount - 1;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())) {
            return this.energy > energyAmount - 1;
        } else {
            return this.energy > energyAmount;
        }
    }

    public void decreaseEnergy(int energyAmount, SkillType skillType) {
        if (this.unlimitedEnergy) {
            return;
        } else if (skillType == null) {
            this.energy -= energyAmount;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill()) &&
                this.getSkill(skillType).getLevel() == MAXIMUM_ENERGY) {
            this.energy -= energyAmount - 2;
        } else if (this.getSkill(skillType).getLevel() == MAXIMUM_ENERGY) {
            this.energy -= energyAmount - 1;
        } else if (this.getBuffSkill() != null && skillType.equals(this.getBuffSkill())) {
            this.energy -= energyAmount - 1;
        } else {
            this.energy -= energyAmount;
        }
    }

    public void update(float delta) {
        if (moving) {
            // Move towards target
            float step = moveSpeed * Tile.getSize() * delta;
            if (Math.abs(targetX - x) <= step && Math.abs(targetY - y) <= step) {
                x = targetX;
                y = targetY;
                moving = false;
            } else {
                if (x < targetX) x += step;
                if (x > targetX) x -= step;
                if (y < targetY) y += step;
                if (y > targetY) y -= step;
            }
        }
    }

    public void tryMove(int dx, int dy) {
        if (moving) return; // Ignore input while moving

        int newX = currentLocation.column() + dx;
        int newY = currentLocation.row() + dy;

        // TODO: check if new tile is walkable

        currentLocation = new Location(newY, newX);
        targetX = newX * Tile.getSize();
        targetY = newY * Tile.getSize();
        moving = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.PLAYER;
    }

    @Override
    public void nextHourUpdate(int amount) {
        if (buffSkill != null) {
            buffHours -= amount;
            if (buffHours <= 0) {
                buffHours = 0;
                buffSkill = null;
            }
        }
    }
}
