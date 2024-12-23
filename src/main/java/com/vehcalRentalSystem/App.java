package com.vehcalRentalSystem;

// import java.util.logging.LoggingMXBean;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

// import com.mysql.cj.log.Log;
//import com.vehcalRentalSystem.model.Users;
// import com.vehcalRentalSystem.dao.UsersDao;
// import com.vehcalRentalSystem.dao.VehicleDao;
// import com.vehcalRentalSystem.daoimpl.UserDaoImpl;
// import com.vehcalRentalSystem.daoimpl.VehicleDaoImpl;
// import com.vehcalRentalSystem.model.Users;
// import com.vehcalRentalSystem.model.Vehicle;
import com.vehcalRentalSystem.service.Menu;
// import com.vehcalRentalSystem.service.UserBusinessImpl;
//import com.vehcalRentalSystem.service.UserMenu;
//import com.vehcalRentalSystem.service.VehicleBusinessImpl;
// import java.util.List;

// import com.vehcalRentalSystem.service.VehicleBusinessImpl;
// import com.vehcalRentalSystem.service.bookingBusinessImpl;

public class App 
{
    //private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args ) {       

        //LOGGER.info("disao");
        // String str = "Talha 2";
        // validation validation = new validation();
        // System.out.println(validation.isValidName(str));
        // Users users = new Users();
    
        Menu menu = new Menu();
        menu.loginMenu();

        // bookingBusinessImpl bookingBusinessImpl = new bookingBusinessImpl();
        // bookingBusinessImpl.bookingHistory();

        // UserBusinessImpl userBusinessImpl = new  UserBusinessImpl();
        // userBusinessImpl.showCustomer();
        // System.out.println("\n\n");
        // VehicleBusinessImpl vehcalRentalSystem = new VehicleBusinessImpl();
        // vehcalRentalSystem.showVehicle();
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
