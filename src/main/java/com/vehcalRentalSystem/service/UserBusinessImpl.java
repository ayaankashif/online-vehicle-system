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
        System.out.println("User ID: ");
    	Integer id = scanner.nextInt();
    	
        Users user = new Users(userName, contact, null, nic, address, email, "Customer", pass, 
        new Date(System.currentTimeMillis()), null, id, null);

        if (usersDaoImpl.saveUser(user) != null ) {
            System.out.println("Registration completed");
        } else {
            System.out.println("Fill out all the fields");
        }
    }

    public void registerDriver() {
        System.out.println("Full Name");
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
    }

    public void showCustomer(){
        List<Users> userList = usersDaoImpl.findAllUsers();
        System.out.println("Customer's List:\n");
        System.out.printf("%-12s %-12s %-15s %-17s %-15s %-18s %-10s%n",  
        "User ID", "User Name" ,"Contact info", "NIC", "Address", "Email", "Created Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Users users : userList) {
            System.out.printf("%-12s %-12s %-15s %-17s %-15s %-18s %-10s%n",
                users.getUserId(),
                users.getUserName(),
                users.getContactInfo(),
                users.getUserNic(),
                users.getAddress(),
                users.getEmail(),
                users.getCreatedDate()
            );
        }
    }

    public void showDriver(){
        List<Users> driverList = usersDaoImpl.showDriverList();
        System.out.println("Driver's List");
        System.out.printf("%-15s %-12s %-15s %-15s %-15s %-17s %-10s%n",  
        "Driver ID", "Name", "Contact Info", "Driver NIC", "Address", "Email", "Driver License Number");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        for (Users users : driverList) {
            System.out.printf("%-15s %-12s %-15s %-15s %-15s %-24s %-10s%n", 
            users.getUserId(),
            users.getUserName(),
            users.getContactInfo(),
            users.getUserNic(),
            users.getAddress(),
            users.getEmail(),
            users.getDriverLicenceNumber());
        }
    }

}
