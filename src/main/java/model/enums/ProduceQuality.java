package model.enums;

public enum ProduceQuality {
    NORMAL(1),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2);
    private final double value;

    ProduceQuality(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static ProduceQuality giveQuality(double quality) {
        if (quality >= 0 && quality < 0.5) {
            return ProduceQuality.NORMAL;
        } else if (quality >= 0.5 && quality < 0.7) {
            return ProduceQuality.SILVER;
        } else if (quality >= 0.7 && quality < 0.9) {
            return ProduceQuality.GOLD;
        } else {
            return ProduceQuality.IRIDIUM;
        }
    }
}
