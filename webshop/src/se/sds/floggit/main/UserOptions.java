package se.sds.floggit.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import se.sds.floggit.server.WebServer;
import se.sds.floggit.api.ECommerceUser;
import se.sds.floggit.model.User;

public class UserOptions {

	Scanner input = new Scanner(System.in);
	
	WebServer webServer = new WebServer();

	ECommerceUser eCommerceUser = new ECommerceUser();
	Collection<User> userList = new ArrayList<User>();

	Validations validations = new Validations();	
	CartOptions cartOptions = new CartOptions();

	public void userMenu() {
		System.out.println("USER MENU\n");
		System.out.println("1. Add user.");
		System.out.println("2. Delete user.");
		System.out.println("3. Update user.");
		System.out
				.println("4. Validate existence of a user through the user's email (username) and password.");
		System.out.println("5. Get all users from DB.");
		// ---------------------------------------------
		System.out.println("6. User cart Management.");
		// ---------------------------------------------
		System.out.println("\n0. To leave USER MENU.\n");
		System.out.println("Option: \n");
	}

	public void options(int option) {
		switch (option) {
		case 1: // Add
			for (;;) {
				System.out.println("ADDING A USER\n");
				System.out.println("Name: ");
				String name = input.nextLine();
				System.out.println("Email: ");
				String email = input.nextLine();
				System.out.println("Address: ");
				String address = input.nextLine();
				System.out.println("Phone number: ");
				String phoneNumber = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();

				eCommerceUser.addUser(new User(-1, name, email, address, phoneNumber, password, null));

				System.out.println("1. Add another user");
				System.out.println("0. Go back to USER MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 2: // Delete
			for (;;) {
				System.out.println("DELETING A USER\n");

				printAll();

				System.out.println("\nId of the user you want to delete: \n");
				int userId = input.nextInt();
				input.nextLine();

				eCommerceUser.deleteUser(new User(userId, null, null, null, null, null, null));

				System.out.println("1. Delete another user");
				System.out.println("0. Go back to USER MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 3: // Update
			for (;;) {
				System.out.println("UPDATING A USER\n");

				printAll();

				System.out.println("\nId of the user you want to update: \n");
				int userId = input.nextInt();
				input.nextLine();

				System.out.println("New name: ");
				String name = input.nextLine();
				System.out.println("Email: ");
				String email = input.nextLine();
				System.out.println("Address: ");
				String address = input.nextLine();
				System.out.println("Phone number: ");
				String phoneNumber = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();

				eCommerceUser.updateUser(new User(userId, name, email, address, phoneNumber, password, null));

				System.out.println("1. Update another user");
				System.out.println("0. Go back to USER MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 4: // Validating a user
			for (;;) {
				System.out.println("VALIDATING A USER\n");

				printAll();

				System.out.println("\nEmail:  ");
				String email = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();

				System.out.println(eCommerceUser.userValidation(new User(-1, null, email, null, null, password, null)));

				System.out.println("\n1. Validate another user");
				System.out.println("0. Go back to USER MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 5: // Get all
			for (;;) {
				System.out.println("GETTING ALL THE USERS\n");

				printAll();

				System.out.println("\n0. Go back to USER MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 6: // Cart management
			String email, password;
			boolean result;
				
			do {
				System.out.println("User's email:");
				email = input.nextLine();
				System.out.println("User's password:");
				password = input.nextLine();

				result = eCommerceUser.userValidation(new User(-1, null, email, null, null, password, null));

				if (result == false) {
					System.out.println("\nWrong username or/and password\n");
				} else {
					System.out.println("\nSUCCESS");
				}
			} while (result != true);	
			
			User user = eCommerceUser.getUser(new User(-1, null, email, null, null, password, null));
			
			System.out.println("\nWelcome\t " + user.getName() + ".");
			
			for (;;) {	
				cartOptions.cartMenu(); 
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break; 
				cartOptions.options(user, option);
			}
			break;
		}
		System.out.println();
	}

	public void printAll() {
		
		userList.clear();
		
		userList.addAll(eCommerceUser.getAllUsers());
		for (User user : userList) {
			System.out.println(user);
		}
		
		try {
			webServer.printUsers(userList);
		}catch (Exception e) {
			System.out.println("connection not achieved!");
			e.printStackTrace();
		}	
	}
}
