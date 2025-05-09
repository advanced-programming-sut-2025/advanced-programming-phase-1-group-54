package view.app;

import controller.LoginMenuController;
import model.Result;
import model.enums.commands.LoginMenuCommand;
import model.enums.Menu;
import view.GenericMenu;

import java.util.Scanner;

public class LoginMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Login Menu");
    }

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
        LoginMenuCommand command = LoginMenuCommand.FORGET_PASSWORD;
        String username = command.getGroup(input, "username");

        String question = LoginMenuController.getSecurityQuestion(username);
        System.out.println("Please answer your security question:");
        System.out.println(question);

        boolean answerIsCorrect = askForSecurityQuestionAnswer(username, scanner);
        if (answerIsCorrect) {
            getNewPasswordFromUser(username, scanner);
        }
    }

    private boolean askForSecurityQuestionAnswer(String username, Scanner scanner) {
        LoginMenuCommand command = LoginMenuCommand.ANSWER;
        String input;
        do {
            input = scanner.nextLine();
            if (!command.matches(input))
                invalidCommand();
        } while (!command.matches(input));

        String answer = command.getGroup(input, "answer");
        Result result = LoginMenuController.answer(username, answer);
        showResult(result);
        return result.success();
    }

    private void getNewPasswordFromUser(String username, Scanner scanner) {
        System.out.println("Do you want a random generated password?");
        boolean randomPasswordConfirmed = askForConfirmation(scanner);
        if (randomPasswordConfirmed) {
            showResult(LoginMenuController.changePasswordToRandom(username));
        } else {
            String password, confirmPassword;
            System.out.println("Please enter your new password:");
            password = scanner.nextLine();
            System.out.println("Confirm password:");
            confirmPassword = scanner.nextLine();

            showResult(LoginMenuController.changePassword(username, password, confirmPassword));
        }
    }

    private void handleLogin(String input) {
        LoginMenuCommand command = LoginMenuCommand.LOGIN;
        String username = command.getGroup(input, "username");
        String password = command.getGroup(input, "password");
        boolean stayLoggedIn = command.getGroup(input, "stayLoggedIn") != null;

        Result result = LoginMenuController.login(username, password, stayLoggedIn);
        showResult(result);

        if (result.success())
            goToMenu(Menu.MAIN);
    }
}
