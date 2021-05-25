package drivers;

import users.User;

public class Driver extends User {
    protected String VehicleType;
    protected String VehicleNumber;
    protected Boolean Availability;

    public Driver(){
        super();
    }

    public Driver(String id){
        super(id);
    }

    public Driver vehicleNumber(String vehicleNumber){
        this.VehicleType = vehicleNumber;
        return this;
    }
    public Driver vehicleType(String vehicleType){
        this.VehicleType = vehicleType;
        return this;
    }
    public Driver availability(Boolean availability){
        this.Availability = availability;
        return this;
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
