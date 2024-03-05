package org.jsp.utility;

import java.util.Random;

public class GenerateAccountNumber {

	public static String getGeneratedAccountNumber() {
		Random random = new Random();
		int number = random.nextInt();
		
		if(number < 0) {
			number *= -1;
		}
		if(number < 1000000000) {
			number += 1000000000;
		}
		
		return "SBI-" + number;
	}
}










