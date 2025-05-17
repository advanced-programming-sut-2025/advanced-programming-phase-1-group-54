package model.lives;

import model.Quest;
import model.enums.Symbol;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Live {
    private static Random rand = new Random();
    private String job;


    private final ArrayList<String> favoriteItems = new ArrayList<>();

    private final ArrayList<Quest> allQuests = new ArrayList<>();

    private int newQuestCounter;
    public NPC(String job, String name) {
        super(name);
        this.job = job;
        newQuestCounter = rand.nextInt(30) + 30;
    }
    public void activateQuest(Quest quest) {
        if (allQuests.contains(quest)) {
            quest.setCompleted(true);
        }
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public ArrayList<String> getFavoriteItems() {
        return favoriteItems;
    }

    public ArrayList<Quest> getAllQuests() {
        return allQuests;
    }

    public  void checkCounter(){
        if (this.newQuestCounter == 0){
            this.allQuests.get(2).setActive(true);
        }
        else{
            this.newQuestCounter--;
        }
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.NPC;
    }
}
