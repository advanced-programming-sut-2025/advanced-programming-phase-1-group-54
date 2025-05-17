import controller.LoginMenuController;
import controller.ProfileMenuController;
import controller.RegisterMenuController;
import model.Result;
import model.enums.Gender;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMenus {
    @Test
    public void testRegisterMenu(){

        Result result;

        result = RegisterMenuController.register("yasin","Yasin$60","Yasin$60",
                "Suiii","user@domain..com",Gender.MALE);
        assertEquals("Email is invalid!",result.message());

        result = RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
                "user@domain.com", Gender.MALE);

        assertEquals("Successfully registered data!", result.message());

        result =  RegisterMenuController.pickQuestion(1,"cow","cow");
        assertEquals("Security question answered", result.message());

        result = RegisterMenuController.saveNewUser();
        assertEquals("Successfully registered!",result.message());


        result = RegisterMenuController.register("yas","Yasin$60","Yasin$60","Sui",
                "user@domain.com", Gender.MALE);

        assertEquals("Successfully registered data!", result.message());

        result =  RegisterMenuController.pickQuestion(1,"cow","cow");
        assertEquals("Security question answered", result.message());

        result = RegisterMenuController.saveNewUser();
        assertEquals("Successfully registered!",result.message());
    }

    @Test
    public void testLoginMenu(){
        Result result;

//        result = RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
//                "user@domain.com", Gender.MALE);
//
//        assertEquals("Successfully registered data!", result.message());
//
//        result =  RegisterMenuController.pickQuestion(1,"cow","cow");
//        assertEquals("Security question answered", result.message());
//
//        result = RegisterMenuController.saveNewUser();
//        assertEquals("Successfully registered!",result.message());


        result = LoginMenuController.login("yaaaaasin","Yasin%60",false);
        assertEquals("User not found", result.message());

        result = LoginMenuController.login("yas","Yaaaaaasin%60",true);
        assertEquals("Incorrect password", result.message());

        result = LoginMenuController.login("yas","Yasin$60",false);
        assertEquals("Successfully logged in", result.message());

    }

    @Test
    public void testProfileMenu(){

        Result result;

//        result = RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
//                "user@domain.com", Gender.MALE);
//
//        assertEquals("Successfully registered data!", result.message());
//
//        result =  RegisterMenuController.pickQuestion(1,"cow","cow");
//        assertEquals("Security question answered", result.message());
//
//        result = RegisterMenuController.saveNewUser();
//        assertEquals("Successfully registered!",result.message());


        result = LoginMenuController.login("yasin","Yasin$60",false);
        assertEquals("Successfully logged in", result.message());

        result = ProfileMenuController.changeUsername("yasin");
        assertEquals("Please enter a new username", result.message());

        result = ProfileMenuController.changeUsername("mammad");
        assertEquals("Username changed successfully.", result.message());

        result = ProfileMenuController.changePassword("salam","Yyyasin$60");
        assertEquals("Old Password does not match", result.message());

        result = ProfileMenuController.changePassword("Salam$60","Yasin$60");
        assertEquals("Password changed successfully.", result.message());

    }

}
