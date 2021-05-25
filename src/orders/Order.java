package orders;

import restaurants.MenuItem;

import javax.swing.plaf.DimensionUIResource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private String ID;
    protected String Status;
    protected String UserId;
    protected String RestaurantId;
    protected String DriverId;
    protected String PaymentMethod;
    protected java.sql.Date placeDate;
    public List<MenuItem> orderedItems;
    protected Double totalPrice = 0.0;

    public Order(){
        this.ID = UUID.randomUUID().toString();
        long millis=System.currentTimeMillis();
        this.placeDate = new java.sql.Date(millis);
    }

    public Order(String id){
        this.ID = id;
    }
    public Order status(String status){
        this.Status = status;
        return this;
    }
    public Order userId(String userId){
        this.UserId = userId;
        return this;
    }
    public Order restaurantId(String restaurantId){
        this.RestaurantId = restaurantId;
        return this;
    }
    public Order driverId(String driverId){
        this.DriverId = driverId;
        return this;
    }
    public Order paymentMethod(String paymentMethod){
        this.PaymentMethod = paymentMethod;
        return this;
    }
    public Order orderedItems(List<MenuItem> items){
        this.orderedItems = items;
        return this;
    }
    public Order totalPrice(){
        for(MenuItem item: this.orderedItems){
            totalPrice += item.getPrice();
        }
        return this;
    }

    public Order price(Double price){
        this.totalPrice = price;
        return this;
    }

    public Order date(java.sql.Date date){
        this.placeDate = date;
        return this;
    }

    public java.sql.Date getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(java.sql.Date placeDate) {
        this.placeDate = placeDate;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
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
