package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;

public class UserBusinessImpl {
    UsersDao usersDaoImpl = new UserDaoImpl();
    
    public void signUp() {
    	
    	System.out.println("User Name: ");
    	Scanner scanner = new Scanner(System.in);
    	String name = scanner.nextLine();
    	System.out.println("Contact: ");
    	String contact = scanner.nextLine();
    	System.out.println("NIC: ");
        String nic = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("UserType: ");
        String userType = scanner.nextLine();
        System.out.println("Your Passsword");
        String pass = scanner.nextLine();
        System.out.println("User ID: ");
    	Integer id = scanner.nextInt();
    	
        Users user = new Users(name, contact, null, nic, address, email, userType, pass,
                new Date(System.currentTimeMillis()), null, id);

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
        System.out.println("UserType: ");
        String userType = scanner.nextLine();
        System.out.println("Your Passsword");
        String pass = scanner.nextLine();
        System.out.println("Driver ID: ");
    	Integer id = scanner.nextInt();
    	
        Users user = new Users(name, contact, licenseNo, nic, address, email, userType, pass,
                new Date(System.currentTimeMillis()), null, id);

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

}
