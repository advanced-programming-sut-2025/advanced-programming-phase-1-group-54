package model.enums;

public enum ToolLevel {
    NORMAL,
    COPPER,
    IRON,
    GOLD,
    IRIDIUM,
    ;


    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
