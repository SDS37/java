package se.sds.floggit.main;

import java.util.Scanner;

public class MenuOptions {

	int option;
	Scanner input = new Scanner(System.in);

	UserOptions userOptions = new UserOptions();
	ProductOptions productOptions = new ProductOptions();
	CategoryOptions categoryOptions = new CategoryOptions();
	OrderOptions orderOptions = new OrderOptions();

	public void showMenu() {
		System.out.println("Floggit database\n");
		System.out.println("MAIN MENU\n");
		System.out.println("1. User managment.");
		System.out.println("2. Product managment.");
		System.out.println("3. Category managment.");
		System.out.println("4. Order management.");
		System.out.println("\n0. To quit program.\n");
		System.out.println("Option: \n");
	}

	public void menuOptions(int option) {
		switch (option) {
		case 1:
			for (;;) {
				userOptions.userMenu();
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				System.out.println();
				userOptions.options(option);
			}
			break;
		case 2:
			for (;;) {
				productOptions.productMenu();
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				System.out.println();
				productOptions.options(option);
			}
			break;
		case 3:
			for (;;) {
				categoryOptions.categoryMenu();
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				System.out.println();
				categoryOptions.options(option);
			}
			break;
		case 4:
			for (;;) {
				orderOptions.orderMenu();
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				System.out.println();
				orderOptions.options(option);
			}
			break;
		}
		System.out.println();
	}
}