package io.github.stardewmini.common.model.enums;

public enum ToolType {
    AXE,
    PICKAXE,
    HOE,
    MILK_PAIL,
    SCYTHE,
    SHEAR,
    WATERING_CAN;

    public static ToolType fromString(String string) {
        for (ToolType toolType : ToolType.values()) {
            if (toolType.toString().equals(string)) {
                return toolType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase()
            .replace("_", " ");
    }
}
