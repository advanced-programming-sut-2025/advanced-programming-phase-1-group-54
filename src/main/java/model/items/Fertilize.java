package model.items;

import model.enums.Feature;

import java.util.HashMap;

public class Fertilize extends Item {

    private static final HashMap<String, Fertilize> fertilizers = new HashMap<>() {{
        put("Speed-Gro", new Fertilize("Speed-Gro", false, Feature.SPEEDFERTILIZE));
        put("Deluxe Retaining Soil", new Fertilize("Deluxe Retaining Soil", false, Feature.WATERFERTILIZE));
    }};

    public static Fertilize getFertilizer(String fertilizeName) {
        return fertilizers.get(fertilizeName);
    }


    private final Feature feature;

    public Fertilize(String name, boolean isEdible, Feature feature) {
        super(name, isEdible, 0);
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fertilize fertilize) {
            return fertilize.getName().equals(this.getName());
        }
        return false;
    }

}
