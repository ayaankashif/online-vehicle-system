package com.vehcalRentalSystem.service;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;
import java.util.Scanner;

public class Menu {
    bookingBusinessImpl bookingBusinessImpl = new bookingBusinessImpl();
    UserBusinessImpl usersmenu = new UserBusinessImpl();
    UsersDao usersDaoImpl = new UserDaoImpl();
    VehicleBusinessImpl vehicleMenu = new VehicleBusinessImpl();
    DriverBusinessImpl driverBusinessImpl = new DriverBusinessImpl();

    public void loginMenu() {
        System.out.println("Sign up or login \n1: Sign up \n2: Login");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                usersmenu.signUp();
                login();
                break;
            case "2":
                login();
                break;
            default:
                System.out.println("Incorrect choice, Try again!!!");
                break;
        }
        scanner.close();
    }
    
    public void login() {
        System.out.println("Welcome To Online Vehicle Rental System.\n");
        System.out.println("Insert Username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("Insert Password: ");
        String password = scanner.nextLine();
        Users users = usersDaoImpl.userLogin(userName, password);

        if (users != null) {
            switch (users.getUserType()) {
                case "customer":
                    userMenu(users);
                    break;
                case "admin":
                    adminMenu();
                    break;
                case "driver":
                    driverMenu(users);
                    break;
                default:
                    System.out.println("No Record Found");
                    break;
            }
        } else {
            System.out.println("Incorrect Login Information. Please Try Again!!!");
            loginMenu();
        }
        scanner.close();
    }

    public void userMenu(Users user) {
        System.out.println("\nOnline Vehicle Rental System.\nCustomer Menu\n");
        System.out.println("1. Booking");
        System.out.println("2. Booking History");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Choice: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                bookingBusinessImpl.bookingImpl(user);
                userMenu(user);
                break;
            case "2":
                bookingBusinessImpl.bookingHistory(user);
                userMenu(user);
                break;
            default:
                System.out.println("Invalid Choice!!!, Try Again");
                userMenu(user);
                break;
        }
        scanner.close();
    }

    public void adminMenu() {
        System.out.println("\nOnline Vehicle Rental System\nAdmin menu\n");
        System.out.println("1: Register Driver");
        System.out.println("2: Customer Details");
        System.out.println("3: Vehicle Section");
        System.out.println("4: Booking Details");
        System.out.println("5: Payments Details");
        System.out.println("6: Maintenance Details");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                usersmenu.registerDriver();
                adminMenu();
                break;
            case 2:
                usersmenu.showCustomer();
                adminMenu();
                break;
            case 3:
                vehicleMenu();
                adminMenu();
                break;
            case 4:
                //bookingBusinessImpl.bookingHistory();
                adminMenu();
                break;
            case 5:
                System.out.println("payments");
                adminMenu();
                break;
            case 6:
                System.out.println("mainteneace");
                adminMenu();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        scanner.close();
    }


    public void vehicleMenu() {
        System.out.println("\nOnline Vehicle Rental System\nVehicle Menu\n");
        System.out.println("1: Register Vehicle");
        System.out.println("2: Update vehicle");
        System.out.println("3: All Vehicles");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                vehicleMenu.registerVehicle();
                adminMenu();
                break;
            case 2:
                vehicleMenu.updateVehicle();
                adminMenu();
                break;
            case 3:
                vehicleMenu.showVehicle();
                adminMenu();
                break;
            default:
                break;
        }
        scanner.close();
    }

    public void driverMenu(Users user) {
        System.out.println("\nOnline Vehicle Rental System.\nDriver Menu\n");
        System.out.println("1. Booking");;
        System.out.println("2: Update Booking");
        System.out.println("3: All Vehicles");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                driverBusinessImpl.driverImpl(user);
                break;
            case 2:
                driverBusinessImpl.updateBooking(user);
                break;
            case 3:
                System.out.println("book menu");
                break;
            default:
                break;
        }
        scanner.close();
    }

}
