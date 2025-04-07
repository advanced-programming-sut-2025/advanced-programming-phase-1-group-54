package model;

import model.enums.AbilityType;
import model.tools.BackPack;


public class Player {
    private User controllingUser;
    private int energy;
    private BackPack backpack;
    private final int[] abilitiesunit = new int[AbilityType.values().length];
    private final int[] abilitiesLevel = new int[AbilityType.values().length];
    private Tool equippedTool;

    public int getAbility(AbilityType abilityType) {
        return abilitiesunit[abilityType.ordinal()];
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

    public int[] getAbilitiesunit() {
        return abilitiesunit;
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public int[] getAbilitiesLevel() {
        return abilitiesLevel;
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

    public void increaseFarmingAbility(int amount) {
        abilitiesunit[AbilityType.FARMING.ordinal()] += amount;
        if(abilitiesLevel[AbilityType.FARMING.ordinal()] != 4
                && abilitiesunit[AbilityType.FARMING.ordinal()] >
                100 * abilitiesLevel[AbilityType.FARMING.ordinal()] + 50) {
            abilitiesLevel[AbilityType.FARMING.ordinal()]++;
        }
    }

    public void increaseExtractionAbility(int amount) {
        abilitiesunit[AbilityType.EXTRACTION.ordinal()] += amount;
        if(abilitiesLevel[AbilityType.EXTRACTION.ordinal()] != 4
                && abilitiesunit[AbilityType.EXTRACTION.ordinal()] >
                100 * abilitiesLevel[AbilityType.EXTRACTION.ordinal()] + 50) {
            abilitiesLevel[AbilityType.EXTRACTION.ordinal()]++;
        }
    }

    public void increaseForagingAbility(int amount) {
        abilitiesunit[AbilityType.FORAGING.ordinal()] += amount;
        if(abilitiesLevel[AbilityType.FORAGING.ordinal()] != 4
                && abilitiesunit[AbilityType.FORAGING.ordinal()] >
                100 * abilitiesLevel[AbilityType.FORAGING.ordinal()] + 50) {
            abilitiesLevel[AbilityType.FORAGING.ordinal()]++;
        }
    }

    public void increaseFishingAbility(int amount) {
        abilitiesunit[AbilityType.FISHING.ordinal()] += amount;
        if(abilitiesLevel[AbilityType.FISHING.ordinal()] != 4
                && abilitiesunit[AbilityType.FISHING.ordinal()] >
                100 * abilitiesLevel[AbilityType.FISHING.ordinal()] + 50) {
            abilitiesLevel[AbilityType.FISHING.ordinal()]++;
        }
    }

}
