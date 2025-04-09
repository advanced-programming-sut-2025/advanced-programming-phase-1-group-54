package view;

import controller.RegisterMenuController;
import model.enums.RegisterMenuCommand;

import java.util.Scanner;

public class RegisterMenu implements AppMenu {
    private final RegisterMenuController controller = new RegisterMenuController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (RegisterMenuCommand.REGISTER.matches(input)) {
            handleRegister(input, scanner);
        } else {
            invalidCommand();
        }
    }

    private void handleRegister(String input, Scanner scanner) {
        // TODO
    }
}
