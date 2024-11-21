package com.vehcalRentalSystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection conn = null;
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/vehical_rental_system";
    private static final String userName = "root";
    private static final String password = "Cyb3r@234";

    private DatabaseConnection(){

    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn == null){
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl,userName,password);
        }
        return conn;
    }
}
