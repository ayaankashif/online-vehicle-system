package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.BookingDao;
import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Booking;
import com.vehcalRentalSystem.model.Users;

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
        UsersDao usersDao = new UserDaoImpl();
        VehicleDao vehicleDao = new VehicleDaoImpl();
        List<Booking> bookingList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select booking_id, customer_id, driver_id, vehicle_id, booking_date, start_date, end_date,"
                   +"ride_type, pickup, destination, booking_type, returned_date, booking_status, is_deleted "
                   +"from Booking where is_deleted = 0");
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt("customer_id")));
                booking.setDriver(usersDao.getUserbyId(rs.getInt("driver_id")));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt("vehicle_id")));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setRideType(rs.getString("ride_type"));
                booking.setPickup(rs.getString("pickup"));
                booking.setDestination(rs.getString("destination"));
                booking.setBookingType(rs.getString("booking_type"));
                booking.setReturnedDate(rs.getDate("returned_date"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setIsDeleted(rs.getInt("is_deleted"));

                bookingList.add(booking);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public List<Booking> fetchAllBookings(Users users) {
        UsersDao usersDao = new UserDaoImpl();
        VehicleDao vehicleDao = new VehicleDaoImpl();
        List<Booking> bookingList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select booking_id, customer_id, driver_id, vehicle_id, booking_date, start_date, end_date,"
                    +"ride_type, pickup, destination, booking_type, returned_date, booking_status, is_deleted "
                    +"from Booking where is_deleted = 0 and user_id = ?");
            stmt.setInt(1,users.getUserId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt("customer_id")));
                booking.setDriver(usersDao.getUserbyId(rs.getInt("driver_id")));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt("vehicle_id")));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setRideType(rs.getString("ride_type"));
                booking.setPickup(rs.getString("pickup"));
                booking.setDestination(rs.getString("destination"));
                booking.setBookingType(rs.getString("booking_type"));
                booking.setReturnedDate(rs.getDate("returned_date"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setIsDeleted(rs.getInt("is_deleted"));

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
        String sql = "insert into Booking (booking_id, customer_id, driver_id, vehicle_id, booking_date, "
                +"start_date, end_date, ride_type, pickup, destination, booking_type, returned_date, "
                + "booking_status, is_deleted) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,'0')";
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
            statement.setInt(14, booking.getIsDeleted());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateBooking(Booking booking) {
        String sql = "UPDATE Booking SET start_date = ?, end_date = ?, pickup = ?, destination = ?, booking_type = ?"
        + "returned_date = ?, booking_status = ? WHERE booking_id = ?";
        
        int rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, booking.getStartDate());
            statement.setDate(2, booking.getEndDate());
            statement.setString(3, booking.getPickup());
            statement.setString(4, booking.getDestination());
            statement.setString(5, booking.getBookingType());
            statement.setDate(6, booking.getReturnedDate());
            statement.setString(7, booking.getBookingStatus());
            
            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer deleteBooking(Booking booking) {
        String sql = "update booking set is_deleted = 1 where booking_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1,booking.getBookingId());
            
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Booking getBookingbyId(int bookingId) {
        UsersDao usersDao = new UserDaoImpl();
        VehicleDao vehicleDao = new VehicleDaoImpl();
        Booking booking = null;
        String sql = "select * from Booking where booking_id = ?";
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, bookingId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt("customer_id")));
                booking.setDriver(usersDao.getUserbyId(rs.getInt("driver_id")));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt("vehicle_id")));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setRideType(rs.getString("ride_type"));
                booking.setPickup(rs.getString("pickup"));
                booking.setDestination(rs.getString("destination"));
                booking.setBookingType(rs.getString("booking_type"));
                booking.setReturnedDate(rs.getDate("returned_date"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setIsDeleted(rs.getInt("is_deleted"));
        
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }
}