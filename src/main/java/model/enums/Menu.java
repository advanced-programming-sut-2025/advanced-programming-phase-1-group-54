package model.enums;

import view.*;

import java.util.Scanner;

public enum Menu {
    REGISTER(new RegisterMenu()),
    LOGIN(new LoginMenu()),
    MAIN(new MainMenu()),
    PROFILE(new ProfileMenu()),
    GAME(new GameMenu()),
    EXIT(new ExitMenu());

    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void run(Scanner scanner) {
        menu.run(scanner);
    }

    public boolean reaches(Menu menu) {
        if (this == Menu.REGISTER) {
            return menu == LOGIN;
        } else if (this == Menu.LOGIN) {
            return menu == REGISTER;
        } else if (this == Menu.MAIN) {
            return menu == PROFILE || menu == GAME;
        }
        return false;
    }

    public Menu getParent() {
        if (this == Menu.REGISTER || this == Menu.LOGIN || this == Menu.MAIN) {
            return EXIT;
        } else if (this == Menu.PROFILE || this == Menu.GAME) {
            return MAIN;
        }
        return null;
    }

    public static Menu getMenu(String menuName) {
        if (menuName.equalsIgnoreCase("register")) {
            return REGISTER;
        } else if (menuName.equalsIgnoreCase("login")) {
            return LOGIN;
        } else if (menuName.equalsIgnoreCase("main")) {
            return MAIN;
        } else if (menuName.equalsIgnoreCase("profile")) {
            return PROFILE;
        } else if (menuName.equalsIgnoreCase("game")) {
            return GAME;
        }
        return null;
    }
}
