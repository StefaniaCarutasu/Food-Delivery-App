package orders;

import restaurants.MenuItem;
import java.util.List;
import java.util.UUID;

public class Orders {
    private String ID;
    protected String Status;
    protected String UserId;
    protected String RestaurantId;
    protected String DriverId;
    protected String PaymentMethod;
    public List<MenuItem> orderedItems;
    protected float totalPrice;

    public Orders(){
        this.ID = UUID.randomUUID().toString();
    }

    public Orders(String status, String userId, String restaurantId, String driverId, String paymentMethod, List<MenuItem> orderedItems) {
        this.ID = UUID.randomUUID().toString();
        Status = status;
        UserId = userId;
        RestaurantId = restaurantId;
        DriverId = driverId;
        PaymentMethod = paymentMethod;
        this.orderedItems = orderedItems;
        this.totalPrice = calculatePrice(orderedItems);
    }

    public Orders(String ID, String status, String userId, String restaurantId, String driverId, String paymentMethod, List<MenuItem> orderedItems) {
        this.ID = ID;
        Status = status;
        UserId = userId;
        RestaurantId = restaurantId;
        DriverId = driverId;
        PaymentMethod = paymentMethod;
        this.orderedItems = orderedItems;
        this.totalPrice = calculatePrice(orderedItems);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public List<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<MenuItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float calculatePrice(List<MenuItem> orderedItems){
        float price = 0;
        for(MenuItem m: orderedItems){
            price += m.getPrice();
        }

        return price;
    }
}
