package com.vehcalRentalSystem.model;

import java.sql.Date;

public class Users extends BaseModel {

    private Integer userId;
    private String userName;
    private String contactInfo;
    private String userNic;
    private String address;
    private String driverLicenceNumber;
    private String email;
    private String userType;
    private String password; 
    private Integer driverStatus;
    private int isDeleted;
    
    public Users() {
    }
        
    public Users(String userName, String contactInfo, String driverLicenceNumber, String userNic, String address, String email, String userType, String password,  Date createdDate, Date modifiedDate, Integer userId, Integer driverStatus){
    	this.userId = userId;
        this.userName = userName;
        this.contactInfo = contactInfo;
        this.driverLicenceNumber = driverLicenceNumber;
        this.userNic = userNic;
        this.address = address;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.driverStatus = driverStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Integer driverStatus) {
        this.driverStatus = driverStatus;
    }
}
