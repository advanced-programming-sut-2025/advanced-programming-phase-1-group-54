import controller.LoginMenuController;
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
    }

    @Test
    public void testLoginMenu(){
        Result result;

        result = RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
                "user@domain.com", Gender.MALE);

        assertEquals("Successfully registered data!", result.message());

        result =  RegisterMenuController.pickQuestion(1,"cow","cow");
        assertEquals("Security question answered", result.message());

        result = RegisterMenuController.saveNewUser();
        assertEquals("Successfully registered!",result.message());

//        result = LoginMenuController

//        result =
    }

    @Test
    public void testProfileMenu(){

        assertEquals(3, 1 + 2);
        assertEquals(4,2+2);
    }

}
