package model.items.plants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Tree extends Plant {

    public static HashMap<String,Tree> trees;

    public Tree clone() {
        Tree newTree = new Tree();

        newTree.name = this.name;
        newTree.source = this.source;
        newTree.stages = this.stages;
        newTree.totalHarvestTime = this.totalHarvestTime;

        return newTree;
    }
}
