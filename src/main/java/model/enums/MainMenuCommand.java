package model.enums;

public enum MainMenuCommand implements Command {
    ; // TODO

    private final String regex;

    MainMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
