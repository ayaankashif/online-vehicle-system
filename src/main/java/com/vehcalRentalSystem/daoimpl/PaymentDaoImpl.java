package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.PaymentsDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Maintenance;
import com.vehcalRentalSystem.model.Payments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentsDao {
    @Override
    public List<Payments> fetchAllPayments() {
    List<Payments> paymentList = new ArrayList<>();
    try{
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select payment_id, booking_id, amount, payment_date, payment_type from payments");
        while (rs.next()){
            Payments payments = new Payments();
            
            payments.setPaymentId(rs.getInt("payment_id"));
            //maintenance.setbooking(rs.getInt("vehicle_id"));
            payments.setAmount(rs.getDouble("amount"));
            payments.setPaymentDate(rs.getDate("payment_date"));
            payments.setPaymentType(rs.getString("payment_type"));
           
            paymentList.add(payments);
        }
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return paymentList;

    @Override
    public Integer payment(Payments payments) {
        return null;
    }
}

