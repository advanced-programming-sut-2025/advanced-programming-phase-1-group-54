import controller.RegisterMenuController;
import model.enums.Gender;
import view.AppView;

public class Main {
    public static void main(String[] args) {
         (new AppView()).run();
//        System.out.println(RegisterMenuController.register("yasin","Yasin$60","Yasin$60","Sui",
//                "user@domain.com", Gender.MALE).message());
    }
}
