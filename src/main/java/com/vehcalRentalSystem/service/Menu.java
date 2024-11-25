package com.vehcalRentalSystem.service;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;

import java.util.Scanner;

public class Menu {

    UsersDao usersDaoImpl = new UserDaoImpl();

    public void registerCustomer(){
        System.out.println("Sign up or login \n1: Sign up \n2: Login");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                signUp();
            case "2":
                loginMenu();
            default:
                System.out.println("Incorrect choice, Try again!!!");
                break;
        }
        scanner.close();
    }

    public void signUp() {
        System.out.println("Your Full Name");
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
        Users user = new Users(name, contact, nic, address, email, userType, pass);
        
        if(usersDaoImpl.saveUser(user) != null){
            System.out.println("Registration completed");
            loginMenu();
        }else {
            System.out.println("Fill out all the fields");
        }
        scanner.close();
    }

    public void registerDriver(){
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
        Users user = new Users(name, contact, licenseNo, nic, address, email, userType);

        if(usersDaoImpl.saveUser(user) != null){
            System.out.println("Driver Registered");
        }else {
            System.out.println("Fill out all the fields");
        }
        scanner.close();
    }
    
    public void loginMenu(){
        System.out.println("Welcome To Online Vehicle Rental System.\n");
        System.out.println("Insert Username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("Insert Password: ");
        String password = scanner.nextLine();
        Users users = usersDaoImpl.userLogin(userName,password);
        if(users != null){
            MainMenu(users);
        }else {
            System.out.println("Incorrect Login Information. Please Try Again!!!");
            loginMenu();
        }
        scanner.close();
    }

    public void MainMenu(Users users){
        System.out.println("Online Vehicle Rental System.\n");
        System.out.println("1. Booking");
        System.out.println("2. Booking History");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Choice: " );
        String input = scanner.nextLine();

        switch (input){
            case "1":
                System.out.println("Booking Implementation");
                MainMenu(users);
            case "2":
                System.out.println("Booking History");
                MainMenu(users);
            default:
                System.out.println("Invalid Choice!!!, Try Again");
                MainMenu(users);
        }
        scanner.close();
    }
}
