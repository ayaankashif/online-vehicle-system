package com.vehcalRentalSystem.dao;

import com.vehcalRentalSystem.model.Payments;

import java.util.List;

public interface PaymentsDao {

    List<PaymentsDao> fetchAllPayments();
    Integer payment(Payments payments);

}
