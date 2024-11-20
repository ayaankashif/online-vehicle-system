package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.PaymentsDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Maintenance;
import com.vehcalRentalSystem.model.Payments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentsDao {
    @Override
    public List<Payments> fetchAllPayments() {
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
    public Integer payment(Payments payments) {
        return null;
    }
}
