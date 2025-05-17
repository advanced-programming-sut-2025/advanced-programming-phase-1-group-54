package model.enums.commands;

public enum CheatCode implements Command {
    ADVANCE_TIME("cheat advance time (?<x>\\d+)h"),
    ADVANCE_DATE("cheat advance date (?<x>\\d+)d"),
    THOR("cheat Thor -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    SET_WEATHER("cheat weather set (?<weatherName>.+)"),
    SET_ENERGY("energy set (?<value>[-+]?\\d+)"),
    SET_UNLIMITED_ENERGY("energy unlimited"),
    ADD_ITEM("cheat add item -n (?<itemName>.+) -c (?<count>\\d+)"),
    SET_FRIENDSHIP("cheat set friendship -n (?<animalName>.+) -c (?<amount>\\d+)"),
    ADD_MONEY("cheat add (?<count>\\d+) dollars"),
    ;

    private final String regex;

    CheatCode(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
