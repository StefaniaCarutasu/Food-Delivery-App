import DB.DatabaseService;
import controllers.UsersController;
import users.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseService db = DatabaseService.getInstance();

        List<User> users = UsersController.Read();
        System.out.println(users.get(0).getAge());
    }
}
