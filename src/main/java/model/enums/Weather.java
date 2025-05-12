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

    public static Weather getRandom(Season season) {
        switch (season) {
            case SPRING, SUMMER, FALL:
                return Weather.values()[(int) (Math.random() * 3)];
            case WINTER:
                return Weather.values()[((int) (Math.random() * 2)) * 3];
            default:
                return null;
        }
    }
}
