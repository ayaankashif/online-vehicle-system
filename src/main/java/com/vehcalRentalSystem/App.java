package com.vehcalRentalSystem;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.dao.VehicleDao;
import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
import com.vehcalRentalSystem.model.Users;
import com.vehcalRentalSystem.model.Vehicle;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println("str1 == str2" + str1.equals(str2));

        UsersDao usersDao = new UserDaoImpl();
        List<Users> usersList = usersDao.findAllUsers();
        for(Users user : usersList){
            System.out.println("UserName: " + user.getUserName());
        }

        //Adding Vehicle
        Vehicle vehicleDefault = new Vehicle();
        vehicleDefault.setVehicleId(123);
        vehicleDefault.setMake("ABC");
        vehicleDefault.setModel("civic");


        System.out.println("\nVehicle Details.");
        VehicleDao vehicleDao = new VehicleDaoImpl();

        // adding vehicle in db
        //vehicleDao.saveVehicle(vehicleDefault);
        //fetch
        List<Vehicle> vehicleList = vehicleDao.fetchAllVehicles();
        for (Vehicle vehicle: vehicleList){
            System.out.println("Vehicle: " + vehicle.getModel());
        }

    }
}
