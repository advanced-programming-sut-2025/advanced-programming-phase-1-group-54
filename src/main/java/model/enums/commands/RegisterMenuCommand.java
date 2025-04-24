package model.enums.commands;

public enum RegisterMenuCommand implements Command {
    REGISTER("register -u (?<username>\\S+)( -p (?<password>\\S+) (?<confirmPassword>\\S+))? -n (?<nickname>\\S+)" +
            " -e (?<email>\\S+) -g (?<gender>male|female|M|F|m|f)"),
    PICK_QUESTION("pick question -q (?<number>[-+]?\\d+) -a (?<answer>.+) -c (?<confirmAnswer>.+)"),
    ;

    private final String regex;

    RegisterMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
