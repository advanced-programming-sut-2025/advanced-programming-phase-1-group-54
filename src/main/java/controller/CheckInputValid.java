package controller;

public interface CheckInputValid {
    default boolean isUsernameUnique(String username) {
        return false;
    }

    default boolean isUsernameValid(String username) {
        return false;
    }

    default boolean isEmailValid(String email) {
        return false;
    }

    default boolean isPasswordValid(String password) {
        return false;
    }

    default boolean isPasswordWeak(String password) {
        return false;
    }
}
