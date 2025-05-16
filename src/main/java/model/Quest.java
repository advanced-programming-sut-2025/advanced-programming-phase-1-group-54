package model;

import model.lives.NPC;
import model.items.Item;

public class Quest {
    NPC questGiver;

    String name;
    String description;

    Item requestedItem;
    Item reward;

    boolean completed;
}
