package model.enums;

public enum ProfileMenuCommand implements Command {
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
