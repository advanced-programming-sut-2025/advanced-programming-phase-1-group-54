package view;

import model.App;
import model.enums.Menu;

import java.util.Scanner;

public interface AppMenu {
    default void invalidCommand() {
        System.out.println("invalid command");
    }

    void run(Scanner scanner);

    default void goToMenu(Menu menu) {
        App.setCurrentMenu(menu);
    }
}
