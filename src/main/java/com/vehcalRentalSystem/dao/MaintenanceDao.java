package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    List<Maintenance> fetchAllMaintenance();
    
    Integer saveMaintenance(Maintenance maintenance);
    
    Integer updateMaintenance(Maintenance maintenance);

    Integer deleteMaintenance(Maintenance maintenance);

    Maintenance getMaintenanceById(int maintenanceId);    
}
