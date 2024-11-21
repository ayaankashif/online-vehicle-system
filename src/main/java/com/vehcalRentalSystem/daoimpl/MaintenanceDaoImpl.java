package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.MaintenanceDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Maintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDaoImpl implements MaintenanceDao {
    @Override
    public List<Maintenance> fetchAllMaintenance() {
        VehicleDao vehicleDao = new VehicleDaoImpl();
        List<Maintenance> maintenanceList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select maintenance_id, vehicle_id, maintenance_type, maintenance_status, is_deleted "
                    +"from maintenance where is_deleted = 0");
            while (rs.next()) {
                Maintenance maintenance = new Maintenance();

                maintenance.setMaintenanceId(rs.getInt("Maintenance_id"));
                maintenance.setVehicle(vehicleDao.getVehicleById(rs.getInt("vehicle_id")));
                maintenance.setMaintenanceType(rs.getString("maintenance_type"));
                maintenance.setMaintenanceStatus(rs.getString("maintenance_status"));
                maintenance.setIsDeleted(rs.getInt("is_deleted"));

                maintenanceList.add(maintenance);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maintenanceList;
    }

    @Override
    public Integer saveMaintenance(Maintenance maintenance) {
        String sql = "insert into maintenance (maintenance_id, vehicle_id, maintenance_type,"
                    +"maintenance_status, is_deleted) "
                    + "values (?,?,?,?,'0')";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, maintenance.getMaintenanceId());
            preparedStatement.setInt(2, maintenance.getVehicle().getVehicleId());
            preparedStatement.setString(3, maintenance.getMaintenanceType());
            preparedStatement.setString(4, maintenance.getMaintenanceStatus());
            preparedStatement.setInt(5, maintenance.getIsDeleted());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateMaintenance(Maintenance maintenance) {
        String sql = "UPDATE maintenance SET maintenance_type= ?, maintenance_status = ?"
        + " WHERE maintenance_id = ?";
        
        int rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, maintenance.getMaintenanceType());
            statement.setString(2, maintenance.getMaintenanceStatus());
            
            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer deleteMaintenance(Maintenance maintenance) {
        String sql = "update maintenance set is_deleted = 1 where maintenance_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,maintenance.getMaintenanceId());
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Maintenance getMaintenanceById(int maintenanceId) {
        throw new UnsupportedOperationException("Unimplemented method 'getMaintenanceById'");
    }
}