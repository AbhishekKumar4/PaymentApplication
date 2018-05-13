package com.abhishek.paymenttracker.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import com.abhishek.paymenttracker.domain.PaymentRecord;
import com.abhishek.paymenttracker.repository.PaymentRecordRepository;

public class PaymentRecordParser {

	private PaymentRecordRepository paymentRecordRepository;

	public PaymentRecordParser(PaymentRecordRepository paymentRecordRepository) {
		this.paymentRecordRepository = paymentRecordRepository;
	}

	public void parseAndStorePaymentRecord(String fileName) {
		int lineNumber = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			for (String line; (line = reader.readLine()) != null;) {
				parseRecordAndPersist(line);
				lineNumber++;
			}
		} catch (IOException e) {
			System.out.println("Invalid input on line : " + lineNumber + 1);
			e.printStackTrace();
		}

	}

	public void parseRecordAndPersist(String line) {
		String[] parts = line.split(" ");
		if (parts.length < 2) {
			System.err.println("Error parsing line");
			return;
		} else {
			String currency = parts[0];
			if (currency.length() != 3 && !currency.matches("\\w+")) {
				System.err.println("Error parsing line");
				return;
			}

			if (!parts[1].matches("-?\\d+")) {
				System.err.println("Error parsing line");
				return;
			}
			paymentRecordRepository.savePaymentRecord(new PaymentRecord(currency, new BigDecimal(parts[1])));
		}
	}
}
