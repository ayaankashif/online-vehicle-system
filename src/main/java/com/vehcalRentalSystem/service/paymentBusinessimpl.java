package com.vehcalRentalSystem.service;

import java.sql.Date;
import java.util.Scanner;

import com.vehcalRentalSystem.dao.PaymentsDao;
import com.vehcalRentalSystem.daoimpl.PaymentDaoImpl;
import com.vehcalRentalSystem.model.Booking;
import com.vehcalRentalSystem.model.Payments;

public class paymentBusinessimpl {

    paymentBusinessimpl paymentBusinessimpl = new paymentBusinessimpl();
    PaymentsDao paymentsDao = new PaymentDaoImpl();

    public void paymentImpl(Booking booking){
        System.out.println("Amount: ");
        Scanner scanner  = new Scanner(System.in);
        double amount = scanner.nextDouble();
        System.out.println("Payment Type: ");
        String type = scanner.nextLine();
        System.out.println("Payment ID : ");
        Integer id = scanner.nextInt();

        Payments payments = new Payments(id, booking, amount, new Date(System.currentTimeMillis()), type);

        if (paymentsDao.payment(payments) != null) {
            System.out.println("Payment Done");
        } else {
            System.out.println("Payment failed");
        }
        scanner.close();
    }

    public double calculateAmount(double amount){
        double payment = 2500;
        return amount * payment;
    }

    public double amountWithDriver(double amount){
        double driver = 2000;
        double payment = 2500;
        return amount * payment + driver; 
    }

}
