package view;

import controller.GameMenuController;
import model.Result;
import model.enums.commands.GameMenuCommand;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Game Menu");
    }

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (GameMenuCommand.NEW_GAME.matches(input)) {
            handleNewGame(input, scanner);
        } else if (GameMenuCommand.LOAD_GAME.matches(input)) {
            handleLoadGame(scanner);
        } else {
            invalidCommand();
        }
    }

    private void handleNewGame(String input, Scanner scanner) {
        GameMenuCommand command = GameMenuCommand.NEW_GAME;
        String[] usernames = command.getGroup(input, "usernames").split(" ");

        Result result = GameMenuController.newGame(usernames);
        showResult(result);

        if (result.success()) {
            chooseMaps(scanner);
            showResult(GameMenuController.saveNewGame());
            showResult(GameMenuController.loadGame());
            startGameView(scanner);
        }
    }

    private void chooseMaps(Scanner scanner) {
        System.out.println("Choose your maps:");
        GameMenuCommand command = GameMenuCommand.CHOOSE_MAP;
        for (int i = 0; i < GameMenuController.getNewGameNumberOfPlayers(); i++) {
            Result result = new Result(false, null);
            do {
                String input = scanner.nextLine();
                if (!command.matches(input))
                    continue;

                int number = Integer.parseInt(command.getGroup(input, "number"));
                result = GameMenuController.chooseMap(number);
                showResult(result);
            } while (!result.success());
        }
    }

    private void handleLoadGame(Scanner scanner) {
        Result result = GameMenuController.loadGame();
        showResult(result);
        if (result.success())
            startGameView(scanner);
    }

    private void startGameView(Scanner scanner) {
        (new GameView()).run(scanner);
    }
}
