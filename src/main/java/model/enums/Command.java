package model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    String getRegex();

    default Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(getRegex()).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
