package com.abhishek.paymenttracker.repository.reader;

import java.util.List;

import com.abhishek.paymenttracker.domain.PaymentRecord;
import com.abhishek.paymenttracker.repository.PaymentRecordRepository;

public class PaymentRecordRepositoryReader {

	private PaymentRecordRepository paymentRecordRepository;

	public PaymentRecordRepositoryReader(PaymentRecordRepository paymentRecordRepository) {
		this.paymentRecordRepository = paymentRecordRepository;
	}

	public List<PaymentRecord> viewAllPaymentRecords() {
		return paymentRecordRepository.getAllPaymentRecords();
	}

}
