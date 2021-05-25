package restaurants;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.UUID;

public class MenuItem {
    private String ID;
    private String RestaurantId;
    protected String name;
    protected Double price;
    protected String ingredients;       //separate prin virgula
    protected String allergens;         //separate prin virgula

    public MenuItem() {
        this.ID = UUID.randomUUID().toString();
    }

    public MenuItem(String id){
        this.ID = id;
    }

    public MenuItem restaurantId(String id){
        this.RestaurantId = id;
        return this;
    }

    public MenuItem name(String name){
        this.name = name;
        return this;
    }

    public MenuItem price(Double price){
        this.price = price;
        return this;
    }

    public MenuItem ingredients(String ingredients){
        this.ingredients = ingredients;
        return this;
    }

    public MenuItem allergens(String allergens){
        this.allergens = allergens;
        return this;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }
}

class sortMenuItem implements Comparator<MenuItem> {
    public int compare(MenuItem m1, MenuItem m2){
        if(m1.getID().compareTo(m2.getID()) > 0){
            return 1;
        }
        return 0;
    }
}
