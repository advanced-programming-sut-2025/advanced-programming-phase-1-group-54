package view.app;

import controller.ProfileMenuController;
import model.Result;
import model.enums.commands.ProfileMenuCommand;
import view.GenericMenu;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Profile Menu");
    }

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
        ProfileMenuCommand command = ProfileMenuCommand.CHANGE_USERNAME;
        String username = command.getGroup(input, "username");

        Result result = ProfileMenuController.changeUsername(username);
        showResult(result);
    }

    private void handleChangeNickname(String input) {
        ProfileMenuCommand command = ProfileMenuCommand.CHANGE_NICKNAME;
        String nickname = command.getGroup(input, "nickname");

        Result result = ProfileMenuController.changeNickname(nickname);
        showResult(result);
    }

    private void handleChangeEmail(String input) {
        ProfileMenuCommand command = ProfileMenuCommand.CHANGE_EMAIL;
        String email = command.getGroup(input, "email");

        Result result = ProfileMenuController.changeEmail(email);
        showResult(result);
    }

    private void handleChangePassword(String input) {
        ProfileMenuCommand command = ProfileMenuCommand.CHANGE_PASSWORD;
        String oldPassword = command.getGroup(input, "oldPassword");
        String newPassword = command.getGroup(input, "newPassword");

        Result result = ProfileMenuController.changePassword(oldPassword, newPassword);
        showResult(result);
    }

    private void showUserInfo() {
        showResult(ProfileMenuController.showUserInfo());
    }
}
