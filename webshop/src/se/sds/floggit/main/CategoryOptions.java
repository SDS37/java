package se.sds.floggit.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import se.sds.floggit.server.WebServer;
import se.sds.floggit.api.ECommerceCategory;
import se.sds.floggit.model.Category;

public class CategoryOptions {
	
	Scanner input = new Scanner(System.in);
	
	WebServer webServer = new WebServer();
	
	ECommerceCategory eCommerceCategory = new ECommerceCategory();
	Collection<Category> categoryList = new ArrayList<Category>();
	
	public void categoryMenu() {
		System.out.println("CATEGORY MENU");
		System.out.println("1. Add category.");
		System.out.println("2. Delete category.");
		System.out.println("3. Update category.");
		System.out.println("4. Get all categories from DB.");
		System.out.println("\n0. To quit CATEGORY MENU.\n");
		System.out.println("Option: \n");
	}
	
	public void options(int option) {
		switch (option) {
		case 1: // Add 
			for (;;) {
				String name = null;
				int staffId = 0;
				System.out.println("ADDING A CATEGORY\n");
				System.out.println("Name: ");
				name = input.nextLine();
				System.out.println("Staff id: ");
				staffId = input.nextInt();
				input.nextLine();					

				eCommerceCategory.addCategory(new Category(-1, name, staffId));			

				System.out.println("1. Add another category");
				System.out.println("0. Go back to CATEGORY MENU");
				option = input.nextInt();
				input.nextLine();	
				
				if (option == 0) break;
				else continue;
			}
			break;
		case 2: // Delete 
			for (;;) {
				System.out.println("DELETING A CATEGORY\n");
				
				//printAll();
				
				eCommerceCategory.getAllCategories().toString(); // CHANGE!!!!
				
				System.out.println("\nId of the category you want to delete: \n");
				int categoryId = input.nextInt();
				input.nextLine();
						
				eCommerceCategory.deleteCategory(new Category(categoryId, null, -1));
				
				System.out.println("\n1. Delete another category");
				System.out.println("0. Go back to CATEGORY MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 3: // Update
			for (;;) {
				System.out.println("UPDATING A CATEGORY\n");
				
				printAll();
				
				System.out.println("\nId of the category you want to update: \n");
				int categoryId = input.nextInt();
				input.nextLine();
							
				System.out.println("New name: ");
				String categoryName = input.nextLine();
				System.out.println("New staff id: ");
				int staffId = input.nextInt();
				input.nextLine();		
				
				eCommerceCategory.updateCategory(new Category(categoryId, categoryName, staffId));
				
				System.out.println("\n1. Update another category");
				System.out.println("0. Go back to CATEGORY MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 4: // Get all
			for (;;) {
				System.out.println("GETTING ALL THE CATEGORIES\n");				
				
				printAll();
				
				System.out.println("\n0. Go back to CATEGORY MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		}
		System.out.println();
	}
	
	public void printAll() {
		
		categoryList.clear();
			
		categoryList.addAll(eCommerceCategory.getAllCategories());
		for (Category category : categoryList) {
			System.out.println(category);
		}
		
		try {
			webServer.printCategories(categoryList);
		}catch (Exception e) {
			System.out.println("connection not achieved!");
			e.printStackTrace();
		}		
	}
}
