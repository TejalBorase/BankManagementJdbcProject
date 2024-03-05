package org.jsp.controller;

import java.sql.Date;

import java.time.LocalDate;
import java.util.Scanner;

import org.jsp.dto.AccountDto;
import org.jsp.dto.CustomerDto;
import org.jsp.dto.TransactionDto;
import org.jsp.service.CustomerService;
import org.jsp.utility.CustomerManager;
import org.jsp.utility.GenerateAccountNumber;

public class CustomerController extends Thread {

	Scanner sc = new Scanner(System.in);

	public void performOperation() {

		CustomerService service = CustomerManager.getCustomerServiceObject();

		System.out.println("Enter 1 For Registration");
		System.out.println("Enter 2 For Login");
		System.out.print("Enter Your choice: ");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			// registration
			CustomerDto dto = new CustomerDto();
			System.out.println("Enter First Name");
			dto.setFirstName(sc.next());
			System.out.println("Enter Last Name");
			dto.setLastName(sc.next());
			System.out.println("Enter Mobile");
			dto.setMobile(sc.nextLong());
			System.out.println("Enter Email");
			dto.setEmail(sc.next());
			System.out.println("Enter aadharNo ");
			dto.setAadharNo(sc.nextLong());
			System.out.println("Enter PancardNo");
			dto.setPancardNo(sc.next());
			System.out.println("Enter Gender");
			dto.setGender(sc.next());

			sc.nextLine();
			System.out.println("Enter Address");
			dto.setAddress(sc.nextLine());

			System.out.println("Enter Date of Birth(yyyy-mm-dd)");
			// to convert String into date
			Date dob = Date.valueOf(sc.next());
			dto.setDateOfBirth(dob);

			System.out.println("Enter password");
			dto.setPassword(sc.next());

			int status = service.addCustomer(dto);
			if (status != 0) {
				System.out.println("********Registration Successfull********");
			} else {
				System.err.println("--------Registration Failed---------");
			}
		}
			break;

		case 2: {
			System.out.println("Enter Email : ");
			String email = sc.next();
			System.out.println("Enter Password : ");
			String password = sc.next();

			CustomerDto dto = service.verifyCustomer(email, password);
			if (dto != null) {
				// login successful!!
				System.out.println("*******Login Successful!!!********");
				System.out.println("------Welcome " + dto.getFirstName() + " " + dto.getLastName() + "-------");
				System.out.println(
						"__________________________________________________________________________________________");

				System.out.println("Enter 1 to open new bank account");
				System.out.println("Enter 2 to Access existing account");
				System.out.println("Enter your choice: ");
				int option = sc.nextInt();

				switch (option) {
				case 1: {
					AccountDto accountDto = new AccountDto();
					System.out.println("Enter Account Type");
					accountDto.setAccountType(sc.next());
					System.out.println("Enter Amount (>= 500)");
					double amount = sc.nextDouble();
					if (amount >= 500) {
						// set amount
						accountDto.setAmount(amount);

						// get account no
						String accountNo = GenerateAccountNumber.getGeneratedAccountNumber();
						accountDto.setAccountNo(accountNo);

						// set customerId in accountdto
						int customerId = dto.getId(); // get customerId from CustomerDto object
						accountDto.setCustomerId(customerId);

						// call addAccount method
						int status = service.addAccount(accountDto);
						if (status != 0) {
							System.out.println("********Account Added Successfully*******");
							System.out.println("------Your Account Number is : " + accountDto.getAccountNo());
						} else {
							System.err.println("Registration Failed...");
						}
					} else {
						System.err.println("Please deposit amount greater than 500");
					}
				}
					break;
				case 2: {
					System.out.println("Enter Account No : ");
					String accountNo = sc.next();
					AccountDto account = service.verifyAccount(accountNo);
					if (account != null) {
						System.out.println("*******Account Verified**********");
						System.out.println("--------Please process with further Operation--------");

						System.out.println("_________________________________________________________________");
						System.out.println("Enter 1 to view balance");
						System.out.println("Enter 2 to Deposit money");
						System.out.println("Enter 3 to Withdraw money");
						int value = sc.nextInt();

						switch (value) {
						case 1: {
							System.out.println("*********************************************");
							System.out.println("Your account balance is " + account.getAmount());
							System.out.println("*********************************************");
						}
							break;

						case 2: {
							System.out.println("Enter Amount to Deposit");
							double amount = sc.nextDouble();
							if (amount > 0) {
								// Add user entered amount to existing amount of account
								double finalAmount = amount + account.getAmount();

								int status = service.updateDepositOrWithdrawalAmount(finalAmount, accountNo);
								if (status != 0) {
									System.out.println("******** Money Deposited Successfully!!! ********");
									
									//to store transaction details
									TransactionDto transactionDto = new TransactionDto();
									transactionDto.setAccountNo(accountNo);
									transactionDto.setBalance(finalAmount);
									// to get current date
									LocalDate currentDate = LocalDate.now();
									//to convert LocalDate into Date
									Date date = Date.valueOf(currentDate);
									transactionDto.setDate(date);
									transactionDto.setTransactionType("credited Rs." + amount);

									service.addTransaction(transactionDto);
									
								} else {
									System.err.println("xxxxxxx Transaction unsuccessful xxxxxxx");
								}
							} else {
								System.err.println("Please enter amount > 0");
								System.err.println("xxxxxxx Transaction unsuccessful xxxxxxx");
							}
						}
							break;

						case 3: {
							System.out.println("Enter Amount to Withdraw");
							double amount = sc.nextDouble();
							if (amount <= account.getAmount()) {
								// Deduct user entered amount to existing amount of account
								double finalAmount = account.getAmount() - amount;

								int status = service.updateDepositOrWithdrawalAmount(finalAmount, accountNo);
								if (status != 0) {
									System.out.println("******** Money Withdraw Successfully!!! ********");
									TransactionDto transactionDto = new TransactionDto();
									transactionDto.setAccountNo(accountNo);
									transactionDto.setBalance(finalAmount);
									// to get current date
									transactionDto.setDate(Date.valueOf(LocalDate.now()));
									transactionDto.setTransactionType("debited Rs."+ amount );

									service.addTransaction(transactionDto);

								} else {
									System.err.println("xxxxxxx Transaction unsuccessful xxxxxxx");
								}
							} else {
								System.err.println("Insuffiecient Balance");
								System.err.println("xxxxxxx Transaction unsuccessful xxxxxxx");
							}
						}
							break;

						default:
							System.err.println("Invalid Choice...");
							break;
						}
					} else {
						System.err.println("Invalid Account details...");
					}
				}
					break;

				default:
					System.err.println("Enter Valid Choice...");
					break;
				}
			} else {
				System.err.println("Invalid Credentails. Please enter valid Email or Password.");
			}
		}
			break;

		default:
			System.err.println("Please Enter Valid Choice...");
			break;
		}
	}

	// Logic to execute performOperation method multiple times as per user choice
	public void continueOrStop() {
		System.out.println("***************Welcome to Customer Bank Management System*************");
		String choice = "no";

		do {
			performOperation();
			System.out.println("Enter Yes/Y to continue");
			System.out.println("Enter No/N to Stop");
			choice = sc.next();
		} while (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y"));

		System.out.println("**********Thank You***********");
	}

	@Override
	public void run() {
		continueOrStop();
	}

}
