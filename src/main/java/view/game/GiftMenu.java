package view.game;

import controller.Game.FriendShipController;
import model.App;
import model.Game;
import model.alive.Player;
import model.enums.Menu;
import model.enums.commands.GameCommand;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;

import java.util.Scanner;

public class GiftMenu implements GameSubMenu{
    public void run(Scanner sc){
        String input = sc.nextLine();
        if(GameCommand.GIFT_RATE.getMatcher(input) != null){
            openGifts(input);
        }
        else if(GameCommand.EXIT.getMatcher(input) != null){
            App.setCurrentMenu(Menu.GAME);
        }
    }
    public void openGifts(String rate){
        Gift gift = App.getCurrentGame().getCurrentPlayer().getRecivedGifts().get(0);
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(),gift.getPayer());
        int rateInt = Integer.parseInt(rate);
        relationship.increasXp((rateInt-3) * 30 + 15);
        if( 0 < App.getCurrentGame().getCurrentPlayer().getRecivedGifts().size()){
            Gift gift1 = App.getCurrentGame().getCurrentPlayer().getRecivedGifts().get(0);
            System.out.println(gift.getPayer().getName() + " gave you " + gift.getAmount() + gift.getItemName() + " as gift.");
        }
        else{
            App.setCurrentMenu(Menu.GAME);
        }
    }
}
