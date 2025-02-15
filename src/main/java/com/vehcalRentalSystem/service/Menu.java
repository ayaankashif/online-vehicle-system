package com.vehcalRentalSystem.service;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.exceptionHandling.invalidInputException;
import com.vehcalRentalSystem.exceptionHandling.onlineVehicleSystem;
import com.vehcalRentalSystem.model.Users;
import java.util.Scanner;

public class Menu {
    bookingBusinessImpl bookingBusinessImpl = new bookingBusinessImpl();
    UserBusinessImpl usersmenu = new UserBusinessImpl();
    UsersDao usersDaoImpl = new UserDaoImpl();
    VehicleBusinessImpl vehicleMenu = new VehicleBusinessImpl();

    public void loginMenu() {
        while (true) {
            try {
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
                        throw new invalidInputException("Incorrect choice, Try again!!!");
                }
            } catch (onlineVehicleSystem e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void login() {
        while (true) {
            try {
                System.out.println("Welcome To Online Vehicle Rental System.\n");
                System.out.println("Insert Username: ");
                Scanner scanner = new Scanner(System.in);
                String userName = scanner.nextLine();
                System.out.println("Insert Password: ");
                String password = scanner.nextLine();
                Users users = usersDaoImpl.userLogin(userName, password);

                if (users != null) {
                    switch (users.getUserType()) {
                        case "Customer":
                            userMenu(users);
                            break;
                        case "admin":
                            adminMenu(users);
                            break;
                        case "driver":
                            // driverMenu(users);
                            break;
                        default:
                            System.out.println("No Record Found");
                            break;
                    }
                } else {
                    throw new invalidInputException("Incorrect Login Information. Please Try Again!!!");
                }
            } catch (onlineVehicleSystem e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void userMenu(Users user) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nOnline Vehicle Rental System.\nCustomer Menu\n");
                System.out.println("1. Booking");
                System.out.println("2. Booking History");
                System.out.println("3. Return Vehicle");
                System.out.println("4. Cancel Booking");
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
                    case "3":
                        bookingBusinessImpl.completeBooking(user);
                        userMenu(user);
                        break;
                    case "4":
                        bookingBusinessImpl.cancelBooking(user);
                        userMenu(user);
                        break;
                    default:
                        throw new invalidInputException("Invalid Choice!!!, Try Again");
                }
            } catch (onlineVehicleSystem e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void adminMenu(Users users) {
        while (true) {
            try {
                System.out.println("\nOnline Vehicle Rental System\nAdmin menu\n");
                System.out.println("1: Register Driver");
                System.out.println("2: Customer Details");
                System.out.println("3: Vehicle Section");
                System.out.println("4: Driver Details");
                System.out.println("5: Booking Details");
                System.out.println("6: Payments Details");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        usersmenu.registerDriver();
                        adminMenu(users);
                        break;
                    case 2:
                        usersmenu.showCustomer();
                        adminMenu(users);
                        break;
                    case 3:
                        vehicleMenu(users);
                        adminMenu(users);
                        break;
                    case 4:
                        usersmenu.showDriver();
                        adminMenu(users);
                        break;
                    case 5:
                        bookingBusinessImpl.bookingHistory();
                        adminMenu(users);
                        break;
                    default:
                        throw new invalidInputException("Invalid choice");
                }
            } catch (onlineVehicleSystem e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void vehicleMenu(Users users) {
        while (true) {
            try {
                System.out.println("\nOnline Vehicle Rental System\nVehicle Menu\n");
                System.out.println("1: Register Vehicle");
                System.out.println("2: Update vehicle");
                System.out.println("3: All Vehicles");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        vehicleMenu.registerVehicle(users);
                        adminMenu(users);
                        break;
                    case 2:
                        vehicleMenu.updateVehicle(users);
                        adminMenu(users);
                        break;
                    case 3:
                        vehicleMenu.showVehicle();
                        adminMenu(users);
                        break;
                    default:
                        throw new invalidInputException("invalid input, Try Again!!!");
                }
            } catch (onlineVehicleSystem e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
