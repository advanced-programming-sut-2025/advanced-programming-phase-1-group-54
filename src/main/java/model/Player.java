package model;

import model.enums.AbilityType;

import java.util.ArrayList;

public class Player {
    private User controllingUser;
    private int energy;

    private ArrayList<Item> inventory;

    private int[] abilities = new int[AbilityType.values().length];

    private Tool equippedTool;

    public int getAbility(AbilityType abilityType) {
        return abilities[abilityType.ordinal()];
    }
}
