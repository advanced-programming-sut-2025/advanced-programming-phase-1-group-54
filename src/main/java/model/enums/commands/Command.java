package model.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    String getRegex();

    default boolean matches(String input) {
        return input.matches(getRegex());
    }

    default Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(getRegex()).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

    default String getGroup(String input, String group) {
        Matcher matcher = getMatcher(input);
        if (matcher != null)
            return matcher.group(group);
        return null;
    }
}
