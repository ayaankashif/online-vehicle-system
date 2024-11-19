package com.vehcalRentalSystem.model;

public class Vehicle extends BaseModel{

    private Integer vehicleId;
    private String make;
    private String model;
    private String variant;
    private String seats;
    private String vehicleType;
    private String vehicleLicenceNumber;

    public Vehicle() {
    }

    public Integer getVehicleId() {

        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleLicenceNumber() {
        return vehicleLicenceNumber;
    }

    public void setVehicleLicenceNumber(String vehicleLicenceNumber) {
        this.vehicleLicenceNumber = vehicleLicenceNumber;
    }
}
