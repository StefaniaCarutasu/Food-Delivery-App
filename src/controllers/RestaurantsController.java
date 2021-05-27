package controllers;

import DB.CsvManipulator;
import DB.DatabaseService;
import restaurants.MenuItem;
import restaurants.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantsController {
    static DatabaseService db = DatabaseService.getInstance();

    public Restaurant Create(String name, String owner, String phoneNumber, String email, String type, String address) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        Restaurant newRestaurant = new Restaurant().name(name).owner(owner).phone(phoneNumber).email(email).type(type).address(address);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO RESTAURANTS (ID, NAME, EMAIL, PHONE_NUMBER, OWNER, ADDRESS, TYPE) VALUES (?,?,?,?,?,?,?)");

        statement.setString(1, newRestaurant.getID());
        statement.setString(2, newRestaurant.getName());
        statement.setString(3, newRestaurant.getEmail());
        statement.setString(4, newRestaurant.getPhoneNumber());
        statement.setString(5, newRestaurant.getOwnerName());
        statement.setString(6, newRestaurant.getAddress());
        statement.setString(7, newRestaurant.getType());

        if(statement.executeUpdate() == 1){
            return newRestaurant;
        }
        return null;
    }

    public static List<MenuItem> ReadFood(String id) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        List<MenuItem> items = new ArrayList<>();

        PreparedStatement fStatement = db.connection.prepareStatement("SELECT ID, NAME, PRICE, INGREDIENTS FROM FOOD_ITEMS WHERE RESTAURANT_ID LIKE ?");
        fStatement.setString(1, id);

        ResultSet fResults = fStatement.executeQuery();

        while(fResults.next()){
            items.add(new MenuItem(fResults.getString(1)).name(fResults.getString(2)).price(fResults.getDouble(3)).ingredients(fResults.getString(4)));
        }

        return items;

    }

    public static List<MenuItem> ReadDrinks(String id) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        List<MenuItem> items = new ArrayList<>();

        PreparedStatement dStatement = db.connection.prepareStatement("SELECT ID, NAME, PRICE, INGREDIENTS FROM DRINK_ITEMS WHERE RESTAURANT_ID LIKE ?");
        dStatement.setString(1, id);

        ResultSet dResults = dStatement.executeQuery();

        while(dResults.next()){
            items.add(new MenuItem(dResults.getString(1)).name(dResults.getString(2)).price(dResults.getDouble(3)).ingredients(dResults.getString(4)));
        }

        return items;
    }

    public static List<Restaurant> Read() throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        List<Restaurant> restaurantList = new ArrayList<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, NAME, EMAIL, PHONE_NUMBER, ADDRESS, TYPE FROM RESTAURANTS");

        while (results.next()){
            restaurantList.add(new Restaurant(results.getString(1)).name(results.getString(2)).email(results.getString(3)).phone(results.getString(4)).address(results.getString(5)).type(results.getString(6)));
        }

        return restaurantList;
    }


    /*public static TreeMap<Restaurant, List<MenuItem>> Read() throws SQLException {
        TreeMap<Restaurant, List<MenuItem>> restaurants = new TreeMap<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, NAME, EMAIL, PHONE_NUMBER FROM RESTAURANTS");

        while(results.next()){
            Restaurant res = new Restaurant(results.getString(1)).name(results.getString(2)).email(results.getString(3)).phone(results.getString(4));
            restaurants.put(res, ReadItems(res.getID()));
        }
        return restaurants;
    }*/

    public static Restaurant getRestaurantByName(String name) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement st = db.connection.prepareStatement("SELECT ID, NAME, EMAIL, PHONE_NUMBER, ADDRESS, TYPE FROM RESTAURANTS WHERE NAME = ?");
        st.setString(1, name);

        ResultSet result = st.executeQuery();
        result.next();
        Restaurant searchedRestaurant= new Restaurant(result.getString(1)).name(result.getString(2)).email(result.getString(3)).phone(result.getString(4)).address(result.getString(5)).type(result.getString(6));

        return searchedRestaurant;
    }

    public Restaurant getRestaurantById(String id) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement st = db.connection.prepareStatement("SELECT ID, NAME, EMAIL, PHONE_NUMBER, ADDRESS, TYPE WHERE ID = ?");
        st.setString(1, id);

        ResultSet result = st.executeQuery();
        result.next();
        Restaurant searchedRestaurant = new Restaurant(result.getString(1)).name(result.getString(2)).email(result.getString(3)).phone(result.getString(4)).address(result.getString(5)).type(result.getString(6));
        return searchedRestaurant;
    }


    public boolean Update(String id, String fieldToUpdate, String updatedValue) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement;
        switch (fieldToUpdate){
            case "NAME":
                statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET USERNAME = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "EMAIL":
                statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET EMAIL = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "PHONE_NUMBER":
                statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET PHONE_NUMBER = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "ADDRESS":
                statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET ADDRESS = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "TYPE":
                statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET TYPE = ? WHERE ID LIKE ?");
                statement.setInt(1, Integer.parseInt(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            default:
                return false;
        }
    }

    public boolean Delete(String id) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM RESTAURANTS WHERE ID LIKE ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }


    public AbstractMap.SimpleEntry<Restaurant, List<MenuItem>> ShowRestaurant(String id) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        Restaurant restaurant = getRestaurantById(id);
        List<MenuItem> menuItemList = Stream.concat(ReadDrinks(restaurant.getID()).stream(), ReadFood(restaurant.getID()).stream()).collect(Collectors.toList());

        AbstractMap.SimpleEntry<Restaurant, List<MenuItem>> restaurantListSimpleEntry =  new AbstractMap.SimpleEntry<>(restaurant, menuItemList);

        return restaurantListSimpleEntry;
    }
}
