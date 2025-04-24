package model.enums.commands;

public enum CheatCode implements Command {
    ; // TODO

    private final String regex;

    CheatCode(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
