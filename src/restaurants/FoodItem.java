package restaurants;

public class FoodItem extends MenuItem {
    public Boolean isSpicy;
    public Boolean isVegetarian;
    public Boolean isVegan;

    public FoodItem(){
        super();
    }
    public FoodItem(String id){
        super(id);
    }

    public FoodItem isSpicy(Boolean isSpicy){
        this.isSpicy = isSpicy;
        return this;
    }

    public FoodItem isVegetarian(Boolean isVegetarian){
        this.isVegetarian = isVegetarian;
        return this;
    }

    public FoodItem isVegan(Boolean isVegan){
        this.isVegan = isVegan;
        return this;
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
