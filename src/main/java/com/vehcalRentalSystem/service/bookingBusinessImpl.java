package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.List;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class bookingBusinessImpl {
    UsersDao usersDao = new UserDaoImpl();
    VehicleDao vehicleDao = new VehicleDaoImpl();
    BookingDao bookingDao = new BookingDaoImpl();
    VehicleBusinessImpl vehicleBusinessImpl = new VehicleBusinessImpl();
    UserBusinessImpl userBusinessImpl = new UserBusinessImpl();

    public void bookingImpl(Users users) {
        if (bookingDao.hasActiveBooking(users.getUserId())) {
            System.out.println(
                    "You already have an active reservation.\nPlease Return the current vehicle if you want to book another one.");
            return;
        }
        System.out.println("Do you want to book a driver ? YES | NO");
        Scanner scanner = new Scanner(System.in);
        String isDriverRequired = scanner.nextLine();
        String bookType;
        Users driver = null;
        if (isDriverRequired.equalsIgnoreCase("YES")) {
            driver = usersDao.getAvailableDriver();
            usersDao.driverStatus(0, driver);
            bookType = "With Driver";
        } else
            bookType = "Indivisual";
        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        System.out.println("Starting Journey: ");
        String pickup = scanner.nextLine();
        System.out.println("Destination: ");
        String dest = scanner.nextLine();
        System.out.println("Ride Type: ");
        String rideType = scanner.nextLine();
        System.out.println("Booking Status");
        String status = scanner.nextLine();
        System.out.println("Booking ID: ");
        Integer id = scanner.nextInt();
        vehicleBusinessImpl.showVehicle();
        System.out.println("Select your Vehicle by ID: ");
        Integer vehicleId = scanner.nextInt();
        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
        vehicleDao.vehicleStatus(vehicle);

        Booking booking = new Booking(id, users, driver, vehicle, new Date(System.currentTimeMillis()),
                startDate, endDate, rideType, pickup, dest, bookType, null, status,
                new Date(System.currentTimeMillis()),
                users.getUserName(), null, null);

        if (bookingDao.saveBooking(booking) != null) {
            System.out.println("Booking Successfull");
        } else {
            System.out.println("Booking Failed");
        }
    }

    public void updateBooking(Users users) {
        System.out.println("Start Date: ");
        Scanner scanner = new Scanner(System.in);
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        System.out.println("Booking Type: ");
        String bookType = scanner.nextLine();
        System.out.println("Booking Status: ");
        String status = scanner.nextLine();
        System.out.println("Starting point ");
        String pickup = scanner.nextLine();
        System.out.println("End Point: ");
        String dest = scanner.nextLine();
        System.out.println("Modified By: ");
        String modifyBy = scanner.nextLine();
        System.out.println("Booking ID: ");
        Integer id = scanner.nextInt();

        Booking booking = new Booking(id, users, null, null, null,
                startDate, endDate, null, pickup, dest, bookType, new Date(System.currentTimeMillis()), status, null,
                null,
                new Date(System.currentTimeMillis()), modifyBy);

        if (bookingDao.updateBooking(booking) != null) {
            System.out.println("Booking Updated");
        } else {
            System.out.println("Failed to update");
        }
    }

    public void bookingHistory(Users users) {
        System.out.println("If you want your brief Booking history press '1' if not press '2'.");
        Scanner scanner = new Scanner(System.in);
        String choice  = scanner.nextLine();

        if (choice.equals("1")) {
            try{
                File obj = new File("C:\\Users\\AYAAN\\Documents\\Booking Details.txt");
                if (obj.createNewFile()) {
                    System.out.println("File Created " + obj.getName());
                } else {
                    System.out.println("File already exists");
                }
                
                List<Booking> bookingList = bookingDao.fetchAllBookings(users);
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(obj))) {
                    writer.write("Prime Wheels (PW)\n\nBooking List\n\n");
                    writer.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", 
                    "Booking ID", "Customer ID", "Vehicle Id", "User name", "Contact", "User NIC", "Address", "Email","Created Date", "Vehicle Type", "Make", "Model", "Variant", "Seats", "License Number", "Status","Created Date", "Booking Date", "Start Date", "End Date", "Ride type", "Pickup", "Destination", "Booking Type", "Booking Status"));
                    writer.write("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    for (Booking booking : bookingList) {
                    writer.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", 
                            booking.getBookingId(),
                            booking.getCustomer().getUserId(),
                            booking.getVehicle().getVehicleId(),
                            booking.getCustomer().getUserName(),
                            booking.getCustomer().getContactInfo(),
                            booking.getCustomer().getUserNic(),
                            booking.getCustomer().getAddress(),
                            booking.getCustomer().getEmail(),
                            booking.getCustomer().getCreatedDate(),
                            booking.getVehicle().getVehicleType(),
                            booking.getVehicle().getMake(),
                            booking.getVehicle().getModel(),
                            booking.getVehicle().getVariant(),
                            booking.getVehicle().getSeats(),
                            booking.getVehicle().getVehicleLicenceNumber(),
                            booking.getVehicle().getStatus(),
                            booking.getVehicle().getCreatedDate(),
                            booking.getBookingDate(),
                            booking.getStartDate(),
                            booking.getEndDate(),
                            booking.getRideType(),
                            booking.getPickup(),
                            booking.getDestination(),
                            booking.getBookingType(),
                            booking.getBookingStatus()));
                    }
                    System.out.println("Booking details written to file successfully");    
                } 
            } catch (IOException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            } 
        } else if (choice.equals("2")) {
            
            List<Booking> bookingList = bookingDao.fetchAllBookings(users);
            System.out.println("\nBooking List\n");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
            "Booking ID", "User name", "Contact", "Model", "License Number", "Booking Date", "End Date", "Pickup", "Destination");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Booking booking : bookingList) {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                        booking.getBookingId(),
                        booking.getCustomer().getUserName(),
                        booking.getCustomer().getContactInfo(),
                        booking.getVehicle().getModel(),
                        booking.getVehicle().getVehicleLicenceNumber(),
                        booking.getBookingDate(),
                        booking.getEndDate(),
                        booking.getPickup(),
                        booking.getDestination());
            }
        }
    }

    public void bookingHistory() {
        List<Booking> bookingList = bookingDao.fetchAllBookings();
        System.out.println("Booking List");
        for (Booking booking : bookingList) {
            System.out.println(String.format(
                    "Booking ID %s\nCustomer ID: %s\nVehicle Id: %s\nUser name: %s\nContact: %s\nUser NIC: %s\nAddress: %s\nEmail: %s\nCreated Date: %s\n"
                            + "Vehicle Type: %s\nMake: %s\nModel: %s\nVariant: %s\nSeats: %s\nLicense Number: %s\nStatus: %s\nCreated Date: %s\n"
                            + "Booking Date: %s\nStart Date: %s\nEnd Date: %s\nRide type: %s\nPickup: %s\nDestination: %s\nBooking Type: %s\nBooking Status: %s\n",
                    booking.getBookingId(),
                    booking.getCustomer().getUserId(),
                    booking.getVehicle().getVehicleId(),
                    booking.getCustomer().getUserName(),
                    booking.getCustomer().getContactInfo(),
                    booking.getCustomer().getUserNic(),
                    booking.getCustomer().getAddress(),
                    booking.getCustomer().getEmail(),
                    booking.getCustomer().getCreatedDate(),
                    booking.getVehicle().getVehicleType(),
                    booking.getVehicle().getMake(),
                    booking.getVehicle().getModel(),
                    booking.getVehicle().getVariant(),
                    booking.getVehicle().getSeats(),
                    booking.getVehicle().getVehicleLicenceNumber(),
                    booking.getVehicle().getStatus(),
                    booking.getVehicle().getCreatedDate(),
                    booking.getBookingDate(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getRideType(),
                    booking.getPickup(),
                    booking.getDestination(),
                    booking.getBookingType(),
                    booking.getBookingStatus()));
        }
    }

    // public void bookingHistory(Users users) {
    // List<Booking> bookingList = bookingDao.fetchAllBookings(users);
    // System.out.println("Booking List");
    // for (Booking booking : bookingList) {
    // System.out.println(String.format(
    // "Booking ID %s\nCustomer ID: %s\nVehicle Id: %s\nUser name: %s\nContact:
    // %s\nUser NIC: %s\nAddress: %s\nEmail: %s\nCreated Date: %s\n"
    // + "Vehicle Type: %s\nMake: %s\nModel: %s\nVariant: %s\nSeats: %s\nLicense
    // Number: %s\nStatus: %s\nCreated Date: %s\n"
    // + "Booking Date: %s\nStart Date: %s\nEnd Date: %s\nRide type: %s\nPickup:
    // %s\nDestination: %s\nBooking Type: %s\nBooking Status: %s\n",
    // booking.getBookingId(),
    // booking.getCustomer().getUserId(),
    // booking.getVehicle().getVehicleId(),
    // booking.getCustomer().getUserName(),
    // booking.getCustomer().getContactInfo(),
    // booking.getCustomer().getUserNic(),
    // booking.getCustomer().getAddress(),
    // booking.getCustomer().getEmail(),
    // booking.getCustomer().getCreatedDate(),
    // booking.getVehicle().getVehicleType(),
    // booking.getVehicle().getMake(),
    // booking.getVehicle().getModel(),
    // booking.getVehicle().getVariant(),
    // booking.getVehicle().getSeats(),
    // booking.getVehicle().getVehicleLicenceNumber(),
    // booking.getVehicle().getStatus(),
    // booking.getVehicle().getCreatedDate(),
    // booking.getBookingDate(),
    // booking.getStartDate(),
    // booking.getEndDate(),
    // booking.getRideType(),
    // booking.getPickup(),
    // booking.getDestination(),
    // booking.getBookingType(),
    // booking.getBookingStatus()
    // ));
    // }
    // }

    public void completeBooking(Users users) {
        System.out.println("Enter your booking ID: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        Booking booking = bookingDao.getBookingbyId(id);
        booking.setBookingStatus("Complete");
        booking.setReturnedDate(new Date(System.currentTimeMillis()));
        usersDao.driverStatus(1, booking.getDriver());
        vehicleDao.returnVehicle(booking.getVehicle());

        if (bookingDao.updateBooking(booking) != null) {
            System.out.println("Vehicle Returned\nThanks for your Visit");
        } else {
            System.out.println("Returning Failed");
        }
    }

    public void cancelBooking(Users users) {
        System.out.println("Enter your 'Booking ID' to cancel your Booking: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        Booking booking = bookingDao.getBookingbyId(id);
        booking.setBookingStatus("Canceled");

        if (bookingDao.updateBooking(booking) != null) {
            System.out.println("Booking cancelled");
        } else {
            System.out.println("Cancelation Failed");
        }
    }
    
}
