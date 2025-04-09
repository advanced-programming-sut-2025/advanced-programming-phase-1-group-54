package view;

import controller.MainMenuController;
import model.enums.MainMenuCommand;
import model.enums.Menu;

import java.util.Scanner;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();

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
        // TODO
        goToMenu(Menu.LOGIN);
    }
}
