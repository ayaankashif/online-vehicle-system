package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {

     List<Users> findAllUsers();

     Integer saveUser(Users user);

     Integer updateUser(Users user);

     Integer deleteUser(Users user);
}
