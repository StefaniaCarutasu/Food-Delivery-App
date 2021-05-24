import DB.DatabaseService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseService db = DatabaseService.getInstance();
    }
}
