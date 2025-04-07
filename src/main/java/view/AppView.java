package view;

import model.App;
import model.enums.Menu;

import java.util.Scanner;

public class AppView {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().run(scanner);
        } while (App.getCurrentMenu() != Menu.EXIT);
    }
}
