package DB;

import java.sql.*;

public class DatabaseService {

    public Connection connection;
    private static DatabaseService instance;

    private DatabaseService() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        this.connection = DriverManager.getConnection("jdbc:derby:E:/transfer/Facultate/Food-Delivery-App/foodDeliveryAppDB;create=true");

        boolean notFoundUsers = true, notFoundDrivers = true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        while (results.next()) {
            if ("users".equalsIgnoreCase(results.getString("TABLE_NAME"))) {
                notFoundUsers = false;
                break;
            }
        }

        if (notFoundUsers) {
            connection.createStatement()
                    .execute("CREATE TABLE users (ID char(36) primary key, username varchar(37) not null, email varchar(30) not null, password varchar(20) not null, address varchar(100) not null, age integer)");
        }


    }

    public static DatabaseService getInstance() {
        if(instance == null) {
            try {
                instance = new DatabaseService();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return instance;
    }

}
