package se.sds.floggit.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import se.sds.floggit.server.WebServer;
import se.sds.floggit.api.ECommerceCategory;
import se.sds.floggit.api.ECommerceProduct;
import se.sds.floggit.model.Category;
import se.sds.floggit.model.Product;

public class ProductOptions {
	
	Scanner input = new Scanner(System.in);
	
	WebServer webServer = new WebServer();
	
	ECommerceProduct eCommerceProduct = new ECommerceProduct();
	ECommerceCategory eCommerceCategory = new ECommerceCategory();
		
	Collection<Product> productList = new ArrayList<Product>();
	Collection<Category> categoryList = new ArrayList<Category>();
	
	public void productMenu() {
		System.out.println("PRODUCT MENU\n");
		System.out.println("1. Add product.");
		System.out.println("2. Delete product.");
		System.out.println("3. Update product.");
		System.out.println("4. Get all products from a category.");
		System.out.println("5. Add a category to product.");
		System.out.println("6. Search a product by name.");
		System.out.println("7. Get all products from DB.");
		System.out.println("8. Show stock amount less than x");
		System.out.println("\n0. To leave PRODUCT MENU.\n");
		System.out.println("Option: \n");
	}
	
	public void options(int option) {
		switch (option) {
		case 1: // Add
			for (;;) {
				System.out.println("ADDING A PRODUCT\n");
				System.out.println("Name: ");
				String productName = input.nextLine();
				System.out.println("Description: ");
				String description = input.nextLine();
				System.out.println("Cost: ");
				int cost = input.nextInt();
				System.out.println("RRP: ");
				int rrp = input.nextInt();
				System.out.println("Quantity in stock: ");
				int stockAmount = input.nextInt();
				input.nextLine();
				
				eCommerceProduct.addProduct(new Product(-1, productName, description, cost, rrp, stockAmount, -1));

				System.out.println("1. Add another product");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 2: // Delete
			for (;;) {
				System.out.println("DELETING A PRODUCT\n");
				
				printAll();
				
				System.out.println("\nId of the product you want to delete: \n");
				int productId = input.nextInt();
				input.nextLine();
				
				eCommerceProduct.deleteProduct(new Product(productId, null, null, -1, -1, -1, -1));

				System.out.println("1. Delete another product");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 3: // Update  
			for (;;) {
				System.out.println("UPDATING A PRODUCT\n");
				
				printAll();
				
				System.out.println("\nId of the product you want to update: \n");
				int productId = input.nextInt();
				input.nextLine();
				
				System.out.println("ProductName: ");
				String productName = input.nextLine();
				System.out.println("Description: ");
				String description = input.nextLine();
				System.out.println("Cost: ");
				int cost = input.nextInt();
				System.out.println("RRP: ");
				int rrp = input.nextInt();
				System.out.println("Quantity in stock: ");
				int stockAmount = input.nextInt();
				input.nextLine();
				
				eCommerceProduct.updateProduct(new Product(productId, productName, description, cost, rrp, stockAmount, -1));

				System.out.println("\n1. Update another product");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 4: // Get all products from a category
			for (;;) {
				
				productList.clear();
				
				System.out.println("GETTING ALL PRODUCTS FROM A CATEGORY\n");
				
				printAllCategories();
				
				System.out.println("\nId of the category: ");
				int categoryId = input.nextInt();
				input.nextLine();
				
				productList.addAll(eCommerceProduct.productsFromCategory(new Category(categoryId, null, -1)));
				for (Product product : productList) {
					System.out.println(product);
				}				
				
				System.out.println("\n1. Get all the products from another category");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 5: // Adding products to categories
			for (;;) {
				System.out.println("ADDING PRODUCTS TO CATEGORIES\n");
				
				printAll(); //Products
				
				System.out.println("\nEnter product id:\n");
				int ProductId = input.nextInt();
				input.nextLine();
				
				Product product = new Product(ProductId, null, null, -1, -1, -1, -1);

				printAllCategories();
				
				System.out.println("\nEnter id of category\n");
				int CategoryId = input.nextInt();
				input.nextLine();
				
				Category category = new Category(CategoryId, null, -1);
				
				eCommerceProduct.addProductsToCategories(product, category);
								
				System.out.println("\n1. Add another product to a category");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;							
			}
			break;
		case 6: // Get a specific product by name
			for (;;) {
				System.out.println("GETTING A SPECIFIC PRODUCT BY NAME\n");		
				
				printAll();
				
				System.out.println("\nName of the product:");
				String productName = input.nextLine();
				
				System.out.println(eCommerceProduct.getProduct(new Product(-1, productName, null, -1, -1, -1, -1)).toString());
								
				System.out.println("\n1. Get another product");
				System.out.println("0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 7: // Get all
			for (;;) {
				System.out.println("GETTING ALL PRODUCTS\n");
							
				printAll();
				
				System.out.println("\n0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 8: // Get stock amount < x
			for (;;) {
				System.out.println("GETTING THE STOCK AMOUNT OF PRODUCTS THAT ARE LESS THAN X\n");
				
				System.out.println("Show amount less than: ");
				int x = input.nextInt();
				input.nextLine();
				
				int quantityInStock;
				
				productList.clear();
				productList.addAll(eCommerceProduct.getAllProducts());
				for (Product product : productList) {
					quantityInStock = product.getQuantityInStock();
						if (quantityInStock < x){
							System.out.println(product);
						}
				}
				
				System.out.println("\n1. Consult stock amount again");
				System.out.println("\n0. Go back to PRODUCT MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		} System.out.println();			
	}
		
	public void printAll() {
		
		productList.clear();
		
		productList.addAll(eCommerceProduct.getAllProducts());
		for (Product product : productList) {
			System.out.println(product);
		}
		
		try {
			webServer.printProducts(productList);
		}catch (Exception e) {
			System.out.println("connection not achieved!");
			e.printStackTrace();
		}	
	}
	
	public void printAllCategories() {
		categoryList.clear();
		categoryList.addAll(eCommerceCategory.getAllCategories());
		for (Category category : categoryList) {
			System.out.println(category);
		}
	}
}
