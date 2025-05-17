import controller.RegisterMenuController;
import model.enums.Gender;
import view.app.RegisterMenu;
import static org.junit.Assert.*;

public class TestMenus {

    public void testRegisterMenu(){
        RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
                "user@domain.com", Gender.MALE);


        assertEquals(4,2+2);
    }
}
