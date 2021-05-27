package controllers;
import DB.CsvManipulator;
import DB.DatabaseService;
import orders.Order;
import users.User;

import javax.swing.text.html.CSS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersController {
    static DatabaseService db = DatabaseService.getInstance();

    public static User Create(String username, String email, String password, String address, int age) throws Exception {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        User newUser = new User().username(username).email(email).password(password).address(address).age(age);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO users (ID, USERNAME, EMAIL, PASSWORD, ADDRESS, AGE) VALUES (?,?,?,?,?,?)");

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
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        List<User> users = new ArrayList<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, username, email, address, age FROM users");
        while(results.next()){
            users.add(new User(results.getString(1)).username(results.getString(2)).email(results.getString(3)).address(results.getString(4)).age(results.getInt(5)));
        }
        return users;
    }

    public static User FindUserByUserName(String userName) throws Exception {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement st = db.connection.prepareStatement("SELECT ID, USERNAME, EMAIL, ADDRESS FROM USERS WHERE USERNAME = ?");
        st.setString(1, userName);

        ResultSet result = st.executeQuery();
        result.next();
        User searchedUser = new User(result.getString(1)).username(result.getString(2)).email(result.getString(3)).address(result.getString(4));

        return searchedUser;
    }

    public static User FindUserById(String id) throws Exception {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement st = db.connection.prepareStatement("SELECT ID, USERNAME, EMAIL, ADDRESS FROM USERS WHERE ID = ?");
        st.setString(1, id);

        ResultSet result = st.executeQuery();
        result.next();
        User searchedUser = new User(result.getString(1)).username(result.getString(2)).email(result.getString(3)).address(result.getString(4));

        return searchedUser;
    }

    public static boolean Update(String id, String fieldToUpdate, String updatedValue) throws Exception {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement;
        switch (fieldToUpdate){
            case "USERNAME":
                statement = db.connection.prepareStatement("UPDATE USERS SET USERNAME = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "EMAIL":
                statement = db.connection.prepareStatement("UPDATE USERS SET EMAIL = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "PASSWORD":
                statement = db.connection.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "ADDRESS":
                statement = db.connection.prepareStatement("UPDATE USERS SET ADDRESS = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "AGE":
                statement = db.connection.prepareStatement("UPDATE USERS SET AGE = ? WHERE ID LIKE ?");
                statement.setInt(1, Integer.parseInt(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            default:
                return false;
        }


    }


    public boolean Delete(String id) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM users WHERE id like ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

    /*
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
    } */

    public TreeMap<Order, String> ShowOrderHistory(String userId) throws SQLException {
        TreeMap<Order, String> orderHistory = new TreeMap<>();
        PreparedStatement statement = db.connection.prepareStatement("""
                SELECT O.ID, R.NAME, PRICE, PAYMENT_METHOD, DATE
                FROM ORDERS O JOIN RESTAURANTS R on O.RESTAURANT_ID = R.ID
                WHERE USER_ID LIKE ?""");
        statement.setString(1, userId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            orderHistory.put(new Order(resultSet.getString(1)).date(resultSet.getDate(5)).price(resultSet.getDouble(3)).paymentMethod(resultSet.getString(4)), resultSet.getString(2));
        }

        return orderHistory;
    }


}
