import DB.DatabaseService;
import controllers.UsersController;
import users.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseService db = DatabaseService.getInstance();
        //List<User> users = UsersController.Read();
        //System.out.println(users.get(0).getAge());

        System.out.println(UsersController.FindUserById("54947df8-0e9e-4471-a2f9-9af509fb5889").getID());
        System.out.println(UsersController.FindUserByUserName("Stefi123").getEmail());

        System.out.println(UsersController.Update("54947df8-0e9e-4471-a2f9-9af509fb5889", "USERNAME", "Stefi123"));
    }
}
