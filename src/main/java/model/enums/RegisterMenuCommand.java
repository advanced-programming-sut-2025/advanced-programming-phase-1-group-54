package model.enums;

public enum RegisterMenuCommand implements Command {
    REGISTER(""),
    PICK_QUESTION(""),
    ; // TODO

    private final String regex;

    RegisterMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
