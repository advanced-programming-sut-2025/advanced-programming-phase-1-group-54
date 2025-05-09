package view.app;

import java.util.Scanner;

public class ExitMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Exit Menu");
    }

    @Override
    public void run(Scanner scanner) {
    }
}
