package model.items.plants;

import model.Placeable;
import model.items.Item;

public abstract class Plant extends Item implements Placeable {

    private String name;
    private Seed source;

    private int[] stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private boolean canBecomeGiant;
}
