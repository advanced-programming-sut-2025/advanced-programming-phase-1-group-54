package model.enums.commands;

public enum GameMenuCommand implements Command {
    NEW_GAME("game new -u (?<usernames>.+)"),
    LOAD_GAME("load game"),
    CHOOSE_MAP("game map (?<number>[-+]?\\d+)"),
    ;

    private final String regex;

    GameMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
