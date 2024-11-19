package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Booking;

import java.util.List;

public interface BookingDao {

    List<Booking> fetchAllBookings();
    Integer saveBooking();
    Integer updateBooking();
    Integer deleteBooking();
}
