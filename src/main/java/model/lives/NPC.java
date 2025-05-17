package model.lives;

import model.Quest;
import model.enums.Symbol;
import model.items.Item;

import java.util.ArrayList;

public class NPC extends Live {
    private final String job;
    private ArrayList<String> springDialogs = new ArrayList<>();
    private ArrayList<String> summerDialogs = new ArrayList<>();
    private ArrayList<String> fallDialogs = new ArrayList<>();
    private ArrayList<String> winterDialogs = new ArrayList<>();


    private ArrayList<String> favoriteItems = new ArrayList<>();

    private ArrayList<Quest> allQuests = new ArrayList<>();

    public NPC(String job, String name) {
        super(name);
        this.job = job;
    }
    public void activateQuest(Quest quest) {
        if (allQuests.contains(quest)) {
            quest.setCompleted(true);
        }
    }

    public String getJob() {
        return job;
    }


    public ArrayList<String> getSpringDialogs() {
        return springDialogs;
    }

    public void setSpringDialogs(ArrayList<String> springDialogs) {
        this.springDialogs = springDialogs;
    }

    public ArrayList<String> getSummerDialogs() {
        return summerDialogs;
    }

    public void setSummerDialogs(ArrayList<String> summerDialogs) {
        this.summerDialogs = summerDialogs;
    }

    public ArrayList<String> getFallDialogs() {
        return fallDialogs;
    }

    public void setFallDialogs(ArrayList<String> fallDialogs) {
        this.fallDialogs = fallDialogs;
    }

    public ArrayList<String> getWinterDialogs() {
        return winterDialogs;
    }

    public void setWinterDialogs(ArrayList<String> winterDialogs) {
        this.winterDialogs = winterDialogs;
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


    @Override
    public Symbol getSymbol() {
        return Symbol.NPC;
    }
}
