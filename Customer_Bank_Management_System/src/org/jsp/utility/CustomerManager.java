package org.jsp.utility;

import org.jsp.dao.CustomerDao;
import org.jsp.dao.CustomerDaoImpl;
import org.jsp.service.CustomerService;
import org.jsp.service.CustomerServiceImpl;

//to achieve abstraction 
//to hide implementation class object of CustomerDao
public class CustomerManager {

	public static CustomerDao getCustomerDaoObject() {
		CustomerDao dao = new CustomerDaoImpl();
		return dao;
	}
	
	public static CustomerService getCustomerServiceObject() {
		return new CustomerServiceImpl();
	}
}
