package model.enums;

public enum GameMenuCommand implements Command {
    NEW_GAME(""),
    LOAD_GAME(""),
    CHOOSE_MAP(""),
    ; // TODO

    private final String regex;

    GameMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
