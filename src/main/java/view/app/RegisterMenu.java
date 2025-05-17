package view.app;

import controller.RegisterMenuController;
import model.Result;
import model.enums.Gender;
import model.enums.commands.RegisterMenuCommand;
import view.GenericMenu;

import java.util.Scanner;

public class RegisterMenu implements AppMenu {
    @Override
    public void showCurrentMenu() {
        System.out.println("Register Menu");
    }

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
        RegisterMenuCommand command = RegisterMenuCommand.REGISTER;
        String username = command.getGroup(input, "username");
        String password = command.getGroup(input, "password");
        String confirmPassword = command.getGroup(input, "confirmPassword");
        String nickname = command.getGroup(input, "nickname");
        String email = command.getGroup(input, "email");
        Gender gender = Gender.getGender(command.getGroup(input, "gender"));

        Result result = RegisterMenuController.register(username, password, confirmPassword, nickname, email, gender);
        showResult(result);

        if (result.code() > 0) {
            boolean confirmed = askForConfirmation(scanner);
            if (!confirmed) {
                showResult(RegisterMenuController.resetUserBuilder());
                return;
            }
        }

        if (result.success()) {
            showSecurityQuestions();
            String question = scanner.nextLine();
            getSecurityQuestionAnswer(question);

            showResult(RegisterMenuController.saveNewUser());
        }
    }

    private void showSecurityQuestions() {
        System.out.println("Choose one of these security questions to answer:");

        int i = 1;
        for (String question : RegisterMenuController.getSecurityQuestions()) {
            System.out.printf("%d- %s\n", i++, question);
        }
    }

    private void getSecurityQuestionAnswer(String input) {
        Result result = new Result(false, null);
        RegisterMenuCommand command = RegisterMenuCommand.PICK_QUESTION;
        do {
//            String input = scanner.nextLine();
            if (!command.matches(input)) {
                invalidCommand();
                continue;
            }

            int number = Integer.parseInt(command.getGroup(input, "number"));
            String answer = command.getGroup(input, "answer");
            String confirmAnswer = command.getGroup(input, "confirmAnswer");

            result = RegisterMenuController.pickQuestion(number, answer, confirmAnswer);
            showResult(result);
        } while (!result.success());
    }
}
