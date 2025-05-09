package model.enums.commands;

public enum GameCommand implements Command {
    // General Commands
    EXIT_GAME("exit game"),
    NEXT_TURN("next turn"),
    TIME("time"),
    DATE("date"),
    DATETIME("datetime"),
    DAY_OF_THE_WEEK("day of week"),
    SEASON("season"),
    WEATHER("weather"),
    WEATHER_FORECAST("weather forecast"),

    // Farm commands
    GREENHOUSE_BUILD("greenhouse build"),

    // Map Commands
    WALK("walk -l (?<x>\\d+) (?<y>\\d+)"),
    PRINT_MAP("print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)"),
    HELP_READ_MAP("help reading map"),

    ;

    private final String regex;

    GameCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
