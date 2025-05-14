package model.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCode implements Command {
    ADD_MONEY("cheat add (?<count>\\S) dollars")
    ; // TODO

    private final String regex;

    CheatCode(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
    public Matcher getMatcher(String input){
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
