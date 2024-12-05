package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;
import com.vehcalRentalSystem.util.validation;

public class UserBusinessImpl {
    UsersDao usersDaoImpl = new UserDaoImpl();
    validation validation = new validation();
    
    public void signUp() {
        System.out.println("Your Username should consist of Alphabets and Numbers");
    	System.out.println("User Name: ");
    	Scanner scanner = new Scanner(System.in);
    	String userName = scanner.nextLine();
        if (validation.isValidUserName(userName) == false) {
            System.out.println("Do not use speacial characters\n");
            signUp();
        }
        validation.passRequirment();
        System.out.println("Your Passsword");
        String pass = scanner.nextLine();
        if (pass != null) {
            System.out.println("Strength: " + validation.passwordStrength(pass));
            System.out.println("Do you want to change your password ? 'YES|NO' ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                signUp();
            }
        }
    	System.out.println("Contact: ");
    	String contact = scanner.nextLine();
        validation.isValidContact(contact);
    	System.out.println("NIC: ");
        String nic = scanner.nextLine();
        validation.isValidNIC(nic);
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        validation.isValidEmail(email);
        System.out.println("UserType: ");
        String userType = scanner.nextLine();
        System.out.println("User ID: ");
    	Integer id = scanner.nextInt();
    	
        Users user = new Users(userName, contact, null, nic, address, email, userType, pass, 
        new Date(System.currentTimeMillis()), null, id, null);

        if (usersDaoImpl.saveUser(user) != null ) {
            System.out.println("Registration completed");
        } else {
            System.out.println("Fill out all the fields");
        }
        scanner.close();
    }

    public void registerDriver() {
        System.out.println("Your Full Name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        validation.isValidUserName(name);
        System.out.println("Contact: ");
        String contact = scanner.nextLine();
        System.out.println("Driver License Number: ");
        String licenseNo = scanner.nextLine();
        System.out.println("NIC: ");
        String nic = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Your Passsword");
        String pass = scanner.nextLine();
        System.out.println("Driver Status: ");
        Integer driverStatus = scanner.nextInt();
        System.out.println("Driver ID: ");
    	Integer id = scanner.nextInt();
    	
        Users user = new Users(name, contact, licenseNo, nic, address, email,"driver", pass,
                new Date(System.currentTimeMillis()), null, id, driverStatus);

        if (usersDaoImpl.saveUser(user) != null) {
            System.out.println("Driver Registered");
        } else {
            System.out.println("Fill out all the fields");
        }
        scanner.close();
    }

    public void showCustomer(){
        List<Users> userList = usersDaoImpl.findAllUsers();
        System.out.println("Customer's List:\n");
        for (Users users : userList) {
            System.out.println(String.format(
                "User ID: %s\nUser Name: %s\nContact info: %s\nNIC: %s\nAddress: %s\nEmail: %s\nCreated Date: %s\n", 
                users.getUserId(),
                users.getUserName(),
                users.getContactInfo(),
                users.getUserNic(),
                users.getAddress(),
                users.getEmail(),
                users.getCreatedDate()
            ));
        }
    }

    public void showDriver(){
        List<Users> driverList = usersDaoImpl.showDriverList();
        System.out.println("Driver's List");
        for (Users users : driverList) {
            System.out.println(String.format("Driver ID: %s\nName: %s\nContact Info: %s\nDriver NIC: %s\n"
            + "Address: %s\nEmail: %s\nDriver License Number: %s\n",
            users.getUserId(),
            users.getUserName(),
            users.getContactInfo(),
            users.getUserNic(),
            users.getAddress(),
            users.getEmail(),
            users.getDriverLicenceNumber()
            ));
        }
    }

}
