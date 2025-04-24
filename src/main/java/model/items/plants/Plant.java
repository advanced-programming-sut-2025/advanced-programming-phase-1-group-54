package model.items.plants;

import model.Placeable;
import model.enums.Season;
import model.enums.Weather;
import model.items.Item;

public abstract class Plant extends Item implements Placeable {

    protected String name;
    protected Seed source;

    protected int[] stages;
    protected int totalHarvestTime;
    protected boolean oneTime;
    protected int regrowthTime;
    protected int baseSellPrice;
    protected boolean isEdible;
    protected int energy;
    protected Season[] seasons;
    protected boolean canBecomeGiant;
}
