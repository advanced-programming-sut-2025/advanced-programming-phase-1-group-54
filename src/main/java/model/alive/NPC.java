package model.alive;

import model.Quest;
import model.enums.Symbol;
import model.items.Item;

import java.util.ArrayList;

public class NPC extends Human {
    private String job;
    
    private ArrayList<String> dialogs;
    
    private ArrayList<Item> possibleGifts;
    
    private ArrayList<Item> favoriteItems;

    private ArrayList<Quest> allQuests;

    private ArrayList<Quest> activeQuests;

    public void activateQuest(Quest quest) {
        if (allQuests.contains(quest)) {
            activeQuests.add(quest);
        }
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.NPC;
    }
}
