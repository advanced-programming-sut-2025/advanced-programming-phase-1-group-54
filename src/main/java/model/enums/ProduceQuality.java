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
}
