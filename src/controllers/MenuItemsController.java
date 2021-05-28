package controllers;

import DB.CsvManipulator;
import DB.DatabaseService;
import restaurants.DrinkItem;
import restaurants.FoodItem;
import restaurants.MenuItem;
import users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuItemsController {
    static DatabaseService db = DatabaseService.getInstance();

    public FoodItem CreateFoodItem(String name, String restaurantId, Double price, String ingredients, String allergens, Boolean isSpicy, Boolean isVegan, Boolean isVegetarian) throws SQLException {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        FoodItem newItem = new FoodItem().isSpicy(isSpicy).isVegetarian(isVegetarian).isVegan(isVegan);
        newItem.name(name).restaurantId(restaurantId).price(price).ingredients(ingredients).allergens(allergens);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO FOOD_ITEMS (ID, NAME, PRICE, INGREDIENTS, ALLERGENS, SPICY, VEGAN, VEGETARIAN) VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, newItem.getID());
        statement.setDouble(2, newItem.getPrice());
        statement.setString(3, newItem.getIngredients());
        statement.setString(4, newItem.getAllergens());
        statement.setBoolean(5, newItem.isSpicy());
        statement.setBoolean(6, newItem.isVegan());
        statement.setBoolean(7, newItem.isVegetarian());

        if(statement.executeUpdate() == 1){
            return  newItem;
        }
        return null;
    }

    public DrinkItem CreateDrinkItem(String name, String restaurantId, Double price, String ingredients, String allergens, Boolean isAlcoholic) throws SQLException {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        DrinkItem newItem = new DrinkItem().isAlcoholic(isAlcoholic);
        newItem.name(name).restaurantId(restaurantId).price(price).ingredients(ingredients).allergens(allergens);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO FOOD_ITEMS (ID, NAME, PRICE, INGREDIENTS, ALLERGENS, ALCOHOLIC) VALUES (?,?,?,?,?,?)");
        statement.setString(1, newItem.getID());
        statement.setDouble(2, newItem.getPrice());
        statement.setString(3, newItem.getIngredients());
        statement.setString(4, newItem.getAllergens());
        statement.setBoolean(5, newItem.isAlcoholic());

        if(statement.executeUpdate() == 1){
            return  newItem;
        }

        return null;
    }

    public List<MenuItem> ReadItem(String id) throws SQLException {

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

        PreparedStatement dStatement = db.connection.prepareStatement("SELECT ID, NAME, PRICE, INGREDIENTS FROM DRINK_ITEMS WHERE RESTAURANT_ID LIKE ?");
        fStatement.setString(1, id);

        ResultSet dResults = fStatement.executeQuery();

        while(dResults.next()){
            items.add(new MenuItem(dResults.getString(1)).name(dResults.getString(2)).price(dResults.getDouble(3)).ingredients(dResults.getString(4)));
        }

        return items;
    }

    public static boolean Update(String id, String fieldToUpdate, String updatedValue, String type) throws Exception {

        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement;
        switch (fieldToUpdate){
            case "NAME":
                if(type.equals("FOOD")){
                    statement = db.connection.prepareStatement("UPDATE FOOD_ITEMS SET NAME = ? WHERE ID LIKE ?");
                }
                else {
                    statement = db.connection.prepareStatement("UPDATE DRINK_ITEMS SET NAME = ? WHERE ID LIKE ?");
                }
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "PRICE":
                if(type.equals("FOOD")){
                    statement = db.connection.prepareStatement("UPDATE FOOD_ITEMS SET PRICE = ? WHERE ID LIKE ?");
                }
                else {
                    statement = db.connection.prepareStatement("UPDATE DRINK_ITEMS SET PRICE = ? WHERE ID LIKE ?");
                }
                statement.setDouble(1, Integer.parseInt(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            default:
                return false;
        }


    }

    public boolean Delete(String id, String type) throws Exception {
        PreparedStatement statement;
        if(type.equals("FOOD")){
            statement = db.connection.prepareStatement("DELETE FROM FOOD_ITEMS WHERE id like ?");
        }
        else {
            statement = db.connection.prepareStatement("DELETE FROM DRINK_ITEMS WHERE id like ?");
        }


        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

}
