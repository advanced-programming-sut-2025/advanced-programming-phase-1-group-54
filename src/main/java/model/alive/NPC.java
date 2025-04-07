package model.alive;

import model.Building.House;
import model.Quest;
import model.items.Item;

import java.util.ArrayList;

public class NPC extends Character {
    private String job;
    private House house;
    
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
}
