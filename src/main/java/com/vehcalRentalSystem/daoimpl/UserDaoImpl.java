package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UsersDao {
    @Override
    public List<Users> findAllUsers() {
        List<Users> usersList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select user_id,user_name,contact_info,user_nic,user_type,address,email,driver_license_number,created_date,modified_date from users");
            while (rs.next()){
                Users user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setContactInfo(rs.getString("contact_info"));
                user.setUserNic(rs.getString("user_nic"));
                user.setUserType(rs.getString("user_type"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setDriverLicenceNumber(rs.getString("driver_license_number"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setCreatedDate(rs.getDate("modified_date"));
                usersList.add(user);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public Integer saveUser(Users user) {
        return null;
    }

    @Override
    public Integer updateUser(Users user) {
        return null;
    }

    @Override
    public Integer deleteUser(Users user) {
        return null;
    }
}
