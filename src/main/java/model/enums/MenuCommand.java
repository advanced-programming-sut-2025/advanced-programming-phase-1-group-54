package model.enums;

public enum MenuCommand implements Command {
    ENTER_MENU("menu enter (?<menuName>\\S+)"),
    EXIT_MENU("menu exit"),
    SHOW_CURRENT_MENU("show current menu");


    private final String regex;

    MenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
