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

}
