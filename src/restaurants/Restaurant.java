package restaurants;

import java.util.Comparator;
import java.util.UUID;

public class Restaurant {
    private String ID;
    protected String Name;
    protected String OwnerName;
    protected String PhoneNumber;
    protected String Email;
    protected String Type;
    protected String Address;

    public Restaurant(){
        this.ID = UUID.randomUUID().toString();
    }

    public Restaurant(String id){
        this.ID = id;
    }

    public Restaurant name(String name){
        this.Name = name;
        return this;
    }

    public Restaurant owner(String name){
        this.OwnerName = name;
        return this;
    }

    public Restaurant phone(String number){
        this.PhoneNumber = number;
        return this;
    }

    public Restaurant email(String email){
        this.Email = email;
        return this;
    }

    public Restaurant type(String type){
        this.Type = type;
        return this;
    }

    public Restaurant address(String address){
        this.Address = address;
        return this;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        String res = this.Name +  System.lineSeparator() + "Phone: " + this.PhoneNumber + "\n" + "Email: " + this.Email;
        return res;
    }
}

class RestaurantsComparator implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant res1, Restaurant res2) {
        return String.CASE_INSENSITIVE_ORDER.compare(res1.getName(), res2.getName());
    }

}
