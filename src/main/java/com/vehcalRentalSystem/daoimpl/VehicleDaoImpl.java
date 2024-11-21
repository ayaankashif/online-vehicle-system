package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Vehicle;

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
                    "select vehicle_id,make,model,varient,seats,vehicle_type,vehicle_license_no, "
                    +"created_date,created_by,modified_date,modified_by,is_deleted "
                    + "from vehicle where is_deleted = 0");

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();

                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setVariant(rs.getString("varient"));
                vehicle.setSeats(rs.getString("seats"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setVehicleLicenceNumber(rs.getString("vehicle_license_no"));
                vehicle.setCreatedDate(rs.getDate("created_date"));
                vehicle.setModifiedDate(rs.getDate("modified_date"));
                vehicle.setCreatedBy(rs.getString("created_by"));
                vehicle.setModifiedBy(rs.getString("modified_by"));
                vehicle.setIsDeleted(rs.getInt("is_deleted"));

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
                + "vehicle_license_no, created_date, created_by, modified_date, modified_by, is_deleted) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,'0')";
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
            statement.setDate(8, vehicle.getCreatedDate());
            statement.setString(9, vehicle.getCreatedBy());
            statement.setDate(10, vehicle.getModifiedDate());
            statement.setString(11, vehicle.getModifiedBy());
            statement.setInt(12, vehicle.getIsDeleted());

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
        String sql = "UPDATE vehicle SET make= ?, model = ?, varient = ?, vehicle_type = ?, modified_date = ?"
        + " WHERE vehicle_id = ?";
        
        int rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getVariant());
            statement.setString(4, vehicle.getVehicleType());
            statement.setDate(5, vehicle.getModifiedDate());
            
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
                vehicle.setVehicleId(rs.getInt("vehicleId"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setVariant(rs.getString("varient"));
                vehicle.setSeats(rs.getString("seats"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setVehicleLicenceNumber(rs.getString("vehicle_license_no"));
                vehicle.setCreatedDate(rs.getDate("created_date"));
                vehicle.setCreatedBy(rs.getString("created_by"));
                vehicle.setModifiedDate(rs.getDate("modified_date"));
                vehicle.setModifiedBy(rs.getString("modified_by"));
                vehicle.setIsDeleted(rs.getInt("is_deleted"));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }
}
    