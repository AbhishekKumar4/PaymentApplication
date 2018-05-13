package com.abhishek.paymenttracker.writer;

import java.util.List;

import com.abhishek.paymenttracker.domain.PaymentRecord;
import com.abhishek.paymenttracker.repository.reader.PaymentRecordRepositoryReader;

/**
 * Implementation responsible for writing records on console.
 * 
 * @author Abhishek
 */
public class ConsoleWriter implements Runnable {
	private PaymentRecordRepositoryReader paymentRecordRepositoryReader;

	public ConsoleWriter(PaymentRecordRepositoryReader paymentRecordRepositoryReader) {
		this.paymentRecordRepositoryReader = paymentRecordRepositoryReader;
	}

	public void writeAllRecords() {
		if (paymentRecordRepositoryReader != null) {
			List<PaymentRecord> records = paymentRecordRepositoryReader.viewAllPaymentRecords();
			for (PaymentRecord paymentRecord : records) {
				if (paymentRecord.getAmount().intValue() != 0) {
					String recordString = paymentRecord.getCurrency().toUpperCase() + " " + paymentRecord.getAmount();
					System.out.println(recordString);
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				writeAllRecords();
				Thread.sleep(60 * 1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting application !!!");
		}
	}
}
