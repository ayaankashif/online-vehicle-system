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
                    "select user_id,user_name,contact_info,user_nic,user_type,address,email,"
                    +"driver_license_number, password, created_date,modified_date,is_deleted "
                    +"from users where is_deleted = 0");
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
                user.setPassword(rs.getString("password"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setCreatedDate(rs.getDate("modified_date"));
                user.setIsDeleted(rs.getInt("is_deleted"));

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
        String sql = "insert into users (user_id, user_name, contact_info, user_nic, user_type, address, "
                + "email, driver_license_number, password, created_date, modified_date, is_deleted) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,'0')";
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
            statement.setString(9, user.getPassword());
            statement.setDate(10, new java.sql.Date(user.getCreatedDate().getTime()));
            statement.setDate(11, user.getModifiedDate());

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
        String sql = "UPDATE users SET user_name = ?, contact_info = ?, user_nic = ?, address = ?, email = ?, "
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
            statement.setDate(7, new java.sql.Date(user.getModifiedDate().getTime()));
            statement.setInt(8, user.getUserId());
            
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
        String sql = "update Users set is_deleted = 1 where user_id = ?";
        Integer rowsAffected = 0;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user.getUserId());
            rowsAffected =  statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Users getUserbyId(int userId) {
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
                user.setPassword(rs.getString("password"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setModifiedDate(rs.getDate("modified_date"));
                user.setIsDeleted(rs.getInt("is_deleted"));        
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public Users userLogin(String username,String password) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("select user_id,user_name,contact_info,user_nic,user_type,address,email, "
            +"driver_license_number,password,created_date,modified_date,is_deleted from users where user_name = ? and password = ?");
            
            stmt.setString(1,username);
            stmt.setString(2,password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setContactInfo(rs.getString("contact_info"));
                user.setUserNic(rs.getString("user_nic"));
                user.setUserType(rs.getString("user_type"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setDriverLicenceNumber(rs.getString("driver_license_number"));
                user.setPassword(rs.getString("password"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setCreatedDate(rs.getDate("modified_date"));
                user.setIsDeleted(rs.getInt("is_deleted"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}