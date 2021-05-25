package controllers;
import DB.DatabaseService;
import users.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersController {
    static DatabaseService db = DatabaseService.getInstance();

    public User Create(String username, String email, String password, String address) throws Exception {
        User newUser = new User().username(username).email(email).password(password).address(address);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO users (ID, USERNAME, EMAIL, PASSWORD, ADDRESS, AGE) VALUES (?,?,?,?,?)");

        statement.setString(1, newUser.getID());
        statement.setString(2, newUser.getUsername());
        statement.setString(3, newUser.getEmail());
        statement.setString(4, newUser.getPassword());
        statement.setString(5, newUser.getAddress());
        statement.setInt(6, newUser.getAge());

        if(statement.executeUpdate() == 1){
            return newUser;
        }
        return null;
    }

    public static List<User> Read() throws Exception {
        List<User> users = new ArrayList<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, username, email, address, age FROM users");
        while(results.next()){
            users.add(new User(results.getString(1)).username(results.getString(2)).email(results.getString(3)).address(results.getString(4)).age(results.getInt(5)));
        }
        return users;
    }

    public User FindUserByUserName(String userName) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT ID, USERNAME, EMAIL, ADDRESS FROM USERS WHERE USERNAME LIKE ?");
        statement.setString(1, userName);
        ResultSet result = statement.executeQuery();

        User searchedUser = new User(result.getString(1)).username(result.getString(2)).email(result.getString(3)).address(result.getString(4));

        return searchedUser;
    }

    public boolean Update(String id, String fieldToUpdate, String updatedValue) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("UPDATE users SET ? = ? WHERE id = ?");

        statement.setString(1, fieldToUpdate);
        statement.setString(2, updatedValue);
        statement.setString(3, id);

        return statement.executeUpdate() == 1;
    }

    public boolean Delete(String id) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

    public TreeMap<String, List<String>> showOrderHistory(String id) throws SQLException {
        PreparedStatement fStatement = db.connection.prepareStatement("""
                SELECT O.ID, O.DATE, R.NAME, FI.NAME, FI.PRICE
                FROM USERS JOIN ORDERS O on USERS.ID = O.USER_ID
                           JOIN RESTAURANTS R ON O.RESTAURANT_ID = R.ID
                           JOIN ORDER_FOOD_ITEMS OFI on O.ID = OFI.ORDER_ID
                           JOIN FOOD_ITEMS FI on FI.ID = OFI.ITEM_ID
                WHERE O.USER_ID like ?""");

        fStatement.setString(1, id);

        ResultSet results = fStatement.executeQuery();

        TreeMap<String, List<String>> orders = new TreeMap<>();

        while(results.next()){
            if(orders.containsKey(results.getString(2))){
                orders.get(results.getString(2)).add(results.getString(3));
                orders.get(results.getString(2)).add(results.getString(4));
                orders.get(results.getString(2)).add(Double.toString(results.getDouble(5)));
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(results.getString(3));
                l.add(results.getString(4));
                l.add(Double.toString(results.getDouble(5)));

                orders.put(results.getString(2), l);
            }
        }

        PreparedStatement dStatement = db.connection.prepareStatement("""
                SELECT O.ID, O.DATE, R.NAME, FI.NAME, FI.PRICE
                FROM USERS JOIN ORDERS O on USERS.ID = O.USER_ID
                           JOIN RESTAURANTS R ON O.RESTAURANT_ID = R.ID
                           JOIN ORDER_DRINK_ITEMS ODI on O.ID = ODI.ORDER_ID
                           JOIN DRINK_ITEMS DI on DI.ID = ODI.ITEM_ID
                WHERE O.USER_ID like ?""");

        dStatement.setString(1, id);

        ResultSet dResults = fStatement.executeQuery();

        while(dResults.next()){
            if(orders.containsKey(dResults.getString(2))){
                orders.get(dResults.getString(2)).add(dResults.getString(3));
                orders.get(dResults.getString(2)).add(dResults.getString(4));
                orders.get(dResults.getString(2)).add(Double.toString(dResults.getDouble(5)));
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(dResults.getString(3));
                l.add(dResults.getString(4));
                l.add(Double.toString(dResults.getDouble(5)));

                orders.put(dResults.getString(2), l);
            }
        }

        return orders;
    }


}
