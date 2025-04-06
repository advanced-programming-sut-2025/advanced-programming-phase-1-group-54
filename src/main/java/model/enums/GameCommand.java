package model.enums;

public enum GameCommand implements Command {
    ; // TODO

    private final String regex;

    GameCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
