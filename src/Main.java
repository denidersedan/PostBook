import controllers.*;
import database.User;
import models.LogInModel;
import models.MainModel;
import views.LogInView;
import views.MainView;

public class Main {
    public static void main(String[] args) {
//        LogInModel model = new LogInModel();
//        LogInView view = new LogInView();
//        LogInController logInController = new LogInController(model, view);
        User account = new User("deni", "password", "Deni Dersedan");
        MainView view = new MainView(account);
        MainController ctrl = new MainController(account, view);
    }
}