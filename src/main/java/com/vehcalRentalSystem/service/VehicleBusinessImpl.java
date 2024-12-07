package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
import com.vehcalRentalSystem.model.Vehicle;

public class VehicleBusinessImpl {

    VehicleDao vehicleDao = new VehicleDaoImpl();
    UserBusinessImpl usersmenu = new UserBusinessImpl();


    public void registerVehicle() {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();
        System.out.print("Vehicle make ");
        String make = scanner.nextLine();
        System.out.print("vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle variant: ");
        String variant = scanner.nextLine();
        System.out.print("Vehicle seats: ");
        String seats = scanner.nextLine();
        System.out.print("Vehicle Licene Plate: ");
        String licenseNo = scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();
        System.out.print("Created by: ");
        String createdBy = scanner.nextLine();
        System.out.print("Vehicle ID: ");
        Integer id = scanner.nextInt(); 
        
        Vehicle vehicle = new Vehicle(type, make, model, variant, seats, licenseNo, status,
                new Date(System.currentTimeMillis()), createdBy, null, null, id);

        if (vehicleDao.saveVehicle(vehicle) != null) {
            System.out.println("Vehicle Registered");
        } else {
            System.out.println("Vehicle Registration Failed");
        }
        scanner.close();
    }

    public void updateVehicle(){
        
        System.out.print("Vehicle type: ");
        Scanner scanner = new Scanner(System.in);  
        String type = scanner.nextLine();
        System.out.print("Vehicle make ");
        String make = scanner.nextLine();
        System.out.print("vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle variant: ");
        String variant = scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();
        System.out.print("Modified by: ");
        String modifiedBy = scanner.nextLine();
        System.out.print("Vehicle ID: ");
        Integer id = scanner.nextInt(); 

        Vehicle vehicle = new Vehicle(type, make, model, variant, null, null, 
                status, null, null, new Date(System.currentTimeMillis()), modifiedBy, id);

        if (vehicleDao.updateVehicle(vehicle) != null) {
            System.out.println("Vehicle updated successfully");
        } else {
            System.out.println("Failed to update");
        }
        scanner.close();
    }

    public void showVehicle(){
        List<Vehicle> vehicleList = vehicleDao.fetchAllVehicles();
        System.out.println("Available Vehicle's List:\n");
        System.out.printf("%-13s %-12s %-10s %-10s %-15s %-10s %-15s %-13s %-15s %-15s%n", 
                      "Vehicle ID", "Type", "Make", "Model",  "Variant", "Seats", "License Plate", "Status", "Created date", "Created by");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

        for (Vehicle vehicle : vehicleList) {
            System.out.printf("%-13s %-12s %-10s %-10s %-15s %-10s %-15s %-13s %-15s %-15s%n",
                vehicle.getVehicleId(),
                vehicle.getVehicleType(), 
                vehicle.getMake(), 
                vehicle.getModel(), 
                vehicle.getVariant(), 
                vehicle.getSeats(), 
                vehicle.getVehicleLicenceNumber(), 
                vehicle.getStatus(), 
                vehicle.getCreatedDate(), 
                vehicle.getCreatedBy());
        }
    }
}
