package view;

import model.enums.Menu;

import java.util.Scanner;

public class StartupMenu implements AppMenu {
    @Override
    public void run(Scanner scanner) {
        System.out.println("Welcome to STARDEW VALLEY !!!");
        goToMenu(Menu.LOGIN);
    }
}
