package model.items.plants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class Tree extends Plant {
    static Map<String,Tree> treeMap;
    static {
        ObjectMapper mapper = new ObjectMapper();
        treeMap = mapper.convertValue(new File("trees.json"),new TypeReference<Map<String,Tree>>(){});
    }


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
