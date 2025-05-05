package view;

import controller.GameController;
import model.App;
import model.enums.commands.GameCommand;

import java.util.Scanner;


public class GameView {
    public void run(Scanner scanner) {
        while (App.getCurrentGame() != null)
            App.getCurrentGame().getSubMenu().run(scanner);
    }
}
