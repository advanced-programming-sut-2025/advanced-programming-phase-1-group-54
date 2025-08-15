package io.github.stardewmini.client.controllers;

import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.Gender;

import java.util.HashMap;

public class ClientConnectionController {
    public static Result getResultFromResponse(Message message) {
        if (message.getType() != Message.Type.response) {
            return null;
        }
        return new Result(message.getIntFromBody("code"), message.getFromBody("message"));
    }

    public static Message handleCommand(Message message) {
        String command = message.getFromBody("command");
        return null; // TODO
    }

    public static void handleUpdate(Message message) {
        // TODO
    }

    public static Message status() {
        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("command", "status");
        messageBody.put("ip", ClientApp.getIp());
        messageBody.put("port", ClientApp.getPort());
        return new Message(messageBody, Message.Type.response);
    }

    public static Message createLogin(String username, String password, boolean stayLoggedIn) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "login");
        body.put("username", username);
        body.put("password", password);
        body.put("stayLoggedIn", stayLoggedIn);
        return new Message(body, Message.Type.command);
    }

    public static Message createRegister(String username, String password, String confirmPassword,
                                         String nickname, String email, Gender gender) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "register");
        body.put("username", username);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        body.put("nickname", nickname);
        body.put("email", email);
        body.put("gender", gender.toString());
        return new Message(body, Message.Type.command);
    }

    public static Message createPickQuestion(int number, String answer, String confirmAnswer) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "pick_question");
        body.put("number", number);
        body.put("answer", answer);
        body.put("confirmAnswer", confirmAnswer);
        return new Message(body, Message.Type.command);
    }

    public static Message createResetRegister() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "reset_register");
        return new Message(body, Message.Type.command);
    }

    public static Message createForgetPassword(String username) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "forgot_password");
        body.put("username", username);
        return new Message(body, Message.Type.command);
    }

    public static Message createAnswer(String username, String answer) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "answer");
        body.put("username", username);
        body.put("answer", answer);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeForgottenPassword(String username, String password, String confirmPassword) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_forgotten_password");
        body.put("username", username);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeUsername(String username) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_username");
        body.put("username", username);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeEmail(String email) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_email");
        body.put("email", email);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangeNickname(String nickname) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_nickname");
        body.put("nickname", nickname);
        return new Message(body, Message.Type.command);
    }

    public static Message createChangePassword(String newPassword, String oldPassword) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "change_password");
        body.put("newPassword", newPassword);
        body.put("oldPassword", oldPassword);
        return new Message(body, Message.Type.command);
    }

    public static Message createUserInfo() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "user_info");
        return new Message(body, Message.Type.command);
    }
}
