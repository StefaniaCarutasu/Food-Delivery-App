package restaurants;

public class DrinkItem extends MenuItem {
    private Boolean isAlcoholic;

    public DrinkItem(){
        super();
    }

    public DrinkItem(String id){
        super(id);
    }

    public DrinkItem isAlcoholic(Boolean isAlcoholic){
        this.isAlcoholic = isAlcoholic;
        return this;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }
}
