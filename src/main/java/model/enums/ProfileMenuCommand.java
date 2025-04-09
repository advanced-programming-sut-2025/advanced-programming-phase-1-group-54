package model.enums;

public enum ProfileMenuCommand implements Command {
    CHANGE_USERNAME(""),
    CHANGE_NICKNAME(""),
    CHANGE_PASSWORD(""),
    CHANGE_EMAIL(""),
    USER_INFO(""),
    ; // TODO

    private final String regex;

    ProfileMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
