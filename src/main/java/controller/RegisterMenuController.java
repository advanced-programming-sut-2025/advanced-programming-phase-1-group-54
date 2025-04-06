package controller;

import model.Result;

import java.util.ArrayList;

public class RegisterMenuController implements MenuController, CheckInputValid {
    @Override
    public Result enterMenu(String menuName) {
        return null;
    }

    @Override
    public Result exitMenu() {
        return null;
    }

    @Override
    public Result showCurrentMenu() {
        return null;
    }

    public Result register(String username, String password, String confirmPassword, String nickname, String email, String gender) {
        return null;
    }

    private String generatePassword() {
        return null;
    }

    public ArrayList<String> getSecurityQuestions() {
        return null;
    }

    public Result pickQuestion(int number, String answer, String confirmAnswer) {
        return null;
    }
}
