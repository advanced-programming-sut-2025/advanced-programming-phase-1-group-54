package controller.builders;

import model.User;
import model.enums.Gender;
import org.apache.commons.codec.digest.DigestUtils;

public class UserBuilder {
    private static String username;
    private static String password;
    private static String nickname;
    private static String email;
    private static Gender gender;

    private static int chosenSecurityQuestionNumber;
    private static String answerToSecurityQuestion;

    public static void reset() {
        username = null;
        password = null;
        nickname = null;
        email = null;
        gender = null;
        chosenSecurityQuestionNumber = -1;
        answerToSecurityQuestion = null;
    }

    public static void registerBasicData(String username, String password, String nickname, String email, Gender gender) {
        UserBuilder.username = username;
        UserBuilder.password = password;
        UserBuilder.nickname = nickname;
        UserBuilder.email = email;
        UserBuilder.gender = gender;
    }

    public static void registerSecurityQuestionAnswer(int number, String answer) {
        UserBuilder.chosenSecurityQuestionNumber = number;
        UserBuilder.answerToSecurityQuestion = answer;
    }

    public static User getUser() {
        User user = new User(username, hash(password), nickname, email, gender, chosenSecurityQuestionNumber, hash(answerToSecurityQuestion));
        UserBuilder.reset();
        return user;
    }

    public static String hash(String string) {
        return DigestUtils.sha256Hex(string);
    }
}
