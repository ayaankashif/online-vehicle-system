package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.Scanner;

import com.vehcalRentalSystem.dao.BookingDao;
import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.daoimpl.BookingDaoImpl;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
import com.vehcalRentalSystem.model.Booking;
import com.vehcalRentalSystem.model.Users;
import com.vehcalRentalSystem.model.Vehicle;

public class DriverBusinessImpl {

    UsersDao usersDao = new UserDaoImpl();
    VehicleDao vehicleDao = new VehicleDaoImpl();
    BookingDao bookingDao = new BookingDaoImpl();
    VehicleBusinessImpl vehicleBusinessImpl = new  VehicleBusinessImpl();

    public void driverImpl(Users driver){
        System.out.println("Start Date: ");
        Scanner scanner = new Scanner(System.in);
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        System.out.println("Pickup: ");
        String pickup = scanner.nextLine();
        System.out.println("Destination: ");
        String dest = scanner.nextLine();
        System.out.println("Ride Type: ");
        String rideType = scanner.nextLine();
        System.out.println("Booking Type: ");
        String bookType = scanner.nextLine();
        System.out.println("Booking Status: ");
        String status = scanner.nextLine();
        System.out.println("Booking ID: ");
        Integer id = scanner.nextInt();
        System.out.println("Driver ID: ");
        Integer userId = scanner.nextInt();
        vehicleBusinessImpl.showVehicle();
        System.out.println("Vehicle ID: ");
        Integer vehicleId = scanner.nextInt();

        driver = usersDao.getUserbyId(userId);
        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);

        Booking booking = new Booking(id, null, driver, vehicle, new Date(System.currentTimeMillis()), startDate, endDate, 
        rideType, pickup, dest, bookType, null, status, new Date(System.currentTimeMillis()), 
        null, null);

        if (bookingDao.saveBooking(booking) != null) {
            System.out.println("Booking Successfull");
        } else{
            System.out.println("Booking Failed");
        }
        scanner.close();
    }

    public void updateBooking(Users driver){
        System.out.println("Start Date: ");
        Scanner scanner = new Scanner(System.in);
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        System.out.println("Pickup: ");
        String pickup = scanner.nextLine();
        System.out.println("Destination: ");
        String dest = scanner.nextLine();
        System.out.println("Booking Type: ");
        String bookType = scanner.nextLine();
        System.out.println("Returned Date: ");
        String retDate = scanner.nextLine();
        System.out.println("Booking Status: ");
        String status = scanner.nextLine();
        System.out.println("Modified By: ");
        String modifyBy = scanner.nextLine();
        System.out.println("Booking ID: ");
        Integer id = scanner.nextInt();
        System.out.println("Driver ID: ");
        Integer userId = scanner.nextInt();

        driver = usersDao.getUserbyId(userId);

        Booking booking = new Booking(id, null, driver, null, null,
        startDate, endDate, null, pickup, dest, bookType, retDate, status, null,
        new Date(System.currentTimeMillis()), modifyBy);

        if (bookingDao.updateBooking(booking) != null) {
            System.out.println("Booking Successfull");
        } else{
            System.out.println("Booking Failed");
        }
        scanner.close();
    }
}
