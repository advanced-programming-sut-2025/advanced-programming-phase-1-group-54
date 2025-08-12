package io.github.stardewmini.model.enums;

public enum FishingPoleType {
    TRAINING,
    BAMBOO,
    FIBERGLASS,
    IRIDIUM;

    public static FishingPoleType fromString(String fishingPoleName) {
        for (FishingPoleType type : FishingPoleType.values()) {
            if (type.toString().equals(fishingPoleName)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
