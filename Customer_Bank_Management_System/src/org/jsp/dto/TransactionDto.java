package org.jsp.dto;

import java.sql.Date;

public class TransactionDto {

	private int tid;
	private String description;
	private double balance;
	private Date date;
	private String transactionType;
	private String accountNo;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "TransactionDto [tid=" + tid + ", description=" + description + ", balance=" + balance + ", date=" + date
				+ ", transactionType=" + transactionType + ", accountNo=" + accountNo + "]";
	}

}
