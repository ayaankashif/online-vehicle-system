package com.vehcalRentalSystem;

// import com.vehcalRentalSystem.dao.UsersDao;
// import com.vehcalRentalSystem.dao.VehicleDao;
// import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
// import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
// import com.vehcalRentalSystem.model.Users;
// import com.vehcalRentalSystem.model.Vehicle;
import com.vehcalRentalSystem.service.Menu;
//import com.vehcalRentalSystem.service.UserMenu;
//import com.vehcalRentalSystem.service.VehicleBusinessImpl;
// import java.util.List;
//import com.vehcalRentalSystem.util.validation;

public class App 
{
    public static void main( String[] args ) {       

        // String str = "Moreamore234gmail.com";
        // validation validation = new validation();
        // System.out.println(validation.passwordStrength(str));
        //Users user = new Users();
        Menu menu = new Menu();
        menu.loginMenu();
        //UserMenu userMenu = new UserMenu();
        //VehicleMenu vehicle = new  VehicleMenu();
        //menu.adminMenu();
        //menu.loginMenu();

        // String str = "Waqas_";
        // System.out.println(validation.isValidUserName(str));
        // String str1 = "Ayaan #";
        // System.out.println(validation.isValidUserName(str1));
        // String str2 = "Talha231";
        // System.out.println(validation.isValidUserName(str2));

        //userMenu.registerCustomer();
        

        // Menu menu = new  Menu();
        // userMenu.signUp();
        // userMenu.registerDriver();
        // menu.loginMenu();



        // String str1 = "Hello";
        // String str2 = "Hello";
        // System.out.println("str1 == str2" + str1.equals(str2));

        // UsersDao usersDao = new UserDaoImpl();
        // List<Users> usersList = usersDao.findAllUsers();
        // for(Users user : usersList){
        //     System.out.println("UserName: " + user.getUserName());
        // }

        // //Adding Vehicle
        // Vehicle vehicleDefault = new Vehicle();
        // vehicleDefault.setVehicleId(123);
        // vehicleDefault.setMake("ABC");
        // vehicleDefault.setModel("civic");

        // System.out.println("\nVehicle Details.");
        // VehicleDao vehicleDao = new VehicleDaoImpl();

        // // adding vehicle in db
        // //vehicleDao.saveVehicle(vehicleDefault);
        // //fetch
        // List<Vehicle> vehicleList = vehicleDao.fetchAllVehicles();
        // for (Vehicle vehicle: vehicleList){
        //     System.out.println("Vehicle: " + vehicle.getModel());
        // }

    }
}
