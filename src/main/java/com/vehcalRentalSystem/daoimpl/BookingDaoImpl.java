package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.BookingDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    @Override
    public List<Booking> fetchAllBookings() {
        List<Booking> bookingList =new ArrayList<>();
        try{
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs= stmt.executeQuery("select booking_id, customer_id, driver_id, vehicle_id, booking_date, start_date, end_date, ride_type, pickup, destination, booking_type, returned_date, booking_status from booking");
            while (rs.next()){
                Booking booking = new Booking();

                booking.setBookingId(rs.getInt("booking_id"));
                //booking.setCustomer(rs.getInt("customer_id"));
                //booking.setCustomer(rs.getuser().getCursorName());
                //booking.setCustomer(rs.getuser().getCursorName());
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setRideType(rs.getString("ride_type"));
                booking.setPickup(rs.getString("pickup"));
                booking.setDestination(rs.getString("destination"));
                booking.setBookingType(rs.getString("booking_type"));
                booking.setReturnedDate(rs.getDate("returned_date"));
                booking.setBookingStatus(rs.getString("booking_status"));

                bookingList.add(booking);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    @Override
    public Integer saveBooking(Booking booking) {
       String sql = "insert into users (booking_id, customer_id, driver_id, vehicle_id, booking_date, start_date, end_date, ride_type, pickup, destination, booking_type, returned_date, booking_status) "
        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, booking.getBookingId());
            statement.setInt(2, booking.getCustomer().getUserId());
            statement.setInt(3, booking.getDriver().getUserId());
            statement.setInt(4, booking.getVehicle().getVehicleId());
            statement.setDate(5, booking.getBookingDate());
            statement.setDate(6, booking.getStartDate());
            statement.setDate(7, booking.getEndDate());
            statement.setString(8, booking.getRideType());
            statement.setString(9, booking.getPickup());
            statement.setString(10, booking.getDestination());
            statement.setString(11, booking.getBookingType());
            statement.setDate(12, booking.getReturnedDate());
            statement.setString(13, booking.getBookingStatus());

            rowsAffected = statement.executeUpdate();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateBooking(Booking booking) {
        return null;
    }

    @Override
    public Integer deleteBooking(Booking booking) {
        return null;
    }
}
