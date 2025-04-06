package model;

import model.enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu;
    private static ArrayList<User> users;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static String SHA256(String string) {
        // TODO
        return string;
    }
}