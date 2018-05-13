package com.abhishek.paymenttracker.domain;

import java.math.BigDecimal;

public class PaymentRecord {

	private String currency;
	private BigDecimal amount;

	public PaymentRecord(String currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String toString() {
		return currency + " " + amount;
	}
}
