package com.abhishek.paymenttracker.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.abhishek.paymenttracker.constants.ApplicationConstants;
import com.abhishek.paymenttracker.parser.PaymentRecordParser;
import com.abhishek.paymenttracker.repository.PaymentRecordRepository;
import com.abhishek.paymenttracker.repository.impl.PaymentRecordRepositoryImpl;
import com.abhishek.paymenttracker.repository.reader.PaymentRecordRepositoryReader;
import com.abhishek.paymenttracker.writer.ConsoleWriter;

/**
 * Main class implementation
 * 
 * @author Abhishek
 * 
 */
public class ApplicationRunner {

	private static PaymentRecordRepository paymentRecordRepository;
	private static Thread consoleWriterThread;

	public static void initPaymentRecordRepository() {
		paymentRecordRepository = new PaymentRecordRepositoryImpl();
	}

	public static void main(String args[]) {
		initPaymentRecordRepository();
		execute();
	}

	private static void execute() {
		try (BufferedReader consoleBufferReader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the path of payment records file (if none, the default file will be used): ");

			PaymentRecordParser parser = new PaymentRecordParser(paymentRecordRepository);
			String line = consoleBufferReader.readLine();

			if (line.equalsIgnoreCase(ApplicationConstants.QUIT)) {
				return;
			}
			loadAndParseFile(line, parser);
			consoleWriterThread = new Thread(
					new ConsoleWriter(new PaymentRecordRepositoryReader(paymentRecordRepository)));
			consoleWriterThread.start();
			System.out.println("Enter new payment record or type 'QUIT' for exit.");
			while (!(line = consoleBufferReader.readLine()).equalsIgnoreCase("quit")) {
				parser.parseRecordAndPersist(line);
			}

		} catch (IOException e) {
			System.out.println("Error while parsing input.");
			e.printStackTrace();
		} finally {
			if (consoleWriterThread != null) {
				consoleWriterThread.interrupt();
			}
		}
		System.out.println("Finished.");
	}

	private static void loadAndParseFile(String fileName, PaymentRecordParser parser) {
		if (fileName == null || fileName.length() == 0) {
			// no record file provided by user
			fileName = ApplicationConstants.DEFAULT_FILE;
		}
		parser.parseAndStorePaymentRecord(fileName);
	}
}
