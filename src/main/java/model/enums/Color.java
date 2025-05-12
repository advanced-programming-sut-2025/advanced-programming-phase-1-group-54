package model.enums;

public enum Color {
    DEFAULT("\u001B[0m"),

    BLACK_FONT("\u001B[30m"),
    RED_FONT("\u001B[31m"),
    GREEN_FONT("\u001B[32m"),
    YELLOW_FONT("\u001B[33m"),
    BLUE_FONT("\u001B[34m"),
    MAGENTA_FONT("\u001B[35m"),
    CYAN_FONT("\u001B[36m"),
    GRAY_FONT("\u001B[37m"),

    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    MAGENTA_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    GRAY_BACKGROUND("\u001B[47m"),
    ;
    private final String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
