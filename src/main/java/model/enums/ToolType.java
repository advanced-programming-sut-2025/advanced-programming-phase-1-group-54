package model.enums;

public enum ToolType {
    AXE,
    PICKAXE,
    HOE,
    MILK_PAIL,
    SCYTHE,
    SHEAR,
    WATERING_CAN;

    public static ToolType fromString(String toolType) {
        switch (toolType) {
            case "axe":
                return ToolType.AXE;
            case "pickaxe":
                return ToolType.PICKAXE;
            case "hoe":
                return ToolType.HOE;
            case "milk pail":
                return ToolType.MILK_PAIL;
            case "scythe":
                return ToolType.SCYTHE;
            case "shear":
                return ToolType.SHEAR;
            case "watering can":
                return ToolType.WATERING_CAN;
        }
        return null;
    }
}
