package controller;

import model.Result;

public class ProfileMenuController implements MenuController, CheckInputValid {
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

    public Result changeUsername() {
        return null;
    }

    public Result changePassword() {
        return null;
    }

    public Result changeProfile() {
        return null;
    }

    public Result showUserInfo() {
        return null;
    }

    public boolean isUsernameDifferent(String username) {
        return false;
    }

    public boolean isNicknameDifferent(String nickname) {
        return false;
    }

    public boolean isEmailDifferent(String email) {
        return false;
    }

    public boolean isPasswordDifferent(String password) {
        return false;
    }
}
