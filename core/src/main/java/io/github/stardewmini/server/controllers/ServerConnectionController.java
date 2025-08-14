package io.github.stardewmini.server.controllers;


import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;

import java.util.HashMap;

public class ServerConnectionController {
    public static Message handleCommand(String username, Message message) {
        String command = message.getFromBody("command");
        switch (command) {
            case "register":
                return handleRegister(message);
            case "pick_question":
                return handlePickSecurityQuestion(message);
            case "reset_register":
                return handleResetUserBuilder();

            case "login":
                return handleLogin(message);
            case "forgot_password":
                return handleForgotPassword(message);
            case "answer":
                return handleAnswer(message);
            case "change_forgotten_password":
                return handleChangeForgottenPassword(message);

            case "change_username":
                return handleChangeUsername(username, message);
            case "change_password":
                return handleChangePassword(username, message);
            case "change_nickname":
                return handleChangeNickname(username, message);
            case "change_email":
                return handleChangeEmail(username, message);
            case "user_info":
                return handleUserInfo(username, message);

            default:
                return GameCommandController.handleCommand(username, message);
        }
    }

    public static void handleUpdate(String username, Message message) {
        // TODO
    }

    private static Message makeResponseFrom(Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("code", result.code());
        body.put("message", result.message());
        return new Message(body, Message.Type.response);
    }

    private static Message handleRegister(Message message) {
        Result result = RegisterMenuController.register(
            message.getFromBody("username"),
            message.getFromBody("password"),
            message.getFromBody("confirmPassword"),
            message.getFromBody("nickname"),
            message.getFromBody("email"),
            message.getFromBody("gender")
        );
        return makeResponseFrom(result);
    }

    private static Message handlePickSecurityQuestion(Message message) {
        Result result = RegisterMenuController.pickQuestion(
            message.getIntFromBody("number"),
            message.getFromBody("answer"),
            message.getFromBody("confirmAnswer")
        );
        return makeResponseFrom(result);
    }

    private static Message handleResetUserBuilder() {
        Result result = RegisterMenuController.resetUserBuilder();
        return makeResponseFrom(result);
    }

    private static Message handleLogin(Message message) {
        Result result = LoginMenuController.login(
            message.getFromBody("username"),
            message.getFromBody("password")
        );
        return makeResponseFrom(result);
    }

    private static Message handleForgotPassword(Message message) {
        Result result = LoginMenuController.getSecurityQuestion(
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleAnswer(Message message) {
        Result result = LoginMenuController.answer(
            message.getFromBody("username"),
            message.getFromBody("answer")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeForgottenPassword(Message message) {
        Result result = LoginMenuController.changePassword(
            message.getFromBody("username"),
            message.getFromBody("password"),
            message.getFromBody("confirmPassword")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeUsername(String username, Message message) {
        Result result = ProfileMenuController.changeUsername(
            username,
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangePassword(String username, Message message) {
        Result result = ProfileMenuController.changePassword(
            username,
            message.getFromBody("newPassword"),
            message.getFromBody("oldPassword")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeNickname(String username, Message message) {
        Result result = ProfileMenuController.changeNickname(
            username,
            message.getFromBody("nickname")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeEmail(String username, Message message) {
        Result result = ProfileMenuController.changeEmail(
            username,
            message.getFromBody("email")
        );
        return makeResponseFrom(result);
    }

    private static Message handleUserInfo(String username, Message message) {
        Result result = ProfileMenuController.showUserInfo(username);
        return makeResponseFrom(result);
    }
}
