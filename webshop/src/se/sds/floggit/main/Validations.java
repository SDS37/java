package se.sds.floggit.main;

import java.util.Scanner;

import se.sds.floggit.api.ECommerceUser;
import se.sds.floggit.model.User;

public class Validations {
	
	Scanner input = new Scanner(System.in);
	boolean result;
	
	ECommerceUser eCommerceUser = new ECommerceUser();
	
	public User validatingUser(String email, String password){
		
		result = eCommerceUser.userValidation(new User(-1, null, email, null, null, password, null));
		
	do {
		if (result == false) {
			System.out.println("\nWrong username or/and password\n"); 
		} else {
			System.out.println("\nSUCCESS");
		}				
	} while (result != true); 
				
		User user = eCommerceUser.getUser(new User(-1, null, email, null, null, null, null));
		
		System.out.println("\nWelcome user: \n" + user);

	return user;
	}
}
