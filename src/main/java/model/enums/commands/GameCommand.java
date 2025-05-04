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
