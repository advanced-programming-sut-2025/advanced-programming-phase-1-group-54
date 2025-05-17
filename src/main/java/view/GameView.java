package view;

import model.App;

import java.util.Scanner;


public class GameView {
    public void run(Scanner scanner) {
        while (App.getCurrentGame() != null)
            App.getCurrentGame().getSubMenu().run(scanner);
    }
}
