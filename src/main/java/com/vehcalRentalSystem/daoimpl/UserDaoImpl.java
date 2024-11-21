package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.UsersDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UsersDao {
    @Override
    public List<Users> findAllUsers() {
        List<Users> usersList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select user_id,user_name,contact_info,user_nic,user_type,address,email,driver_license_number,created_date,modified_date from users");
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
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
        String sql = "insert into users (user_id, user_name, contact_info, user_nic, user_type, address, email, driver_license_number, created_date, modified_date) "
                + "values (?,?,?,?,?,?,?,?,?,?)";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getContactInfo());
            statement.setString(4, user.getUserNic());
            statement.setString(5, user.getUserType());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getDriverLicenceNumber());
            statement.setDate(9, user.getCreatedDate());
            statement.setDate(10, user.getModifiedDate());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer updateUser(Users user) {
        String sql = "UPDATE users SET user_name = ?, contact_info = ?, user_nic = ?, address = ?, email = ?"
        + "driver_license_number = ?, modified_date = ? WHERE user_id = ?";
        
        int rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getContactInfo());
            statement.setString(3, user.getUserNic());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getDriverLicenceNumber());
            
            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Integer deleteUser(Users user) {
        return null;
    }

    @Override
    public Users getcustomerbyId(int userId) {
        Users user = null;
        String sql = "select * from Users where user_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, userId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setContactInfo(rs.getString("contact_info"));
                user.setUserNic(rs.getString("user_nic"));
                user.setUserType(rs.getString("user_type"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setDriverLicenceNumber(rs.getString("driver_license_number"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setModifiedDate(rs.getDate("modified_date"));
        
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
