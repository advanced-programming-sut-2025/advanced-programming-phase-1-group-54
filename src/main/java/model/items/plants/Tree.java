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


}
