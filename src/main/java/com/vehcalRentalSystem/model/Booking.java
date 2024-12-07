package com.vehcalRentalSystem.model;

import java.sql.Date;

public class Booking extends BaseModel {
    private Integer bookingId;
    private Users customer;
    private Users driver;
    private Vehicle vehicle; 
    private Date bookingDate;
    private String startDate;
    private String endDate;
    private String rideType;
    private String pickup;
    private String destination;
    private String bookingType;
    private Date returnedDate;
    private String bookingStatus;
    private Integer isDeleted;

    
    public Booking() {
    }

    public Booking(Integer bookingID, Users customer, Users driver,  Vehicle vehicle, Date bookingDate, String startDate, String endDate, String rideType, String pickup, String destination, 
                String bookingType, Date returnedDate, String bookingStatus, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy ){
        
        this.bookingId = bookingID;
        this.customer = customer;
        this.driver = driver;
        this.vehicle = vehicle;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rideType = rideType;
        this.pickup = pickup;
        this.destination = destination;
        this.bookingType = bookingType;
        this.bookingDate = bookingDate;
        this.returnedDate = returnedDate;
        this.bookingStatus = bookingStatus;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    public Users getDriver() {
        return driver;
    }

    public void setDriver(Users driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
