package model.items.plants;

public class Tree extends Plant {

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

    public Tree clone() {
        Tree newTree = new Tree();

        newTree.name = this.name;
        newTree.source = this.source;
        newTree.stages = this.stages;
        newTree.totalHarvestTime = this.totalHarvestTime;
        newTree.oneTime = this.oneTime;

        return newTree;
    }
}
