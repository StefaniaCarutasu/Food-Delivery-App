package restaurants;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class Menu {
    private String ID;
    private String restaurantID;
    protected TreeMap<String, List<MenuItem>> menuList = new TreeMap<String, List<MenuItem>>();

    public Menu(String restaurantID){
        this.ID = UUID.randomUUID().toString();
        this.restaurantID = restaurantID;
        menuList.put("Food", new ArrayList<>());
        menuList.put("Drinks", new ArrayList<>());
    }

    public Menu(){
        this.ID = UUID.randomUUID().toString();
        menuList.put("Food", new ArrayList<>());
        menuList.put("Drinks", new ArrayList<>());
    }

    public Menu(String restaurantID, List<MenuItem> menuFood, List<MenuItem> menuDrink) {
        this.ID = UUID.randomUUID().toString();
        this.restaurantID = restaurantID;
        menuList.put("Food", new ArrayList<>());
        menuList.put("Drinks", new ArrayList<>());
        menuList.get("Food").addAll(menuFood);
        menuList.get("Drinks").addAll(menuDrink);
    }

    public Menu(String restaurantID, String ID, TreeMap<String, List<MenuItem>> menuList) {
        this.ID = ID;
        this.restaurantID = restaurantID;
        this.menuList = menuList;
    }

    public Menu(String restaurantID, TreeMap<String, List<MenuItem>> menuList) {
        this.ID = UUID.randomUUID().toString();
        this.restaurantID = restaurantID;
        this.menuList = menuList;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public TreeMap<String, List<MenuItem>> getMenuList() {
        return menuList;
    }

    public void setMenuList(TreeMap<String, List<MenuItem>> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        StringBuilder foods = new StringBuilder("Food\n");
        StringBuilder drinks = new StringBuilder("Drinks\n");
        for(MenuItem m: menuList.get("Food")){
            foods.append(m.getName()).append("             Price: ").append(m.getPrice()).append('\n');
        }

        for (MenuItem m: menuList.get("Drinks")){
            drinks.append(m.getName()).append("             Price: ").append(m.getPrice()).append('\n');
        }

        return foods.append(drinks).toString();
    }
}
