package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Vehicle;
import com.vehcalRentalSystem.util.TableColumnConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDao {
    @Override
    public List<Vehicle> fetchAllVehicles() {
        List<Vehicle> listVehicle = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select vehicle_id,make,model,varient,seats,vehicle_type,vehicle_license_no, status, "
                    +"created_date,created_by,modified_date,modified_by,is_deleted "
                    + "from vehicle where is_deleted = 0 AND status = 'Available'");

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();

                vehicle.setVehicleId(rs.getInt(TableColumnConstant.VEHICLE_ID));
                vehicle.setMake(rs.getString(TableColumnConstant.MAKE));
                vehicle.setModel(rs.getString(TableColumnConstant.MODEL));
                vehicle.setVariant(rs.getString(TableColumnConstant.VARIANT));
                vehicle.setSeats(rs.getString(TableColumnConstant.SEATS));
                vehicle.setVehicleType(rs.getString(TableColumnConstant.VEHICLE_TYPE));
                vehicle.setVehicleLicenceNumber(rs.getString(TableColumnConstant.VEHICLE_LICENCE_NO));
                vehicle.setStatus(rs.getString(TableColumnConstant.STATUS));
                vehicle.setCreatedDate(rs.getDate(TableColumnConstant.CREATED_DATE));
                vehicle.setModifiedDate(rs.getDate(TableColumnConstant.MODIFIED_DATE));
                vehicle.setCreatedBy(rs.getString(TableColumnConstant.CREATED_BY));
                vehicle.setModifiedBy(rs.getString(TableColumnConstant.MODIFIED_BY));
                vehicle.setIsDeleted(rs.getInt(TableColumnConstant.IS_DELETED));

                listVehicle.add(vehicle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listVehicle;
    }

    @Override
    public Integer saveVehicle(Vehicle vehicle) {
        String sql = "insert into vehicle (vehicle_id, make, model, varient, seats, vehicle_type, "
                + "vehicle_license_no, status, created_date, created_by, modified_date, modified_by, is_deleted) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,'0')";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, vehicle.getVehicleId());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getVariant());
            statement.setString(5, vehicle.getSeats());
            statement.setString(6, vehicle.getVehicleType());
            statement.setString(7, vehicle.getVehicleLicenceNumber());
            statement.setString(8, vehicle.getStatus());
            statement.setDate(9, new java.sql.Date(vehicle.getCreatedDate().getTime()));
            statement.setString(10, vehicle.getCreatedBy());
            statement.setDate(11, vehicle.getModifiedDate());
            statement.setString(12, vehicle.getModifiedBy());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle object cannot be null");
        }
        if (vehicle.getVehicleId() == null) {
            throw new IllegalArgumentException("Vehicle ID is required for updating the vehicle");
        }
        
        String sql = "UPDATE vehicle SET make= ?, model = ?, varient = ?, vehicle_type = ?, status = ?, modified_date = ?, modified_by = ? "
        + " WHERE vehicle_id = ?";
        
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getVariant());
            statement.setString(4, vehicle.getVehicleType());
            statement.setString(5, vehicle.getStatus());
            statement.setDate(6, new java.sql.Date(vehicle.getModifiedDate().getTime()));
            statement.setString(7, vehicle.getModifiedBy());
            statement.setInt(8, vehicle.getVehicleId());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer deleteVehicle(Vehicle vehicle){
    String sql = "update vehicle set is_deleted = 1 where vehicle_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1,vehicle.getVehicleId());
            
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        Vehicle vehicle = null;
        String sql = "select * from vehicle where vehicle_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, vehicleId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt(TableColumnConstant.VEHICLE_ID));
                vehicle.setMake(rs.getString(TableColumnConstant.MAKE));
                vehicle.setModel(rs.getString(TableColumnConstant.MODEL));
                vehicle.setVariant(rs.getString(TableColumnConstant.VARIANT));
                vehicle.setSeats(rs.getString(TableColumnConstant.SEATS));
                vehicle.setVehicleType(rs.getString(TableColumnConstant.VEHICLE_TYPE));
                vehicle.setVehicleLicenceNumber(rs.getString(TableColumnConstant.VEHICLE_LICENCE_NO));
                vehicle.setStatus(rs.getString(TableColumnConstant.STATUS));
                vehicle.setCreatedDate(rs.getDate(TableColumnConstant.CREATED_DATE));
                vehicle.setModifiedDate(rs.getDate(TableColumnConstant.MODIFIED_DATE));
                vehicle.setCreatedBy(rs.getString(TableColumnConstant.CREATED_BY));
                vehicle.setModifiedBy(rs.getString(TableColumnConstant.MODIFIED_BY));
                vehicle.setIsDeleted(rs.getInt(TableColumnConstant.IS_DELETED));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }

    @Override
    public Integer vehicleStatus(Vehicle vehicle) {
        String sql = "update vehicle set status = 'Rented' where vehicle_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,vehicle.getVehicleId());
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer returnVehicle(Vehicle vehicle) {
        String sql = "update vehicle set status = 'Available' where vehicle_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1,vehicle.getVehicleId());
            
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }


    // public Integer vehicleStatus(Vehicle vehicle) {
    //     String sql = "select * from vehicle where status = 'Available' ";
    //     Integer rowsAffected = 0;
    //     try{
    //         Connection connection = DatabaseConnection.getConnection();
    //         PreparedStatement statement = connection.prepareStatement(sql);
    //         ResultSet rs =  statement.executeQuery();

    //         while (rs.next()) {
                
    //         }

    //     }catch (SQLException sqlException){
    //         sqlException.printStackTrace();
    //     } catch (ClassNotFoundException e) {
    //         throw new RuntimeException(e);
    //     }
    //     return rowsAffected;
    // }
}   