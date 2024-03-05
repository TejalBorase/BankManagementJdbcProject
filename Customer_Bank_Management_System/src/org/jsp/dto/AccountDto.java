package org.jsp.dto;

public class AccountDto {

	private String accountNo;
	private double amount;
	private String accountType;
	private int customerId;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "AccountDto [accountNo=" + accountNo + ", amount=" + amount + ", accountType=" + accountType
				+ ", customerId=" + customerId + "]";
	}

}
