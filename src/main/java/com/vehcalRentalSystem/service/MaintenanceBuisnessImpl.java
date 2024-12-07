package com.vehcalRentalSystem.service;

import java.util.Scanner;

import com.vehcalRentalSystem.dao.MaintenanceDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.daoimpl.MaintenanceDaoImpl;
import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
import com.vehcalRentalSystem.model.Maintenance;
import com.vehcalRentalSystem.model.Vehicle;

public class MaintenanceBuisnessImpl {
    
    VehicleDao vehicleDao = new VehicleDaoImpl();
    Vehicle vehicle = new Vehicle();
    MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();

    public void maintenanceImpl(){
        
        System.out.println("Maintenance Type: ");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        
        System.out.println("Maintenance Status: ");
        String status = scanner.nextLine();

        System.out.println("MAintenance ID: ");
        Integer id = scanner.nextInt();
        
        System.out.println("Enter the Vehicle ID: "); 
        Integer vehicleId = scanner.nextInt();
        vehicle = vehicleDao.getVehicleById(vehicleId);
        vehicleDao.returnVehicle(vehicle);

        Maintenance maintenance = new Maintenance(id, vehicle, type, status);

        if (maintenanceDao.saveMaintenance(maintenance) != null) {
            System.out.println("Vehicle went to maintenance");
        } else{
            System.out.println("failed to return vehicle");
        }        
        scanner.close();
    }
}
