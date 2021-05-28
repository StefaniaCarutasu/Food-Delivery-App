package controllers;
import DB.CsvManipulator;
import DB.DatabaseService;
import drivers.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriversController {
    static DatabaseService db = DatabaseService.getInstance();

    public static Driver Create(String Drivername, String email, String password, String vehicleType, String vehicleNumber) throws Exception {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        Driver newDriver = new Driver().availability(true).vehicleNumber(vehicleNumber).vehicleType(vehicleType);
        newDriver.setUsername(Drivername);
        newDriver.setEmail(email);
        newDriver.setPassword(password);
        newDriver.setVehicleNumber(vehicleNumber);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO Drivers (ID, USERNAME, EMAIL, PASSWORD, ADDRESS, VEHICLE_TYPE, VEHICLE_NUMBER, AVAILABILITY) VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, newDriver.getID());
        statement.setString(2, newDriver.getUsername());
        statement.setString(3, newDriver.getEmail());
        statement.setString(4, newDriver.getPassword());
        statement.setString(5, "");
        statement.setString(6, newDriver.getVehicleType());
        statement.setString(7, newDriver.getVehicleNumber());
        statement.setBoolean(8, newDriver.getAvailability());

        if(statement.executeUpdate() == 1){
            return newDriver;
        }
        return null;
    }

    public static List<Driver> Read() throws Exception {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        List<Driver> Drivers = new ArrayList<>();
        ResultSet results = db.connection.createStatement().executeQuery("SELECT ID, USERNAME, EMAIL, VEHICLE_TYPE, VEHICLE_NUMBER, AVAILABILITY FROM DRIVERS");
        while(results.next()){
            Driver driver = new Driver(results.getString(1)).vehicleType(results.getString(4)).vehicleNumber(results.getString(5)).availability(results.getBoolean(6));
            driver.setUsername(results.getString(2));
            Drivers.add(driver);
        }
        return Drivers;
    }

    public static boolean Update(String id, String fieldToUpdate, String updatedValue) throws Exception {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement;

        switch (fieldToUpdate){
            case "USERNAME":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET USERNAME = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "EMAIL":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET EMAIL = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "PASSWORD":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET PASSWORD = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "ADDRESS":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET ADDRESS = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "AGE":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET AGE = ? WHERE ID LIKE ?");
                statement.setInt(1, Integer.parseInt(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "VEHICLE_TYPE":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET VEHICLE_TYPE = ? WHERE ID LIKE ?");
                statement.setInt(1, Integer.parseInt(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "VEHICLE_NUMBER":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET VEHICLE_NUMBER = ? WHERE ID LIKE ?");
                statement.setString(1, updatedValue);
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            case "AVAILABILITY":
                statement = db.connection.prepareStatement("UPDATE DRIVERS SET AVAILABILITY = ? WHERE ID LIKE ?");
                statement.setBoolean(1, Boolean.parseBoolean(updatedValue));
                statement.setString(2, id);
                return statement.executeUpdate() == 1;
            default:
                return false;
        }

    }

    public boolean Delete(String id) throws Exception {
        String threadName = Thread.currentThread().getName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        CsvManipulator.write(methodName, threadName);

        PreparedStatement statement = db.connection.prepareStatement("DELETE FROM Drivers WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }
}
