package model.items.plants;

public abstract class Plant {

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
