package org.jsp.dao;

import java.util.List;

import org.jsp.dto.AccountDto;
import org.jsp.dto.CustomerDto;
import org.jsp.dto.TransactionDto;

public interface CustomerDao {

	// for registration
	int addCustomer(CustomerDto customerDto) throws Exception;

//	for login
	CustomerDto verifyCustomer(String email, String password) throws Exception;

//	
//	to open an account
//	while opening an account atleast user should deposit 500rs.
	int addAccount(AccountDto accountDto) throws Exception;

//	
//	to access account
	AccountDto verifyAccount(String accountNo) throws Exception;

	//method to update amount in accountdetails after deposit or Withdraw
	int updateDepositOrWithdrawalAmount(double amount, String accountNo) throws Exception;
	
	//add trasanction details
	int addTransaction(TransactionDto dto) throws Exception;
			

	List<TransactionDto> viewAllTransaction(String accountNo) throws Exception;
	
	/*
	 * Firstly check whether receiverAccountNo is valid or not
	 * If it is valid then add respective amount with existing balance of receiver 
	 * and update in a table as well as add transaction in transaction table 
	 * (credited from senderAccountNo (Rs. Amount))
	 * 
	 * Then deduct respective amount from sender account and update in a table
	 * as well as add transaction in table (debited to receiverAccountNo (Rs. Amount))
	 */
	int transferMoney(String senderAccountNo, String receiverAccountNo, double amount) 
			throws Exception;
}











