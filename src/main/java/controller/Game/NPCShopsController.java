package controller.Game;

import model.Shops.*;
import model.alive.Human;

public class NPCShopsController {
    static {
        //creating blacksmith shop
        Human human = new Human();
        human.setName("Clint");
        BlackSmithShop blackSmithShop = new BlackSmithShop(human);


        //creating JojoMart shop
        Human human1 = new Human();
        human1.setName("Morris");
        JojoMartShop jojoMartShop = new JojoMartShop(human);

        //creating Pierre General Shop
        Human human2 = new Human();
        human2.setName("Pierre");
        PierreGeneralShop pierreGeneralShop = new PierreGeneralShop(human);

        //creating Carpenter’s Shop
        Human human3 = new Human();
        human3.setName("Robin");
        CarpenterShop carpenterShop = new CarpenterShop(human);

        //creating Fish Shop
        Human human4 = new Human();
        human4.setName("Willy");
        FishShop fishShop = new FishShop(human);

        //crating Marnie’s Ranch
        Human human5 = new Human();
        human5.setName("Marnie");
        MarnieRanch marnieRanch = new MarnieRanch(human);

        //creating :The Stardrop Saloon
        Human human6 = new Human();
        human6.setName("Gus");
        TheStardropSaloonShop theStardropSaloonShop = new TheStardropSaloonShop(human);


    }
    public void purchaseItem(Shop shop,String itemName) {

    }
}
