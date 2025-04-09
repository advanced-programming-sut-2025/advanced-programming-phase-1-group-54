package model.enums;

public enum LoginMenuCommand implements Command {
    LOGIN(""),
    FORGET_PASSWORD(""),
    ANSWER(""),
    ; // TODO

    private final String regex;

    LoginMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
