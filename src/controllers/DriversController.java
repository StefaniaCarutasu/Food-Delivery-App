package controllers;
import DB.DatabaseService;
import drivers.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriversController {
    static DatabaseService db = DatabaseService.getInstance();

    public Driver Create(String Drivername, String email, String password, String vehicleType, String vehicleNumber) throws Exception {
        Driver newDriver = new Driver().availability(true).vehicleNumber(vehicleNumber).vehicleType(vehicleType);
        newDriver.setUsername(Drivername);
        newDriver.setEmail(email);
        newDriver.setPassword(password);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO Drivers (ID, USERNAME, EMAIL, PASSWORD, VEHICLE_TYPE, VEHICLE_NUMBER, AVAILABILITY) VALUES (?,?,?,?,?)");
        statement.setString(1, newDriver.getID());
        statement.setString(2, newDriver.getUsername());
        statement.setString(3, newDriver.getEmail());
        statement.setString(4, newDriver.getPassword());
        statement.setString(5, newDriver.getVehicleType());
        statement.setString(6, newDriver.getVehicleNumber());
        statement.setBoolean(7, newDriver.getAvailability());

        if(statement.executeUpdate() == 1){
            return newDriver;
        }
        return null;
    }

    public static List<Driver> Read() throws Exception {
        List<Driver> Drivers = new ArrayList<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, USERNAME, EMAIL, VEHICLE_TYPE, VEHICLE_NUMBER, AVAILABILITY FROM DRIVERS");
        while(results.next()){
            Driver driver = new Driver(results.getString(1)).vehicleType(results.getString(4)).vehicleNumber(results.getString(5)).availability(results.getBoolean(6));
            driver.setUsername(results.getString(2));
            Drivers.add(driver);
            //Drivers.add(new Driver(results.getString(1), results.getString(2), results.getString(3), results.getString(5), results.getString(6), results.getBoolean(7)));
        }
        return Drivers;
    }

    public boolean Update(String id, String fieldToUpdate, String updatedValue) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("UPDATE Drivers SET ? = ?, WHERE id = ?");

        statement.setString(1, fieldToUpdate);
        statement.setString(2, updatedValue);
        statement.setString(3, id);

        return statement.executeUpdate() == 1;
    }

    public boolean Delete(String id) throws Exception {
        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM Drivers WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }
}
