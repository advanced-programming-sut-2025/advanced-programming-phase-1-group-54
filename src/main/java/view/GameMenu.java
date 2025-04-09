package view;

import controller.GameMenuController;
import model.enums.GameMenuCommand;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (GameMenuCommand.NEW_GAME.matches(input)) {
            handleNewGame(input, scanner);
        } else if (GameMenuCommand.LOAD_GAME.matches(input)) {
            checkLoadGame(scanner);
        } else {
            invalidCommand();
        }
    }

    private void handleNewGame(String input, Scanner scanner) {
        // TODO
    }

    private void checkLoadGame(Scanner scanner) {
        // TODO
    }

    private void startGameView(Scanner scanner) {
        (new GameView()).run(scanner);
    }
}
