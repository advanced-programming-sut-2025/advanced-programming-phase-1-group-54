package model.enums;

import model.items.tools.Tool;

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
        return this.name().toLowerCase().replace("_", " ");
    }
}
