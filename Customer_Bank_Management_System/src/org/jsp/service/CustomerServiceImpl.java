package org.jsp.service;

import org.jsp.dao.CustomerDao;
import org.jsp.dto.AccountDto;
import org.jsp.dto.CustomerDto;
import org.jsp.dto.TransactionDto;
import org.jsp.utility.CustomerManager;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao = CustomerManager.getCustomerDaoObject();

	@Override
	public int addCustomer(CustomerDto dto) {
		int status = 0;
		try {
			status = dao.addCustomer(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public CustomerDto verifyCustomer(String email, String password) {
		try {
			//return dao.verifyCustomer(email,password);
			CustomerDto dto = dao.verifyCustomer(email, password);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addAccount(AccountDto accountDto) {
		try {
			int status = dao.addAccount(accountDto);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public AccountDto verifyAccount(String accountNo) {
		try {
			AccountDto dto = dao.verifyAccount(accountNo);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateDepositOrWithdrawalAmount(double amount, String accountNo) {
		try {
			return dao.updateDepositOrWithdrawalAmount(amount, accountNo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int addTransaction(TransactionDto dto) {
		try {
			return dao.addTransaction(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}











