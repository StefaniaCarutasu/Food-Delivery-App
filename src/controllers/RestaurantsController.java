package controllers;

import DB.DatabaseService;
import restaurants.MenuItem;
import restaurants.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RestaurantsController {
    DatabaseService db = DatabaseService.getInstance();

    public Restaurant Create(String name, String owner, String phoneNumber, String email, String type, String address) throws SQLException {
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

    public List<MenuItem> ReadItems(String id) throws SQLException {
        List<MenuItem> items = new ArrayList<>();

        PreparedStatement fStatement = db.connection.prepareStatement("SELECT ID, NAME, PRICE, INGREDIENTS FROM FOOD_ITEMS WHERE RESTAURANT_ID LIKE ?");
        fStatement.setString(1, id);

        ResultSet fResults = fStatement.executeQuery();

        while(fResults.next()){
            items.add(new MenuItem(fResults.getString(1)).name(fResults.getString(2)).price(fResults.getDouble(3)).ingredients(fResults.getString(4)));
        }

        PreparedStatement dStatement = db.connection.prepareStatement("SELECT ID, NAME, PRICE, INGREDIENTS FROM DRINK_ITEMS WHERE RESTAURANT_ID LIKE ?");
        fStatement.setString(1, id);

        ResultSet dResults = fStatement.executeQuery();

        while(dResults.next()){
            items.add(new MenuItem(dResults.getString(1)).name(dResults.getString(2)).price(dResults.getDouble(3)).ingredients(dResults.getString(4)));
        }

        return items;
    }

    public TreeMap<Restaurant, List<MenuItem>> Read() throws SQLException {
        TreeMap<Restaurant, List<MenuItem>> restaurants = new TreeMap<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, NAME, EMAIL, PHONE_NUMBER FROM RESTAURANTS");

        while(results.next()){
            Restaurant res = new Restaurant(results.getString(1)).name(results.getString(2)).email(results.getString(3)).phone(results.getString(4));
            restaurants.put(res, ReadItems(res.getID()));
        }

        return restaurants;
    }

    public boolean Update(String id, String fieldToUpdate, String updatedValue) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("UPDATE RESTAURANTS SET ? = ? WHERE ID = ?");
        statement.setString(1, fieldToUpdate);
        statement.setString(2, updatedValue);
        statement.setString(3, id);

        return statement.executeUpdate() == 1;
    }

    public boolean Delete(String id) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM RESTAURANTS WHERE ID LIKE ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

    /*
    public TreeMap<String, List<MenuItem>> ShowRestaurant(String id){
        TreeMap<String, List<MenuItem>> restaurant = new TreeMap<>();


    }*/
}
