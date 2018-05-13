package com.abhishek.paymenttracker.repository;

import java.util.List;

import com.abhishek.paymenttracker.domain.PaymentRecord;

/**
 * Interface to access Payment Record Repository
 * 
 * @author Abhishek
 */
public interface PaymentRecordRepository {

	void savePaymentRecord(PaymentRecord record);

	List<PaymentRecord> getAllPaymentRecords();
}
