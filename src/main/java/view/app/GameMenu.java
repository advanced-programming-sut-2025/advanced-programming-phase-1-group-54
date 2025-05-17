package view.app;

import controller.GameMenuController;
import model.Result;
import model.enums.commands.GameMenuCommand;
import view.GameView;

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

        Result result = GameMenuController.selectNewGameUsers(usernames);
        showResult(result);

        if (result.success()) {
            chooseMaps(scanner);
            showResult(GameMenuController.createNewGame());
            showResult(GameMenuController.loadGame());
            startGameView(scanner);
        }
    }

    private void chooseMaps(Scanner scanner) {
        System.out.println("Choose your maps:");
        GameMenuCommand command = GameMenuCommand.CHOOSE_MAP;
        Result result;
        do {
            do {
                result = new Result(false, null);
                String input = scanner.nextLine();
                if (!command.matches(input)) {
                    invalidCommand();
                    continue;
                }
                int number = Integer.parseInt(command.getGroup(input, "number"));
                result = GameMenuController.chooseNewGameMap(number);
                showResult(result);
            } while (!result.success());
        } while (result.code() != 1);
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
