package model.enums;

import view.game.*;

import java.util.Scanner;

public enum SubMenu {
    DEFAULT(new DefaultMenu()),
    TRADING(new TradingMenu()),
    NPCMenu(new NPCMenu()),
    ;

    private final GameSubMenu menu;

    SubMenu(GameSubMenu menu) {
        this.menu = menu;
    }

    public void run(Scanner scanner) {
        menu.run(scanner);
    }
}
