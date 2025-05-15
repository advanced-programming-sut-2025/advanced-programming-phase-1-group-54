package model.enums;

public enum FishingPoleType {
    TRAINING,
    BAMBOO,
    FIBERGLASS,
    IRIDIUM;

    public static FishingPoleType fromString(String fishingPoleName) {
        switch (fishingPoleName) {
            case "training":
                return TRAINING;
            case "bamboo":
                return BAMBOO;
            case "fiberglass":
                return FIBERGLASS;
            case "iridium":
                return IRIDIUM;
            default:
                return null;
        }
    }
}
