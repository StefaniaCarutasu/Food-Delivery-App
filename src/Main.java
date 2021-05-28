import DB.DatabaseService;
import controllers.DriversController;
import controllers.RestaurantsController;
import controllers.UsersController;
import restaurants.Restaurant;
import users.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseService db = DatabaseService.getInstance();

        //UsersController.Create("user", "user@email.ro", "12345", "Adresa user", 20);
        DriversController.Create("driver4", "driver4@email.ro", "12334", "Car", "B99PAO");
    }
}
