package se.sds.floggit.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		MenuOptions menu = new MenuOptions();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int option;

		for (;;) {
			menu.showMenu();
			option = input.nextInt();
			input.nextLine();

			if (option == 0) break;

			System.out.println();
			menu.menuOptions(option);
		}
	}
}
