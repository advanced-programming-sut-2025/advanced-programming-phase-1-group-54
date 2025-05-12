package model.enums;

public enum BackPackLevel {
    NORMAL(12),
    LARGE(24),
    DELUX(Integer.MAX_VALUE);

    private final int size;

    BackPackLevel(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}