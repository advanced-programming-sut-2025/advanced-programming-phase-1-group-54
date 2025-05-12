package view.game;

import controller.Game.FriendShipController;
import controller.Game.NPCShopsController;
import controller.GameController;
import model.App;
import model.Result;
import model.alive.Player;
import model.enums.commands.GameCommand;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class DefaultMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (GameCommand.EXIT_GAME.matches(input))
            handleExitGame();
        else if (GameCommand.NEXT_TURN.matches(input))
            handleNextTurn();
        else if (GameCommand.TIME.matches(input))
            handleShowTime();
        else if (GameCommand.DATE.matches(input))
            handleShowDate();
        else if (GameCommand.DATETIME.matches(input))
            handleShowDateTime();
        else if (GameCommand.DAY_OF_THE_WEEK.matches(input))
            handleShowDayOfWeek();
        else if (GameCommand.SEASON.matches(input))
            handleShowSeason();
        else if (GameCommand.WEATHER.matches(input))
            handleShowWeather();
        else if (GameCommand.WEATHER_FORECAST.matches(input))
            handleShowWeatherForecast();
        else if (GameCommand.GREENHOUSE_BUILD.matches(input))
            handleGreenHouseBuild();
        else if (GameCommand.FRIENDSHIP.matches(input))
            friendShips();
        else if(GameCommand.TALK.getMatcher(input) != null)
            talk(GameCommand.TALK.getMatcher(input));
        else if(GameCommand.TALK_HISTORY.getMatcher(input) != null)
            talkHistory(GameCommand.TALK_HISTORY.getMatcher(input));
        else if(GameCommand.GIFT.getMatcher(input) != null)
            gift(GameCommand.GIFT.getMatcher(input));



        /*else if (GameCommand.SHOW_ALL_PRODUCTS.matches(input))
            NPCShopsController.showProducts(shop , false);
        else if (GameCommand.SHOW_ALL_AVAILABLE_PRODUCTS.matches(input))
            NPCShopsController.showProducts(shop , true);
        */
//      todo  else if (GameComman

    }

    private void handleExitGame() {
        showResult(GameController.exitGame());
    }

    private void handleNextTurn() {
        showResult(GameController.nextTurn());
    }

    private void handleShowDate() {
        showResult(GameController.showDate());
    }

    private void handleShowDateTime() {
        showResult(GameController.showTime());
    }

    private void handleShowTime() {
        showResult(GameController.showDateTime());
    }

    private void handleShowDayOfWeek() {
        showResult(GameController.showDayOfWeek());
    }

    private void handleShowSeason() {
        showResult(GameController.showSeason());
    }

    private void handleShowWeather() {
        showResult(GameController.showWeather());
    }

    private void handleShowWeatherForecast() {
        showResult(GameController.showWeatherForecast());
    }

    private void handleGreenHouseBuild() {

    }
    private void friendShips(){
        ArrayList<PlayerRelationship> relationships = FriendShipController.showFriendShip(App.getCurrentGame().getCurrentPlayer());
        for (PlayerRelationship playerRelationship : relationships){
            Player temp = null;
            if(playerRelationship.getPlayer1().equals(App.getCurrentGame().getCurrentPlayer())){
                temp = playerRelationship.getPlayer2();
            }
            else{
                temp = playerRelationship.getPlayer1();
            }
            System.out.println(temp.getName() + "  level: " + playerRelationship.getLevel() + "  Xp:  " + playerRelationship.getXp());
        }
    }
    private void talk(Matcher mathcer){
        String username = mathcer.group("username");
        String message = mathcer.group("message");
        Result result = FriendShipController.talk(username, message);
    }
    private void talkHistory(Matcher mathcer){
        String username = mathcer.group("username");
        if (App.getCurrentGame().getPlayerByUsername(username) == null){
            System.out.println("user not found");
        }
        else{
            ArrayList<String> talkHistory = FriendShipController.talkHistory(username);
            for (String string : talkHistory){
                System.out.println(string);
            }
        }
    }
    private void gift(Matcher mathcer){
        // TODO if (next to each other)
        String username = mathcer.group("username");
        String item = mathcer.group("item");
        String amount = mathcer.group("amount");
        Result result = FriendShipController.gift(username,item,amount);
        System.out.println(result.message());
    }


}
