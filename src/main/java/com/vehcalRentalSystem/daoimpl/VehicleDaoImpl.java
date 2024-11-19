package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Vehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDao {

    @Override
    public List<Vehicle> fetchAllVehicles() {
        List<Vehicle> listVehicle = new ArrayList<>();
        try{
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select vehicle_id,make,model,varient,seats,vehicle_type,vehicle_license_no,created_date,created_by,modified_date,modified_by from vehicle");
            while (rs.next()){
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
                listVehicle.add(vehicle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listVehicle;
    }

    @Override
    public Integer saveVehicle(Vehicle vehicle) {
        String sql = "insert into vehicle(vehicle_id,make,model) values('"+vehicle.getVehicleId()+"','"+vehicle.getMake()+"','"+vehicle.getModel()+"')";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement stmt= connection.createStatement();
            rowsAffected = stmt.executeUpdate(sql);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Integer deleteVehicle(Vehicle vehicle) {
        return null;
    }
}
