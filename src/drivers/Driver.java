package drivers;

import users.User;

public class Driver extends User {
    protected String VehicleType;
    protected String VehicleNumber;
    protected Boolean Availability;

    public Driver(String id, String userName, String email, String password, String address, int age, String vehicleType, String vehicleNumber ){
        super(id, userName, email, password, address, age);
        this.VehicleNumber = vehicleNumber;
        this.Availability = Boolean.TRUE;
        this.VehicleType = vehicleType;
    }

    public Driver(String userName, String email, String password, String address, int age, String vehicleType, String vehicleNumber ){
        super(userName, email, password, address, age);
        this.VehicleNumber = vehicleNumber;
        this.Availability = Boolean.TRUE;
        this.VehicleType = vehicleType;
    }

    public Driver(String userName, String email, String password, String address, String vehicleType, String vehicleNumber ){
        super(userName, email, password, address);
        this.VehicleNumber = vehicleNumber;
        this.Availability = Boolean.TRUE;
        this.VehicleType = vehicleType;
    }

    public Driver(String ID, String userName, String email, String vehicleType, String vehicleNumber, Boolean availability){
        super(ID, userName, email);
        this.VehicleNumber = vehicleNumber;
        this.Availability = Boolean.TRUE;
        this.VehicleType = vehicleType;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public Boolean getAvailability() {
        return Availability;
    }

    public void setAvailability(Boolean availability) {
        Availability = availability;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "Username ='" + Username + '\'' +
                ", Vehicle Number='" + VehicleNumber + '\'' +
                ", Vehicle Type=" + VehicleType +
                '}';
    }
}
