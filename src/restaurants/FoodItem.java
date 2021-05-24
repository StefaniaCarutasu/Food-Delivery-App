package restaurants;

public class FoodItem extends MenuItem {
    public boolean isSpicy;
    public boolean isVegetarian;
    public boolean isVegan;

    public FoodItem(){
        super();
    }

    public FoodItem(String ID, String name, float price, String ingredients, String allergens, boolean isSpicy, boolean isVegetarian, boolean isVegan){
        super(ID, name, price, ingredients, allergens);
        this.isSpicy = isSpicy;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    public FoodItem(String name, float price, String ingredients, String allergens, boolean isSpicy, boolean isVegetarian, boolean isVegan){
        super(name, price, ingredients, allergens);
        this.isSpicy = isSpicy;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }
}
