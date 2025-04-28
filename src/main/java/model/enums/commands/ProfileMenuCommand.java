package model.enums.commands;

public enum ProfileMenuCommand implements Command {
    CHANGE_USERNAME("change username -u (?<username>\\S+)"),
    CHANGE_NICKNAME("change nickname -u (?<nickname>\\S+)"),
    CHANGE_EMAIL("change email -e (?<email>\\S+)"),
    CHANGE_PASSWORD("change password -p (?<newPassword>\\S+) -o (?<oldPassword>\\S+)"),
    USER_INFO("user info"),
    ;

    private final String regex;

    ProfileMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
