package model.enums.commands;

public enum MainMenuCommand implements Command {
    LOGOUT("user logout"),
    ;

    private final String regex;

    MainMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
