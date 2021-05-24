package restaurants;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.UUID;

public class MenuItem {
    private String ID;
    protected String name;
    protected Double price;
    protected String ingredients;       //separate prin virgula
    protected String allergens;         //separate prin virgula

    public MenuItem() {
        this.ID = UUID.randomUUID().toString();
    }

    public MenuItem(String ID, String name, Double price, String ingredients, String allergens) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public MenuItem(String name, Double price, String ingredients, String allergens) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.allergens = allergens;
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
