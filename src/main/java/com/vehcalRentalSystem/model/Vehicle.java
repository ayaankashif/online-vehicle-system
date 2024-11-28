package com.vehcalRentalSystem.model;

import java.sql.Date;

public class Vehicle extends BaseModel{

	private Integer vehicleId;
    private String vehicleType;
    private String make;
    private String model;
    private String variant;
    private String seats;
    private String vehicleLicenceNumber;
    private String status;
    private int isDeleted;
    
    public Vehicle() {
    }

    public Vehicle(String vehicleType, String make, String model, String variant, String seats, String vehicleLicenceNumber, String status, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy, Integer vehicleId){
    	this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.seats = seats;
        this.vehicleLicenceNumber = vehicleLicenceNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy =createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
