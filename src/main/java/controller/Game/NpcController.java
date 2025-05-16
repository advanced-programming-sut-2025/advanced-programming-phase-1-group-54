package controller.Game;

import model.App;
import model.Quest;
import model.items.Item;
import model.lives.NPC;
import model.map.World;

public class NpcController {
    public void initialNPCs(){
        NPC npc = new NPC("Artisan","sebastian");

        npc.getFavoriteItems().add("Wool");
        npc.getFavoriteItems().add("Pumpkin pie");
        npc.getFavoriteItems().add("Pizza");


        Quest quest = new Quest(npc,"Iron Ore",50,"Diamond",2,true);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc,"Pumpkin pie",1,"Coin",5000,false);
        npc.getAllQuests().add(quest);


        quest = new Quest(npc,"Stone",150,"Quartz",50,false);
        npc.getAllQuests().add(quest);

        App.getCurrentGame().getWorld().getNpcs().add(npc);



        NPC npc1 = new NPC("Miner","Abigail");

        npc1.getFavoriteItems().add("Stone");
        npc1.getFavoriteItems().add("Iron Ore");
        npc1.getFavoriteItems().add("Coffee");

        quest = new Quest(npc1,"Gold Bar",1,"friendShip",1,true);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1,"Pumpkin",1,"Coin",500,false);
        npc1.getAllQuests().add(quest);

        quest = new Quest(npc1,"Wheat",50,"Iridium Sprinkler",1,false);
        npc1.getAllQuests().add(quest);

        App.getCurrentGame().getWorld().getNpcs().add(npc1);



        NPC npc2 = new NPC("Baker","Harvey");

        npc2.getFavoriteItems().add("Coffee");
        npc2.getFavoriteItems().add("Pickle");
        npc2.getFavoriteItems().add("Wine");

        quest = new Quest(npc2,"Tree",12,"Coin",750,true);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2,"Salmon",1,"friendShip",1,false);
        npc2.getAllQuests().add(quest);

        quest = new Quest(npc2,"Wine",1,"Salad",5,false);
        npc2.getAllQuests().add(quest);

        App.getCurrentGame().getWorld().getNpcs().add(npc2);



        NPC npc3 = new NPC("Chef","liya");

        npc3.getFavoriteItems().add("Salad");
        npc3.getFavoriteItems().add("Coffee");
        npc3.getFavoriteItems().add("Wine");

        quest = new Quest(npc3,"Wood",50,"Coin",500,true);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3,"Salmon",1,"Salmon Dinner Recipe",1,false);
        npc3.getAllQuests().add(quest);

        quest = new Quest(npc3,"Wood",200,"Deluxe Scarecrow",3,false);
        App.getCurrentGame().getWorld().getNpcs().add(npc3);


        NPC npc4 = new NPC("Blacksmith","Robin");

        npc4.getFavoriteItems().add("Spaghetti");
        npc4.getFavoriteItems().add("Wood");
        npc4.getFavoriteItems().add("Iron Bar");

        quest = new Quest(npc4,"Wood",80,"Coin",1000,true);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4,"Iron Bar",10,"Bee House",3,false);
        npc4.getAllQuests().add(quest);

        quest = new Quest(npc4,"Wood",1000,"Coin",25000,false);
        npc4.getAllQuests().add(quest);

        App.getCurrentGame().getWorld().getNpcs().add(npc4);
    }
}
