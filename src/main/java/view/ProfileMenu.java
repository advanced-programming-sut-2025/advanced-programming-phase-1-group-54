package view;

import controller.ProfileMenuController;
import model.enums.ProfileMenuCommand;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkMenuCommand(input))
            return;

        if (ProfileMenuCommand.CHANGE_USERNAME.matches(input)) {
            handleChangeUsername(input);
        } else if (ProfileMenuCommand.CHANGE_NICKNAME.matches(input)) {
            handleChangeNickname(input);
        } else if (ProfileMenuCommand.CHANGE_EMAIL.matches(input)) {
            handleChangeEmail(input);
        } else if (ProfileMenuCommand.CHANGE_PASSWORD.matches(input)) {
            handleChangePassword(input);
        } else if (ProfileMenuCommand.USER_INFO.matches(input)) {
            showUserInfo();
        } else {
            invalidCommand();
        }
    }

    private void handleChangeUsername(String input) {
        // TODO
    }

    private void handleChangeNickname(String input) {
        // TODO
    }

    private void handleChangeEmail(String input) {
        // TODO
    }

    private void handleChangePassword(String input) {
        // TODO
    }

    private void showUserInfo() {
        // TODO
    }
}
