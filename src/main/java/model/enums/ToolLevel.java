package model.enums;

public enum ToolLevel {
    NORMAL,
    COPPER,
    IRON,
    GOLD,
    IRIDIUM,

    TRAINING,
    BAMBOO,
    FIBERGLASS,
    ;

    public ToolLevel getNextLevel() {
        switch (this) {
            case NORMAL:
                return COPPER;
            case COPPER:
                return IRON;
            case IRON:
                return GOLD;
            case GOLD, FIBERGLASS, IRIDIUM:
                return IRIDIUM;
            case TRAINING:
                return TRAINING;
            case BAMBOO:
                return FIBERGLASS;
            default:
                return NORMAL;
        }
    }
}
