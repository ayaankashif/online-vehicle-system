package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;
import com.vehcalRentalSystem.util.Validation;

public class UserBusinessImpl {
    static final Logger LOGGER = LogManager.getLogger(UserBusinessImpl.class);
    UsersDao usersDaoImpl = new UserDaoImpl();
    Validation validation = new Validation();
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Your Username should consist of Alphabets and Numbers");
            System.out.println("User Name: ");
            String userName = scanner.nextLine();
            while (!validation.isValidUserName(userName)) {
                LOGGER.info("Invalid Username");
                userName = scanner.nextLine();
            }
            validation.passRequirment();
            System.out.println("Your Passsword");
            String pass = scanner.nextLine();
            if (pass != null) {
                System.out.println("Strength: " + validation.passwordStrength(pass));
                System.out.println("Do you want to change your password ? 'YES|NO' ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    pass = scanner.nextLine();
                }
            }
            System.out.println("Contact (must includes 11 digits): ");
            String contact = scanner.nextLine();
            while (!validation.isValidContact(contact)) {
                LOGGER.info("Invalid Contact, it must includes 11 digits. Try again: ");
                contact = scanner.nextLine();
            }
            System.out.println("NIC (must includes 13 digits): ");
            String nic = scanner.nextLine();
            while (!validation.isValidNIC(nic)) {
                LOGGER.info("Invalid Contact, it must includes 13 digits. Try again: ");
                nic = scanner.nextLine();
            }

            System.out.println("Address: ");
            String address = scanner.nextLine();
            
            System.out.println("Email: ");
            String email = scanner.nextLine();
            while (!validation.isValidEmail(email)) {
                LOGGER.info("Invalid email format. Try again: ");
                email = scanner.nextLine();
            }
            
            System.out.println("User ID: ");
            Integer id = scanner.nextInt();

            Users user = new Users(userName, contact, null, nic, address, email, "Customer", pass,
                    new Date(System.currentTimeMillis()), null, id, null);

            if (usersDaoImpl.saveUser(user) != null) {
                System.out.println("Registration completed");
            } else {
                LOGGER.info("Fill out all the fields");
            }
        } catch (Exception e) {
            System.out.println("An unexpeced error occured " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registerDriver() {
        System.out.println("Full Name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        while(!validation.isValidName(name)){
            System.out.println("Invalid Name, (dont use special chracters) ");
            name = scanner.nextLine();
        }

        validation.passRequirment();
            System.out.println("Your Passsword");
            String pass = scanner.nextLine();
            if (pass != null) {
                System.out.println("Strength: " + validation.passwordStrength(pass));
                System.out.println("Do you want to change your password ? 'YES|NO' ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    registerDriver();
                }
            }

        System.out.println("Contact (must includes 11 digits): ");
        String contact = scanner.nextLine();
        while (!validation.isValidContact(contact)) {
            LOGGER.info("Invalid Contact, it must includes 11 digits. Try again: ");
            contact = scanner.nextLine();
        }
        System.out.println("Driver License Number: ");
        String licenseNo = scanner.nextLine();
        System.out.println("NIC (must includes 13 digits): ");
            String nic = scanner.nextLine();
            while (!validation.isValidNIC(nic)) {
                LOGGER.info("Invalid Contact, it must includes 13 digits. Try again: ");
                nic = scanner.nextLine();
            }

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Email: ");
            String email = scanner.nextLine();
            while (!validation.isValidEmail(email)) {
                LOGGER.info("Invalid email format. Try again: ");
                email = scanner.nextLine();
            }

        System.out.println("Driver Status: ");
        Integer driverStatus = scanner.nextInt();
        System.out.println("Driver ID: ");
        Integer id = scanner.nextInt();

        Users user = new Users(name, contact, licenseNo, nic, address, email, "driver", pass,
                new Date(System.currentTimeMillis()), null, id, driverStatus);

        if (usersDaoImpl.saveUser(user) != null) {
            System.out.println("Driver Registered");
        } else {
            LOGGER.info("Fill out all the fields");
        }
    }

    public void showCustomer() {
        List<Users> userList = usersDaoImpl.findAllUsers();
        System.out.println("Customer's List:\n");
        System.out.printf("%-12s %-12s %-15s %-17s %-15s %-18s %-10s%n",
                "User ID", "User Name", "Contact info", "NIC", "Address", "Email", "Created Date");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------");
        for (Users users : userList) {
            System.out.printf("%-12s %-12s %-15s %-17s %-15s %-18s %-10s%n",
                    users.getUserId(),
                    users.getUserName(),
                    users.getContactInfo(),
                    users.getUserNic(),
                    users.getAddress(),
                    users.getEmail(),
                    users.getCreatedDate());
        }
    }

    public void showDriver() {
        List<Users> driverList = usersDaoImpl.showDriverList();
        System.out.println("Driver's List");
        System.out.printf("%-15s %-12s %-15s %-15s %-15s %-17s %-10s%n",
                "Driver ID", "Name", "Contact Info", "Driver NIC", "Address", "Email", "Driver License Number");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------");

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
