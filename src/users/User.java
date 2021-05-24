package users;

import java.util.SplittableRandom;
import java.util.UUID;

public class User {

    private String ID;
    protected String Username;
    private String Email;
    private String Password;
    protected String Address;
    protected int Age;

    public User(){
        this.ID = UUID.randomUUID().toString();
    }

    public User(String ID, String userName, String email, String address, int age ){
        this.ID = ID;
        this.Username = userName;
        this.Email = email;
        this.Address = address;
        this.Age = age;
    }

    public User(String userName, String email, String password, String address){
        this.ID = UUID.randomUUID().toString();
        this.Username = userName;
        this.Email = email;
        this.Password = password;
        this.Address = address;
    }

    public User(String id, String userName, String email, String password, String address, int age ){
        this.ID = id;
        this.Username = userName;
        this.Email = email;
        this.Password = password;
        this.Address = address;
        this.Age = age;
    }

    public User(String id, String userName, String email) {
        this.ID = id;
        this.Username = userName;
        this.Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Age=" + Age +
                '}';
    }
}
