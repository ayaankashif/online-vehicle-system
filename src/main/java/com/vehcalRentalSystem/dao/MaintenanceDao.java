package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    List<Maintenance> fetchAllMaintenance();
    Integer saveMaintenance();
    Integer updateMaintenance();
    Integer deleteMaintenance();
}
