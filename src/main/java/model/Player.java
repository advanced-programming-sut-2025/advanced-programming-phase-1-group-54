package model;

import model.enums.AbilityType;
import model.tools.BackPack;


public class Player {
    private User controllingUser;
    private int energy;
    private BackPack backpack;
    private int[] abilities = new int[AbilityType.values().length];
    private Tool equippedTool;

    public int getAbility(AbilityType abilityType) {
        return abilities[abilityType.ordinal()];
    }

    public User getControllingUser() {
        return controllingUser;
    }

    public int getEnergy() {
        return energy;
    }

    public BackPack getBackpack() {
        return backpack;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public Tool getEquippedTool() {
        return equippedTool;
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

    public void setAbilities(int[] abilities) {
        this.abilities = abilities;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }
}
