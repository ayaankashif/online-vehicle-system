package com.vehcalRentalSystem.daoimpl;

import com.vehcalRentalSystem.dao.BookingDao;
import com.vehcalRentalSystem.dao.PaymentsDao;
import com.vehcalRentalSystem.db.DatabaseConnection;
import com.vehcalRentalSystem.model.Payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentsDao {
    @Override
    public List<Payments> fetchAllPayments() {
        BookingDao bookingDao = new BookingDaoImpl();
        List<Payments> paymentList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select payment_id, booking_id, amount, payment_date, payment_type, is_deleted"
                    + "from payments where is_deleted = 0");
            while (rs.next()) {
                Payments payments = new Payments();

                payments.setPaymentId(rs.getInt("payment_id"));
                payments.setBooking(bookingDao.getBookingbyId(rs.getInt("booking_id")));
                payments.setAmount(rs.getDouble("amount"));
                payments.setPaymentDate(rs.getDate("payment_date"));
                payments.setPaymentType(rs.getString("payment_type"));
                payments.setIsDeleted(rs.getInt("is_deleted"));

                paymentList.add(payments);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    @Override
    public Integer payment(Payments payments) {
        String sql = "insert into payments ( payment_id, booking_id, amount, payment_date, payment_type, is_deleted) "
                + "values (?,?,?,?,?,'0')";
        Integer rowsAffected = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, payments.getPaymentId());
            statement.setInt(2, payments.getBooking().getBookingId());
            statement.setDouble(3, payments.getAmount());
            statement.setDate(4, payments.getPaymentDate());
            statement.setString(5, payments.getPaymentType());
            statement.setInt(6, payments.getIsDeleted());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    @Override
    public Payments getPaymentsById(int paymentId) {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentsById'");
    }
}
