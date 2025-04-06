package model.enums;

public enum GameMenuCommand implements Command {
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
