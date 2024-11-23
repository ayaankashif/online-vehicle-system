package com.vehcalRentalSystem.service;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.model.Users;

import java.util.Scanner;

public class Menu {

    UsersDao usersDaoImpl = new UserDaoImpl();

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

    }
}
