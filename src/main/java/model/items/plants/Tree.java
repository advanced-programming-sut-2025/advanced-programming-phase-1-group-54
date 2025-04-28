package model.items.plants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Tree extends Plant {

    public Tree(String name, String source, String fruit, int[] stages, int regrowthTime, Season[] seasons) {
        super(name, source, fruit, stages, regrowthTime, seasons);
    }

    public static HashMap<String,Tree> trees;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("trees.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Tree>>(){}.getType();
        trees = gson.fromJson(file,type);
        System.out.println(trees.size());
    }

//    public Tree clone() {
//        Tree newTree = new Tree();
//
//        //newTree.name = this.name;
//        newTree.source = this.source;
//        newTree.stages = this.stages;
//        newTree.totalHarvestTime = this.totalHarvestTime;
//
//        return newTree;
//    }
}
