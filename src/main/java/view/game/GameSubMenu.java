package view.game;

import model.App;
import model.enums.SubMenu;
import view.GenericMenu;

public interface GameSubMenu extends GenericMenu {
    default void goToMenu(SubMenu subMenu) {
        App.getCurrentGame().setSubMenu(subMenu);
    }
}
