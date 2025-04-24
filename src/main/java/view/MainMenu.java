package view;

import controller.MainMenuController;
import model.Result;
import model.enums.commands.MainMenuCommand;
import model.enums.Menu;

import java.util.Scanner;

public class MainMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Main menu");
    }

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (MainMenuCommand.LOGOUT.matches(input)) {
            handleLogout(input);
        } else {
            invalidCommand();
        }
    }

    private void handleLogout(String input) {
        Result result = MainMenuController.logout();
        showResult(result);
        if (result.success())
            goToMenu(Menu.LOGIN);
    }
}
