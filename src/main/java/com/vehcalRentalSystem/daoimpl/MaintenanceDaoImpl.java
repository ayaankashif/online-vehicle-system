package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.MaintenanceDao;
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
        List<Maintenance> maintenanceList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select maintenance_id, vehicle_id, maintenance_type, maintenance_status from maintenance");
            while (rs.next()){
                Maintenance maintenance = new Maintenance();
                
                maintenance.setMaintenanceId(rs.getInt("Maintenance_id"));
                //maintenance.setVehicle(rs.getInt("vehicle_id"));
                maintenance.setMaintenanceType(rs.getString("maintenance_type"));
                maintenance.setMaintenanceStatus(rs.getString("maintenance_status"));
               
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
    public Integer saveMaintenance (Maintenance maintenance) {
        String sql = "insert into maintenance (maintenance_id, vehicle_id, maintenance_type, maintenance_status) "
        + "values (?,?,?,?)";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, maintenance.getMaintenanceId());
            preparedStatement.setInt(3, maintenance.getVehicle().getVehicleId());
            preparedStatement.setString(2, maintenance.getMaintenanceType());

            rowsAffected = preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateMaintenance(Maintenance maintenance) {
        return null;
    }

    @Override
    public Integer deleteMaintenance(Maintenance maintenance) {
        return null;
    }
}
