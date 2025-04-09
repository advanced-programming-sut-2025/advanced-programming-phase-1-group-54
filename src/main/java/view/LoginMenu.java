package view;

import controller.LoginMenuController;
import model.enums.LoginMenuCommand;

import java.util.Scanner;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (LoginMenuCommand.LOGIN.matches(input)) {
            handleLogin(input);
        } else if (LoginMenuCommand.FORGET_PASSWORD.matches(input)) {
            handleForgetPassword(input, scanner);
        } else {
            invalidCommand();
        }
    }

    private void handleForgetPassword(String input, Scanner scanner) {
        // TODO
    }

    private void handleLogin(String input) {
        // TODO
    }
}
