package controllers;

import DB.DatabaseService;
import restaurants.FoodItem;
import restaurants.MenuItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuItemController {
    DatabaseService db = DatabaseService.getInstance();

    public FoodItem Create(String name, String restaurantId, Double price, String ingredients, String allergens, Boolean isSpicy, Boolean isVegan, Boolean isVegetarian) throws SQLException {
        FoodItem newItem = new FoodItem().isSpicy(isSpicy).isVegetarian(isVegetarian).isVegan(isVegan);
        newItem.name(name).restaurantId(restaurantId).price(price).ingredients(ingredients).allergens(allergens);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO FOOD_ITEMS (ID, PRICE, INGREDIENTS, ALLERGENS, SPICY, VEGAN, VEGETARIAN) VALUES (?,?,?,?,?,?,?)");
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

}
