package controllers;

import DB.DatabaseService;
import orders.Order;
import org.apache.derby.client.am.DateTime;
import restaurants.MenuItem;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrdersController {
    DatabaseService db = DatabaseService.getInstance();

    public Order Create(String userId, String restaurantId, String driverId, String paymentMethod, List<MenuItem> items) throws SQLException {
        Order newOrder = new Order().userId(userId).restaurantId(restaurantId).driverId(driverId).paymentMethod(paymentMethod).orderedItems(items).totalPrice().status("Active");
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ORDERS (ID, USER_ID, RESTAURANT_ID, DRIVER_ID, PRICE, PAYMENT_METHOD, STATUS, DATE VALUES (?,?,?,?,?,?,?,?)");

        statement.setString(1, newOrder.getID());
        statement.setString(2, newOrder.getUserId());
        statement.setString(3, newOrder.getRestaurantId());
        statement.setString(4, newOrder.getDriverId());
        statement.setDouble(5, newOrder.getTotalPrice());
        statement.setString(6, newOrder.getPaymentMethod());
        statement.setString(7, newOrder.getStatus());
        statement.setDate(8, newOrder.getPlaceDate());

        if(statement.executeUpdate() == 1){

            for(MenuItem m: items){
                String className = m.getClass().getSimpleName();
                if(className.contains("Food")){
                    String id = UUID.randomUUID().toString();
                    PreparedStatement stmt = db.connection.prepareStatement("INSERT INTO ORDER_FOOD_ITEMS (ID, ORDER_ID, ITEM_ID) VALUES (?,?,?)");

                    stmt.setString(1, id);
                    stmt.setString(2, newOrder.getID());
                    stmt.setString(3, m.getID());

                    stmt.executeUpdate();
                }
                else {
                    String id = UUID.randomUUID().toString();
                    PreparedStatement stmt = db.connection.prepareStatement("INSERT INTO ORDER_DRINK_ITEMS (ID, ORDER_ID, ITEM_ID) VALUES (?,?,?)");

                    stmt.setString(1, id);
                    stmt.setString(2, newOrder.getID());
                    stmt.setString(3, m.getID());

                    stmt.executeUpdate();
                }
            }
            return newOrder;
        }
        return null;
    }

    public boolean Update(String id, String fieldToUpdate, String updatedValue) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("UPDATE ORDERS SET ? = ? WHERE id like ?");

        statement.setString(1, fieldToUpdate);
        statement.setString(2, updatedValue);
        statement.setString(3, id);

        return statement.executeUpdate() == 1;
    }

    public boolean Delete(String id) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM ORDERS WHERE id like ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }
}
