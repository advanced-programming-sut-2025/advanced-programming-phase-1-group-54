package model.enums;

public enum Symbol {
    EMPTY("."),
    PLAYER(Color.BLUE_FONT + "P" + Color.DEFAULT),
    HOUSE(Color.YELLOW_FONT + "H" + Color.DEFAULT),
    GREENHOUSE(Color.GREEN_FONT + "G" + Color.DEFAULT),
    QUARRY(Color.MAGENTA_FONT + "Q" + Color.DEFAULT),

    BARN(Color.CYAN_FONT + "B" + Color.DEFAULT),
    COOP(Color.CYAN_FONT + "C" + Color.DEFAULT),

    LAKE(Color.BLUE_BACKGROUND + "~" + Color.DEFAULT),
    TREE(Color.GREEN_FONT + "T" + Color.DEFAULT),

    FORAGING(Color.YELLOW_FONT + "!" + Color.DEFAULT),
    SEED(Color.GREEN_FONT + "*" + Color.DEFAULT),
    CROP(Color.GREEN_FONT + "&" + Color.DEFAULT),
    ROCK(Color.MAGENTA_FONT + "@" + Color.DEFAULT),
    ;


    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
