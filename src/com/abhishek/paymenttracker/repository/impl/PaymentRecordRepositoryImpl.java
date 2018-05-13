package com.abhishek.paymenttracker.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.abhishek.paymenttracker.domain.PaymentRecord;
import com.abhishek.paymenttracker.repository.PaymentRecordRepository;

/**
 * Payment record repository implementation
 * 
 * @author Abhishek
 *
 */
public class PaymentRecordRepositoryImpl implements PaymentRecordRepository {

	private Map<String, PaymentRecord> paymentRecordRepo = new ConcurrentHashMap<String, PaymentRecord>();

	@Override
	public void savePaymentRecord(PaymentRecord record) {
		if (paymentRecordRepo.containsKey(record.getCurrency())) {
			PaymentRecord paymentRecord = paymentRecordRepo.get(record.getCurrency());
			paymentRecord.setAmount(paymentRecord.getAmount().add(record.getAmount()));
			paymentRecordRepo.put(paymentRecord.getCurrency(), paymentRecord);
		} else {
			paymentRecordRepo.put(record.getCurrency(), record);
		}
	}

	@Override
	public List<PaymentRecord> getAllPaymentRecords() {
		return new ArrayList<>(paymentRecordRepo.values());
	}
}
