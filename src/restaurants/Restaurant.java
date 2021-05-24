package restaurants;

import java.util.UUID;

public class Restaurant {
    private String ID;
    protected String Name;
    protected String OwnerName;
    protected String PhoneNumber;
    protected String Email;
    protected String Type;
    Menu menu;

    public Restaurant(){
        this.ID = UUID.randomUUID().toString();
        this.menu = new Menu(ID);
    }

    public Restaurant(String name, String ownerName, String type) {
        this.ID = UUID.randomUUID().toString();
        this.Name = name;
        this.OwnerName = ownerName;
        this.menu = new Menu(ID);
        this.Type = type;
    }

    public Restaurant(String ID, String name, String ownerName, String type) {
        this.ID = ID;
        this.Name = name;
        this.OwnerName = ownerName;
        this.Type = type;
        this.menu = new Menu(ID);
    }

    public Restaurant(String ID, String name, String ownerName, String type, Menu menu) {
        this.ID = ID;
        this.Name = name;
        this.OwnerName = ownerName;
        this.Type = type;
        this.menu = menu;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "Name='" + Name + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", Type='" + Type + '\'' +
                ", menu=" + menu +
                '}';
    }
}
