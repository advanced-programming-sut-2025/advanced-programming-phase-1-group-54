package controller;

import model.Result;

import java.util.ArrayList;

public class RegisterMenuController {

    public Result register(String username, String password, String confirmPassword, String nickname, String email, String gender) {
        return null;
    }

    private boolean isUsernameUnique(String username) {
        return false;
    }

    private boolean isUsernameValid(String username) {
        return false;
    }

    private boolean isEmailValid(String email) {
        return false;
    }

    private boolean isPasswordValid(String password) {
        return false;
    }

    private boolean isPasswordWeak(String password) {
        return false;
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
