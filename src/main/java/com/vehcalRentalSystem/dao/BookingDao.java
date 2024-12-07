package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Booking;
import com.vehcalRentalSystem.model.Users;

import java.util.List;

public interface BookingDao {

    List<Booking> fetchAllBookings();
    
    List<Booking> fetchAllBookings(Users users);
    
    Integer saveBooking(Booking booking);

    Integer updateBooking(Booking booking);
    
    Integer deleteBooking(Booking booking);
    
    Booking getBookingbyId(int bookingId);

    Booking bookingStatus();

    //Booking getBooking();
}

