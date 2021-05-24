package restaurants;

public class DrinkItem extends MenuItem {
    private boolean isAlcoholic;

    public DrinkItem(){
        super();
    }

    public DrinkItem(String ID, String name, Double price, String ingredients, String allergens, boolean isAlcoholic){
        super(ID, name, price, ingredients, allergens);
        this.isAlcoholic = isAlcoholic;
    }

    public DrinkItem(String name, Double price, String ingredients, String allergens, boolean isAlcoholic){
        super(name, price, ingredients, allergens);
        this.isAlcoholic = isAlcoholic;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }
}
