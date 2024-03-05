package org.jsp.service;
import org.jsp.dto.AccountDto;
import org.jsp.dto.CustomerDto;
import org.jsp.dto.TransactionDto;
public interface CustomerService {
	int addCustomer(CustomerDto dto);
	
	CustomerDto verifyCustomer(String email, String password);
	
	int addAccount(AccountDto accountDto);
	
	AccountDto verifyAccount(String accountNo);
	
	int updateDepositOrWithdrawalAmount(double amount, String accountNo);
	
	int addTransaction(TransactionDto dto);
}
