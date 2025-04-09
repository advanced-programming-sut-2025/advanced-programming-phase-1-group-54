package view;

import java.util.Scanner;

public class ExitMenu implements AppMenu {
    @Override
    public void run(Scanner scanner) {
        System.out.println("thanks for playing!");
        System.out.println("come again!!");
    }
}
