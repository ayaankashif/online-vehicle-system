package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> fetchAllVehicles();

    Integer saveVehicle(Vehicle vehicle);

    Integer updateVehicle(Vehicle vehicle);

    Integer deleteVehicle(Vehicle vehicle);
}
