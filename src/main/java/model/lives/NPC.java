package model.lives;

import model.Quest;
import model.enums.Symbol;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Live {
    private static Random rand = new Random();


    private String job;


    private ArrayList<String> favoriteItems;

    private ArrayList<Quest> allQuests;

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

    public void setFavoriteItems(ArrayList<String> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }

    public ArrayList<Quest> getAllQuests() {
        return allQuests;
    }

    public void setAllQuests(ArrayList<Quest> allQuests) {
        this.allQuests = allQuests;
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
