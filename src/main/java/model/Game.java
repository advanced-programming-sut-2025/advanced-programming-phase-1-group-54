package model;

import controller.Game.CommonGameController;
import controller.Game.FriendShipController;
import controller.Game.NPCShopsController;
import model.Shops.Shop;
import model.alive.Player;
import model.enums.SubMenu;
import model.enums.Weather;
import model.map.World;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;

public class Game implements DailyUpdate, HourUpdate {
    private SubMenu subMenu = SubMenu.DEFAULT;

    private final World world;
    private final Player[] players;

    private final DateTime dateTime = new DateTime();
    private int turn;

    private ArrayList<Shop> npcShops;

    private ArrayList<PlayerRelationship> playerRelationships;

    public Game(World world, Player[] players) {
        this.world = world;
        this.players = players;
        this.playerRelationships = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            for (int j = i+1; j < players.length; j++) {
                playerRelationships.add(new PlayerRelationship(players[i], players[j]));
            }
        }
    }
    public ArrayList<PlayerRelationship> getPlayerRelationships() {
        return playerRelationships;
    }
    public void setPlayerRelationships(ArrayList<PlayerRelationship> playerRelationships) {
        this.playerRelationships = playerRelationships;
    }
    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public World getWorld() {
        return world;
    }

    public Player[] getPlayers() {
        return players;
    }

    public ArrayList<Shop> getNpcShops() {
        return npcShops;
    }

    public void setNpcShops(ArrayList<Shop> npcShops) {
        this.npcShops = npcShops;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return players[turn];
    }

    @Override
    public void nextDayUpdate() {
        world.nextDayUpdate();
        world.setTomorrowWeather(Weather.getRandom(dateTime.getSeason()));

        for (Player player : players) {
            player.nextDayUpdate();
        }

        for (PlayerRelationship relationship : playerRelationships) {
            relationship.reset();
        }

        dateTime.increaseDay(1);
        //FriendShipController.relaitionshipUpdate();
        FriendShipController.decreaseHeartBropken();
        NPCShopsController.refillShps();
        CommonGameController.nextDayMoney();
        //TODO in every turn check the gifts trades etc
        //TODO fill the shops
    }

    @Override
    public void nextHourUpdate() {
        dateTime.increaseHour(1);
        if (dateTime.getHour() == DateTime.getStartHour()) {
            nextDayUpdate();
        }
    }

    public void nextTurn() {
        do {
            turn++;
            if (turn >= players.length) {
                turn = 0;
                nextHourUpdate();
            }
        } while (players[turn].isFallen());

        Player player = App.getCurrentGame().getCurrentPlayer();
        if (!player.getReceivedTrades().isEmpty()){
            System.out.println("you have some trade to do");
        }
        if(!player.getReceivedGifts().isEmpty()){
            System.out.println("you have some gift to open");
        }
        if (!(player.getReceivedRequests()).isEmpty()){
            System.out.println("you have some marriage request");
        }
    }

    public Weather getCurrentWeather() {
        return world.getCurrentWeather();
    }

    public Weather getTomorrowWeather() {
        return world.getTomorrowWeather();
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if(player.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }
}
