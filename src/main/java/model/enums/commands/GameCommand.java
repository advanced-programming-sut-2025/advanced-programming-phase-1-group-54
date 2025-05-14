package model.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommand implements Command {
    // General Commands
    EXIT_GAME("exit game"),
    NEXT_TURN("next turn"),
    TIME("time"),
    DATE("date"),
    DATETIME("datetime"),
    DAY_OF_THE_WEEK("day of week"),
    SEASON("season"),
    WEATHER("weather"),
    WEATHER_FORECAST("weather forecast"),

    // Farm commands
    GREENHOUSE_BUILD("greenhouse build"),

    // Map Commands
    WALK("walk -l (?<x>\\d+) (?<y>\\d+)"),
    PRINT_MAP("print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)"),
    HELP_READ_MAP("help reading map"),
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    PURCHASE_ITEM("purchase (?<product_name>\\S) -n (?<count>\\d)"),
    PURCHASE_ANIMAL("purchase animal (?<product_name>\\S) -n (?<count>\\d) -name (?<name>\\S)"),
    SELL("sell (?<product_name>\\S) -n (?<count>\\d)"),
    SELL_ALL("sell (?<product_name>\\S)"),
    FRIENDSHIP("friendships"),
    TALK(" talk -u (?<username>\\S) -m (?<message>\\S)"),
    TALK_HISTORY("talk history (?<username>\\S)"),
    GIFT("gift -u (?<username>\\S) -i (?<item>\\S) -a (?<amount>\\d)"),
    GIFT_LIST("gift list"),
    GIFT_RATE("gift rate -i (?<number>\\d) -r (?<rate>\\d)"),
    GIFT_HISTORY("gift history -u (?<username>\\S)"),
    HUG("hug -u (?<username>\\S)"),
    FLOWER("flower -u (?<username>\\S)"),
    ASK_MARRIAGE("ask marriage -u (?<username>\\S) -r (?<ring>\\S)"),
    RESPOND_MARRIAGE("respond (?<bool>(–accept|–reject)) -u (?<username>\\S)"),
    START_TRADE("start trade"),
    TRADE_REQUSET("trade -u (?<username>\\S) -t request -i (<item>\\S) -a (?<amount>\\d)"),
    TRADE_WITH_MONEY("trade -u (?<username>\\S) -t offer -i (?<item>\\S) -a (?<amount>\\d) -p (?<price>\\d)"),
    TRADE_WITH_PRODUCT("trade -u (?<username>\\S) -t offer -i (?<item>\\S) -a (?<amount>\\d) -ti (?<targetItem>\\S) -ta (?<targetAmount>\\d)"),
    TRADE_LIST("trade list"),
    TRADE_RESPONSE("trade response (?<answer>(–accept|–reject)) -i (?<id>\\d)"),
    TRADE_HISTORY("trade history"),
    EXIT("exit")
    ;
    private final String regex;
    GameCommand(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
