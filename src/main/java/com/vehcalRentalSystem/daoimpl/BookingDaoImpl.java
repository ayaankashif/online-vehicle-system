package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.BookingDao;
import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Booking;
import com.vehcalRentalSystem.model.Users;
import com.vehcalRentalSystem.util.TableColumnConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    VehicleDao vehicleDao = new VehicleDaoImpl();
    UsersDao usersDao = new UserDaoImpl();
    @Override
    public List<Booking> fetchAllBookings() {
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
                booking.setBookingId(rs.getInt(TableColumnConstant.BOOKING_ID));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt(TableColumnConstant.CUSTOMER_ID)));
                booking.setDriver(usersDao.getUserbyId(rs.getInt(TableColumnConstant.DRIVER_ID)));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt(TableColumnConstant.VEHICLE_ID)));
                booking.setBookingDate(rs.getDate(TableColumnConstant.BOOKING_DATE));
                booking.setStartDate(rs.getString(TableColumnConstant.START_DATE));
                booking.setEndDate(rs.getString(TableColumnConstant.END_DATE));
                booking.setRideType(rs.getString(TableColumnConstant.RIDE_TYPE));
                booking.setPickup(rs.getString(TableColumnConstant.PICKUP));
                booking.setDestination(rs.getString(TableColumnConstant.DESTINATION));
                booking.setBookingType(rs.getString(TableColumnConstant.BOOKING_TYPE));
                booking.setReturnedDate(rs.getDate(TableColumnConstant.RETURNED_DATE));
                booking.setBookingStatus(rs.getString(TableColumnConstant.BOOKING_STATUS));
                booking.setIsDeleted(rs.getInt(TableColumnConstant.IS_DELETED));
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
            PreparedStatement stmt = conn.prepareStatement("select booking_id, customer_id, driver_id, vehicle_id, booking_date, start_date, end_date, ride_type, pickup, destination, booking_type, returned_date, booking_status, is_deleted from Booking where is_deleted = 0 and customer_id = ?");
            stmt.setInt(1,users.getUserId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt(TableColumnConstant.BOOKING_ID));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt(TableColumnConstant.CUSTOMER_ID)));
                booking.setDriver(usersDao.getUserbyId(rs.getInt(TableColumnConstant.DRIVER_ID)));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt(TableColumnConstant.VEHICLE_ID)));
                booking.setBookingDate(rs.getDate(TableColumnConstant.BOOKING_DATE));
                booking.setStartDate(rs.getString(TableColumnConstant.START_DATE));
                booking.setEndDate(rs.getString(TableColumnConstant.END_DATE));
                booking.setRideType(rs.getString(TableColumnConstant.RIDE_TYPE));
                booking.setPickup(rs.getString(TableColumnConstant.PICKUP));
                booking.setDestination(rs.getString(TableColumnConstant.DESTINATION));
                booking.setBookingType(rs.getString(TableColumnConstant.BOOKING_TYPE));
                booking.setReturnedDate(rs.getDate(TableColumnConstant.RETURNED_DATE));
                booking.setBookingStatus(rs.getString(TableColumnConstant.BOOKING_STATUS));
                booking.setIsDeleted(rs.getInt(TableColumnConstant.IS_DELETED));

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
                + "booking_status, created_date, created_by, modified_date, modified_by, is_deleted) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'0')";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, booking.getBookingId());
            if (booking.getCustomer() == null) {
                statement.setString(2, null);
            } else {
                statement.setInt(2, booking.getCustomer().getUserId());
            }
            if (booking.getDriver() == null) {
                statement.setString(3, null);
            } else {
                statement.setInt(3, booking.getDriver().getUserId());
            }
            statement.setInt(4, booking.getVehicle().getVehicleId());
            statement.setDate(5, new java.sql.Date(booking.getBookingDate().getTime()));
            statement.setString(6, booking.getStartDate());
            statement.setString(7, booking.getEndDate());
            statement.setString(8, booking.getRideType());
            statement.setString(9, booking.getPickup());
            statement.setString(10, booking.getDestination());
            statement.setString(11, booking.getBookingType());
            statement.setDate(12, booking.getReturnedDate());
            statement.setString(13, booking.getBookingStatus());
            statement.setDate(14, booking.getCreatedDate());
            statement.setString(15, booking.getCreatedBy());
            statement.setDate(16, booking.getModifiedDate());
            statement.setString(17, booking.getModifiedBy());

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
        String sql = "UPDATE Booking SET start_date = ?, end_date = ?, pickup = ?, destination = ?, booking_type = ?, "
        + "returned_date = ?, booking_status = ?, modified_date = ?, modified_by = ? WHERE booking_id = ?";
        
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, booking.getStartDate());
            statement.setString(2, booking.getEndDate());
            statement.setString(3, booking.getPickup());
            statement.setString(4, booking.getDestination());
            statement.setString(5, booking.getBookingType());
            statement.setDate(6, booking.getReturnedDate());
            statement.setString(7, booking.getBookingStatus());
            statement.setDate(8, booking.getModifiedDate());
            statement.setString(9, booking.getModifiedBy());
            statement.setInt(10, booking.getBookingId());

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
                booking.setBookingId(rs.getInt(TableColumnConstant.BOOKING_ID));
                booking.setCustomer(usersDao.getUserbyId(rs.getInt(TableColumnConstant.CUSTOMER_ID)));
                booking.setDriver(usersDao.getUserbyId(rs.getInt(TableColumnConstant.DRIVER_ID)));
                booking.setVehicle(vehicleDao.getVehicleById(rs.getInt(TableColumnConstant.VEHICLE_ID)));
                booking.setBookingDate(rs.getDate(TableColumnConstant.BOOKING_DATE));
                booking.setStartDate(rs.getString(TableColumnConstant.START_DATE));
                booking.setEndDate(rs.getString(TableColumnConstant.END_DATE));
                booking.setRideType(rs.getString(TableColumnConstant.RIDE_TYPE));
                booking.setPickup(rs.getString(TableColumnConstant.PICKUP));
                booking.setDestination(rs.getString(TableColumnConstant.DESTINATION));
                booking.setBookingType(rs.getString(TableColumnConstant.BOOKING_TYPE));
                booking.setReturnedDate(rs.getDate(TableColumnConstant.RETURNED_DATE));
                booking.setBookingStatus(rs.getString(TableColumnConstant.BOOKING_STATUS));
                booking.setIsDeleted(rs.getInt(TableColumnConstant.IS_DELETED));
        
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    public boolean hasActiveBooking(int userId) {
        String sql = "SELECT COUNT(*) AS count FROM booking WHERE customer_id = ? AND booking_status = 'active'";
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs =  statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

