package io.github.stardewmini.server.controllers;


import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;

import java.util.HashMap;

public class ServerConnectionController {
    public static Message handleCommand(Message message) {
        String command = message.getFromBody("command");
        switch (command) {
            case "register":
                return handleRegister(message);
            case "pick_security_question":
                return handlePickSecurityQuestion(message);
            case "reset_user_builder":
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
                return handleChangeUsername(message);
            case "change_password":
                return handleChangePassword(message);
            case "change_nickname":
                return handleChangeNickname(message);
            case "change_email":
                return handleChangeEmail(message);


            default:
                return null;
        }
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
            message.getFromBody("password"),
            message.getBooleanFromBody("stayLoggedIn")
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

    private static Message handleChangeUsername(Message message) {
        Result result = ProfileMenuController.changeUsername(
            message.getFromBody("username")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangePassword(Message message) {
        Result result = ProfileMenuController.changePassword(
            message.getFromBody("newPassword"),
            message.getFromBody("oldPassword")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeNickname(Message message) {
        Result result = ProfileMenuController.changeNickname(
            message.getFromBody("nickname")
        );
        return makeResponseFrom(result);
    }

    private static Message handleChangeEmail(Message message) {
        Result result = ProfileMenuController.changeEmail(
            message.getFromBody("email")
        );
        return makeResponseFrom(result);
    }

}
