package controller;

import model.Result;

public interface MenuController {
    Result enterMenu(String menuName);

    Result exitMenu();

    Result showCurrentMenu();
}
