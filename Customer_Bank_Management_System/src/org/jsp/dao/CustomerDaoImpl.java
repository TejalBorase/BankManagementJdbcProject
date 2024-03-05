package org.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsp.dto.AccountDto;
import org.jsp.dto.CustomerDto;
import org.jsp.dto.TransactionDto;
import org.jsp.utility.Singleton;

public class CustomerDaoImpl implements CustomerDao {

	private Singleton singleton = Singleton.getSingletonClassObject();
	private Connection connection = singleton.getDatabaseConnectionObject();
	private PreparedStatement pstmt = null;

	@Override
	public int addCustomer(CustomerDto customerDto) throws Exception {

		String query = "insert into customer_Details "
				+ "(firstName, lastName, mobile, email, aadharNo, pancard, DateOfBirth," + " gender, address, password)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";

		pstmt = connection.prepareStatement(query);

		// take data from dto and assign to placeholder
		pstmt.setString(1, customerDto.getFirstName());
		pstmt.setString(2, customerDto.getLastName());
		pstmt.setLong(3, customerDto.getMobile());
		pstmt.setString(4, customerDto.getEmail());
		pstmt.setLong(5, customerDto.getAadharNo());
		pstmt.setString(6, customerDto.getPancardNo());
		pstmt.setDate(7, customerDto.getDateOfBirth());
		pstmt.setString(8, customerDto.getGender());
		pstmt.setString(9, customerDto.getAddress());
		pstmt.setString(10, customerDto.getPassword());

		// Execute query
		int status = pstmt.executeUpdate();

		return status;
	}

	@Override
	public CustomerDto verifyCustomer(String email, String password) throws Exception {

		String query = "select * from customer_Details where email = ? and password = ?";

		pstmt = connection.prepareStatement(query);

		pstmt.setString(1, email);
		pstmt.setString(2, password);

		CustomerDto customerDto = null;

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			// customer is valid
			// Step1 : Fetch data from Database
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			// Step2 : Store data in CustomerDto object
			customerDto = new CustomerDto();
			customerDto.setFirstName(firstName);
			customerDto.setLastName(lastName);
			customerDto.setMobile(rs.getLong("mobile"));
			customerDto.setAadharNo(rs.getLong("aadharNo"));
			customerDto.setPancardNo(rs.getString("pancard"));
			customerDto.setDateOfBirth(rs.getDate("DateOfBirth"));
			customerDto.setEmail(email);
			customerDto.setId(rs.getInt("customerId"));
			System.out.println(customerDto);
			return customerDto;
		}
		return null;
	}

	@Override
	public int addAccount(AccountDto accountDto) throws Exception {
		String query = "insert into account_Details values(?,?,?,?)";

		pstmt = connection.prepareStatement(query);

		pstmt.setString(1, accountDto.getAccountNo());
		pstmt.setString(2, accountDto.getAccountType());
		pstmt.setDouble(3, accountDto.getAmount());
		pstmt.setInt(4, accountDto.getCustomerId());

		int status = pstmt.executeUpdate();
		return status;
	}

	@Override
	public AccountDto verifyAccount(String accountNo) throws Exception {
		String query = "select * from account_details where accountNo = ?";
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, accountNo);
		AccountDto accountDto = null;
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			accountDto = new AccountDto();
			accountDto.setAccountNo(accountNo);
			accountDto.setAccountType(rs.getString("accountype"));
			accountDto.setAmount(rs.getDouble("amount"));
			accountDto.setCustomerId(rs.getInt("customerId"));
			System.out.println(accountDto);
			return accountDto;
		}
		return null;
	}

	@Override
	public int updateDepositOrWithdrawalAmount(double amount, String accountNo) throws Exception {
		String query = "update account_details set amount = ? where accountNo = ? ";

		pstmt = connection.prepareStatement(query);
		pstmt.setDouble(1, amount);
		pstmt.setString(2, accountNo);

		int status = pstmt.executeUpdate();

		return status;
	}

	@Override
	public int addTransaction(TransactionDto dto) throws Exception {
		String query = "insert into transaction (description, balance, DateAndTime, "
				+ "transactiontype, accountNo)values(?,?,?,?,?)";
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, dto.getDescription());
		pstmt.setDouble(2, dto.getBalance());
		pstmt.setDate(3, dto.getDate());
		pstmt.setString(4, dto.getTransactionType());
		pstmt.setString(5, dto.getAccountNo());

		int status = pstmt.executeUpdate();
		return status;
	}

	@Override
	public List<TransactionDto> viewAllTransaction(String accountNo) throws Exception {
		String query = "select * from transaction where accountNo = ?";
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, accountNo);
		ResultSet rs = pstmt.executeQuery();
		if (rs.isBeforeFirst()) {
			List<TransactionDto> listOfTransactions = new ArrayList<TransactionDto>();
			while (rs.next()) {
				TransactionDto dto = new TransactionDto();
				dto.setAccountNo(accountNo);
				dto.setBalance(rs.getDouble("balance"));
				dto.setDate(rs.getDate("DateAndTime"));
				dto.setTid(rs.getInt("transactionId"));
				dto.setDescription(rs.getString("description"));
				dto.setTransactionType(rs.getString("transactionType"));

				listOfTransactions.add(dto);
			}
			return listOfTransactions;
		}
		return null;
	}

}
