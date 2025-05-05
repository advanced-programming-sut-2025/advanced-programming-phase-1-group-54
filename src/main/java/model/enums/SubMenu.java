package model.enums;

import view.game.DefaultMenu;
import view.game.GameSubMenu;
import view.game.NPCHouseMenu;

import java.util.Scanner;

public enum SubMenu {
    DEFAULT(new DefaultMenu()),
    ;

    private final GameSubMenu menu;

    SubMenu(GameSubMenu menu) {
        this.menu = menu;
    }

    public void run(Scanner scanner) {
        menu.run(scanner);
    }
}
