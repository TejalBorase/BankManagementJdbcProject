package org.jsp.utility;

import java.sql.*;

public class Singleton {

	private static Singleton singleton = new Singleton();

	private static Connection connection = null;

	private Singleton() {
		System.out.println("------Singleton class object created-------");
	}

	public static Singleton getSingletonClassObject() {
		return singleton;
	}

	public Connection getDatabaseConnectionObject() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String username = "root";
				String password = "sql123";
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_bank_system", username,
						password);
				return connection;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
	}

}
