package model.enums.commands;

public enum LoginMenuCommand implements Command {
    LOGIN("login -u (?<username>\\S+) -p (?<password>\\S+)( (?<stayLoggedIn>--stay-logged-in))?"),
    FORGET_PASSWORD("forget password -u (?<username>\\S+)"),
    ANSWER("answer -a (?<answer>.+)"),
    ;

    private final String regex;

    LoginMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
