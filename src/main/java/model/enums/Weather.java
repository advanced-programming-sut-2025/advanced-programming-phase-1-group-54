package model.enums;

public enum Weather {
    SUNNY(1.5),
    RAIN(1.2),
    STORM(0.5),
    SNOW(1);

    private final double fishingFactor;

    Weather(double fishingFactor) {
        this.fishingFactor = fishingFactor;
    }

    public double getFishingFactor() {
        return fishingFactor;
    }
}
