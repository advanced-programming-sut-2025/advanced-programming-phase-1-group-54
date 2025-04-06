package model;

import model.enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu;
    private final static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

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

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}