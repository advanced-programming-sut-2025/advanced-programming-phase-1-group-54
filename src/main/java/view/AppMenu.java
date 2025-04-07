package view;

import java.util.Scanner;

public interface AppMenu {
    default void invalidCommand() {
        System.out.println("invalid command");
    }

    void run(Scanner scanner);
}
