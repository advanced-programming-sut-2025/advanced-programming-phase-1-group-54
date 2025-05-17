package model.enums.commands;

public enum GameCommand implements Command {
    // General Commands
    EXIT_GAME("exit game"),
    NEXT_TURN("next turn"),
    TIME("time"),
    DATE("date"),
    DATETIME("datetime"),
    DAY_OF_THE_WEEK("day of the week"),
    SEASON("season"),
    WEATHER("weather"),
    WEATHER_FORECAST("weather forecast"),

    // Map Commands
    GREENHOUSE_BUILD("greenhouse build"),
    WALK("walk -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    PRINT_MAP("print map -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+) -s (?<size>\\d+)"),
    HELP_READ_MAP("help reading map"),

    // Player Commands
    SHOW_ENERGY("energy show"),
    SHOW_INVENTORY("inventory show"),
    THROW_IN_TRASH("inventory trash -i (?<itemName>.+)( -n (?<number>\\d+))?"),
    EQUIP_TOOL("tools equip (?<toolName>.+)"),
    SHOW_CURRENT_TOOL("tools show current"),
    SHOW_AVAILABLE_TOOLS("tools show available"),
    USE_TOOL("tools use -d (?<direction>.+)"),
    UPGRADE_TOOL("tools upgrade (?<toolName>.+)"),

    // Farming commands
    SHOW_PLANT_INFO("craftinfo -n (?<plantName>.+)"),
    PLANTING("plant -s (?<seedName>.+) -d (?<direction>.+)"),
    SHOW_PLANT_AT_LOCATION("showplant -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    FERTILIZE("fertilize -f (?<fertilizer>.+) -d (?<direction>.*)"),
    SHOW_WATER("howmuch water"),

    // Crafting commands
    SHOW_CRAFTING_RECIPES("crafting show recipes"),
    CRAFTING("crafting craft (?<itemName>.+)"),
    PLACE_ITEM("place item -n (?<itemName>.+) -d (?<direction>.+)"),

    // Cooking commands
    REFRIGERATOR_ACTION("cooking refrigerator (?<action>put|pick) (?<itemName>.+)"),
    SHOW_COOKING_RECIPES("cooking show recipes"),
    COOKING("cooking prepare (?<recipeName>.+)"),
    EATING("eat (?<foodName>.+)"),

    // Animal commands
    BUILD_ANIMAL_HOUSE("build -a (?<buildingName>.+) -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    BUY_ANIMAL("buy animal -a (?<animalName>.+) -n (?<name>.+)"),
    PET_ANIMAL("pet -a (?<name>.+)"),
    FEED_ANIMAL("feed hay -n (?<animalName>.+) -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    SHOW_ANIMALS("animals"),
    MOVE_ANIMAL("shepherd animals -n (?<name>.+) -l (?<x>[-+]?\\d+) (?<y>[-+]?\\d+)"),
    SHOW_ANIMAL_PRODUCES("produces"),
    COLLECT_ANIMAL_PRODUCE("collect produce -n (?<name>.+)"),
    SELL_ANIMAL("sell animal -n (?<name>.+)"),

    // Fishing commands
    FISHING("fishing -p (?<poleName>.+)"),

    // Artisan commands
    START_PRODUCER_ARTISAN("artisan use (?<artisanName>.+) (?<produceName>.+)"),
    GET_PRODUCT_ARTISAN("artisan get (?<artisanName>.+)"),

    // Shop commands
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_AVAILABLE_PRODUCTS("show all available products"),
    PURCHASE_ITEM("purchase (?<productName>.+)( -n (?<count>\\d+))?"),
    SELLING("sell (?<productName>.+)( -n (?<count>\\d+))?"),

    // Friendship Commands
    SHOW_FRIENDSHIPS("friendships"),
    TALKING(" talk -u (?<username>\\S+) -m (?<message>.+)"),
    SHOW_TALK_HISTORY("talk history (?<username>\\S+)"),
    GIFT("gift -u (?<username>\\S+) -i (?<itemName>.+) -a (?<amount>\\d+)"),
    SHOW_GIFT_LIST("gift list"),
    RATE_GIFT("gift rate -i (?<number>\\d+) -r (?<rate>\\d+)"),
    SHOW_GIFTS_FROM_PLAYER("gift history -u (?<username>\\S+)"),
    HUGGING("hug -u (?<username>\\S+)"),
    GIVE_FLOWER("flower -u (?<username>\\S+)"),
    ASK_MARRIAGE("ask marriage -u (?<username>\\S+) -r (?<ringName>.+)"),
    RESPOND_MARRIAGE("respond -(?<answer>(accept|reject)) -u (?<username>\\S+)"),

    // NPC
    FRIENDSHIP_NPC_LIST("friendship NPC list"),
    MEET_NPC("meet NPC (?<npcName>.+)"),
    GIFT_NPC("gift NPC (?<npcName>.+) -i (?<item>.+)"),
    QUESTS_LIST("quests list"),
    QUESTS_FINISH("quests finish -i (?<index>.+)"),

    // TRADING MENU COMMANDS
    START_TRADING("start trade"),
    SHOW_TRADES_LIST("trade list"),
    TRADING_RESPONSE("trade response -(?<answer>(accept|reject)) -i (?<id>\\d+)"),
    SHOW_TRADES_HISTORY("trade history"),
    STOP_TRADING("stop trade"),
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
