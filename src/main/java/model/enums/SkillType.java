package model.enums;

import java.util.ArrayList;

public enum SkillType {
    FARMING(new ArrayList[]{new ArrayList<>(){{
        add("Sprinkler Recipe");
        add("Bee House Recipe");
    }},
    new ArrayList<>(){{
        add("Quality Sprinkler Recipe");
        add("Deluxe Scarecrow Recipe");
        add("Cheese Press Recipe");
        add("Preserves Jar Recipe");
    }},
    new ArrayList<>(){{
        add("Iridium Sprinkler Recipe");
        add("Keg Recipe");
        add("Loom Recipe");
        add("Oil Maker Recipe");
    }},
    new ArrayList<>()}),
    MINING(new ArrayList[]{new ArrayList<>(){{
        add("Cherry Bomb Recipe");
    }},
    new ArrayList<>(){{
        add("Bomb Recipe");
    }},
    new ArrayList<>(){{
        add("Mega Bomb Recipe");
    }},
    new ArrayList<>()}),
    FORAGING(new ArrayList[]{new ArrayList<>(){{
        add("Charcoal Kiln Recipe");
    }},
    new ArrayList<>(),
    new ArrayList<>(),
    new ArrayList<>(){{
        add("Mystic Tree Seed Recipe");
    }}}),
    FISHING(new ArrayList[]{new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()});

    private final ArrayList<String>[] craftingRecipesRelease ;

    SkillType(ArrayList<String>[] craftingRecipesRelease) {
        this.craftingRecipesRelease = craftingRecipesRelease;
    }

    public ArrayList<String>[] getCraftingRecipesRelease() {
        return craftingRecipesRelease;
    }
}
