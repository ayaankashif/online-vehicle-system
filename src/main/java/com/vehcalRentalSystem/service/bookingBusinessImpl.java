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

public class bookingBusinessImpl {
    UsersDao usersDao = new UserDaoImpl();
    VehicleDao vehicleDao = new VehicleDaoImpl();
    BookingDao bookingDao = new BookingDaoImpl();
    VehicleBusinessImpl vehicleBusinessImpl = new VehicleBusinessImpl();

    public void bookingImpl(Users users) {
        System.out.println("Start Date: ");
        Scanner scanner = new Scanner(System.in);
        String startDate = scanner.nextLine();
        System.out.println("End Date : ");
        String endDate = scanner.nextLine();
        System.out.println("Ride Type: ");
        String rideType = scanner.nextLine();
        System.out.println("Booking Type: ");
        String bookType = scanner.nextLine();
        System.out.println("Booking Status");
        String status = scanner.nextLine();
        System.out.println("Booking ID: ");
        Integer id = scanner.nextInt();
        System.out.println("User ID: ");
        Integer Userid = scanner.nextInt();
        vehicleBusinessImpl.showVehicle();
        System.out.println("Select your Vehicle by ID: ");
        Integer vehicleId = scanner.nextInt();

        users = usersDao.getUserbyId(Userid);
        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);

        Booking booking = new Booking(id, users, null, vehicle, new Date(System.currentTimeMillis()),
                startDate, endDate, rideType, null, null, bookType, null, status, new Date(System.currentTimeMillis()),
                null, null);

        if (bookingDao.saveBooking(booking) != null) {
            System.out.println("Booking Successfull");
        } else {
            System.out.println("Booking Failed");
        }
        scanner.close();
    }

    public void updateBooking(Users users) {
        System.out.println("Start Date: ");
        Scanner scanner = new Scanner(System.in);
        String startDate = scanner.nextLine();
        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
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
        System.out.println("User ID: ");
        Integer userId = scanner.nextInt();

        Users user = usersDao.getUserbyId(userId);

        Booking booking = new Booking(id, user, null, null, null,
                startDate, endDate, null, null, null, bookType, retDate, status, null,
                new Date(System.currentTimeMillis()), modifyBy);

        if (bookingDao.updateBooking(booking) != null) {
            System.out.println("Booking Updated");
        } else {
            System.out.println("Failed to update");
        }
        scanner.close();
    }

    public void bookingHistory(Users users) {
        List<Booking> bookingList = bookingDao.fetchAllBookings();
        System.out.println("Booking List");
        for (Booking booking : bookingList) {
            System.out.println(String.format(
                    "Booking ID %s\nCustomer ID: %s\nVehicle Id: %s\nBooking Date: %s\n"
                            + "Start Date: %s\nEnd Date: %s\nRide type: %s\nPickup: %s\nDestination: %s\nBooking Type: %s\n"
                            + "Booking Status: %s\n",
                    booking.getBookingId(),
                    booking.getCustomer().getUserId(),
                    booking.getVehicle().getVehicleId(),
                    booking.getCustomer().getUserName(),
                    booking.getCustomer().getContactInfo(),
                    booking.getCustomer().getUserNic(),
                    booking.getCustomer().getUserType(),
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
                    booking.getVehicle().getCreatedBy(),
                    booking.getBookingDate(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getRideType(),
                    booking.getPickup(),
                    booking.getDestination(),
                    booking.getBookingType(),
                    booking.getBookingStatus()
            ));
        }
    }
}
