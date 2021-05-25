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
    public User(String id){
        this.ID = id;
    }
    public User username(String username){
        this.Username = username;
        return this;
    }
    public User email(String email){
        this.Email = email;
        return this;
    }
    public User password(String password){
        this.Password = password;
        return this;
    }
    public User address(String address){
        this.Address = address;
        return this;
    }
    public User age(Integer age){
        this.Age = age;
        return this;
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
