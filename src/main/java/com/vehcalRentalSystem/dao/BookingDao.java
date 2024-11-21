package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Booking;

import java.util.List;

public interface BookingDao {

    List<Booking> fetchAllBookings();
    
    Integer saveBooking(Booking booking);

    Integer updateBooking(Booking booking);
    
    Integer deleteBooking(Booking booking);
    
    Booking getBookingbyId(int bookingId);
}

