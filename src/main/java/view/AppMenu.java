package view;

import model.App;
import model.Result;
import model.enums.Menu;
import model.enums.commands.MenuCommand;

import java.util.Scanner;

public interface AppMenu {
    default void invalidCommand() {
        System.out.println("invalid command");
    }

    void run(Scanner scanner);

    void showCurrentMenu();

    default boolean checkMenuCommand(String input) {
        if (MenuCommand.ENTER_MENU.matches(input)) {
            handleEnterMenu(input);
            return true;
        } else if (MenuCommand.EXIT_MENU.matches(input)) {
            goToMenu(App.getCurrentMenu().getParent());
            return true;
        } else if (MenuCommand.SHOW_CURRENT_MENU.matches(input)) {
            showCurrentMenu();
            return true;
        }

        return false;
    }

    private void handleEnterMenu(String input) {
        String menuName = MenuCommand.ENTER_MENU.getGroup(input, "menuName");
        Menu menu = Menu.getMenu(menuName);

        if (menu == null) {
            System.out.println("invalid menu name");
        } else if (App.getCurrentMenu() == menu) {
            System.out.printf("you are already in %s menu\n", menuName);
        } else if (App.getCurrentMenu().reaches(menu)) {
            goToMenu(menu);
        } else {
            System.out.printf("%s is not reachable from here\n", menuName);
        }
    }

    default void goToMenu(Menu menu) {
        App.setCurrentMenu(menu);
        if (menu != Menu.EXIT)
            System.out.printf("you are now in %s menu\n", menu.toString().toLowerCase());
    }

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
