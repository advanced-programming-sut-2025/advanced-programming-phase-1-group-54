package view;

import model.Result;

import java.util.Scanner;

public interface GenericMenu {
    default void invalidCommand() {
        System.out.println("invalid command");
    }

    void run(Scanner scanner);

    default void showResult(Result result) {
        if (result.message() != null)
            System.out.println(result.message());
    }

    default boolean askForConfirmation(Scanner scanner) {
        String input;
        do {
            System.out.println("[y/n]:");
            input = scanner.nextLine();
        } while (!input.equalsIgnoreCase("y") || !input.equalsIgnoreCase("n"));
        return input.equalsIgnoreCase("y");
    }
}
