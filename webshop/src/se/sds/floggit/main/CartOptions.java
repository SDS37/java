package se.sds.floggit.main;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Scanner;

import se.sds.floggit.api.ECommerceCart;
import se.sds.floggit.api.ECommerceOrder;
import se.sds.floggit.api.ECommerceProduct;
import se.sds.floggit.api.ECommerceUser;
import se.sds.floggit.model.Order;
import se.sds.floggit.model.Product;
import se.sds.floggit.model.User;

public class CartOptions {

	Scanner input = new Scanner(System.in);

	Collection<User> userList = new ArrayList<User>();
	Collection<Product> productList = new ArrayList<Product>();

	ECommerceCart eCommerceCart = new ECommerceCart();
	ECommerceOrder eCommerceOrder = new ECommerceOrder();
	ECommerceUser eCommerceUser = new ECommerceUser();
	ECommerceProduct eCommerceProduct = new ECommerceProduct();
	
	Product retrievedProduct;
	
	public void cartMenu() {
		System.out.println("\nCART MENU\n");
		System.out.println("1. Add products to the cart.");
		System.out.println("2. Delete products from the cart.");
		System.out.println("3. Update cart.");
		System.out.println("4. Getting all the products from the cart.");
		System.out.println("5. Make the order.");
		System.out.println("\n0. Go back to USER MENU.\n");
		System.out.println("Option: \n");
	}

	public void options(User activeUser, int option) {	
		switch (option) {
		case 1: // Add
			for (;;) {
				System.out.println("\nADDING PRODUCTS TO " + activeUser.getName() + "'s CART.");
				System.out.println("\nList of products from DB:\n");

				printAllProducts();

				System.out.println("\nId of the product you want to add:");
				int productId = input.nextInt();
				System.out.println("Quantity:");
				int amount = input.nextInt();
				input.nextLine();
				
				if (amount == 0) {
					System.out.println("\nA quantity is needed\n");
				} else {
					eCommerceCart.addProduct(activeUser, new Product(productId, null, null, -1, -1, -1, amount));
				}

				System.out.println("1. Add another product");
				System.out.println("0. Go back to CART MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 2: // Delete
			for (;;) {
				System.out.println("\nDELETING PRODUCTS FROM " + activeUser.getName() + "'s CART.\n");

				productList.clear();
				productList.addAll(eCommerceCart.getAllProducts(activeUser));
				for (Product product : productList) {
					System.out.println(product.toStringCart());
				}

				if (productList.isEmpty()) {
					System.out.println("The cart is empty");
				} else {
					System.out.println("\nId of product you want to delete:");
					int productId = input.nextInt();
					input.nextLine();
		
					eCommerceCart.deleteProduct(activeUser, new Product(productId, null, null, -1, -1, -1, -1));					
				}

				System.out.println("1. Delete another product");
				System.out.println("0. Go back to CART MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 3: // Update
			for (;;) {
				System.out.println("\nUPDATING PRODUCTS FROM " + activeUser.getName() + "'s CART.\n");
				
				productList.clear();
				productList.addAll(eCommerceCart.getAllProducts(activeUser));
				for (Product product : productList) {
					System.out.println(product.toStringCart());
				}
				
				System.out.println("\nId of product you want to update:");
				int productId = input.nextInt();
				input.nextLine();
								
				System.out.println("New amount:");
				int amount = input.nextInt();
				input.nextLine();

				eCommerceCart.updateCart(activeUser, new Product(productId, null, null, -1, -1, -1, amount));
				
				System.out.println("1. Update another product");
				System.out.println("0. Go back to CART MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 4: // Get all
			for (;;) {
				System.out.println("\nGETTING ALL PRODUCTS FROM " + activeUser.getName() + "'s CART.");

				productList.clear();
				productList.addAll(eCommerceCart.getAllProducts(activeUser));
				for (Product product : productList) {
					System.out.println(product.toStringCart());
				}
				
				System.out.println("\n0. Go back to CART MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		case 5: // Make an order
			for (;;) {
				System.out.println("\nMAKING " + activeUser.getName() + "'s ORDER.");
				
				eCommerceOrder.addOrder(new Order(-1, null, activeUser.getUserId(), null, null, null, null, null, -1));
				
				int quantityInStock, quantityInCart;
				
				productList.clear();
				productList.addAll(eCommerceCart.getAllProducts(activeUser));
				for (Product product : productList) {
					
					retrievedProduct = eCommerceProduct.getProduct(new Product(-1, product.getName(), null, -1, -1, -1, -1));
					
					quantityInStock = retrievedProduct.getQuantityInStock();
					quantityInCart = product.getQuantityInCart();
					
					quantityInStock = quantityInStock - quantityInCart;
					
					eCommerceProduct.updateProduct(new Product(
							retrievedProduct.getProductId(), 
							retrievedProduct.getName(), 
							retrievedProduct.getDescription(), 
							retrievedProduct.getCost(), 
							retrievedProduct.getRRP(), 
							quantityInStock, 
							-1));
					
					System.out.println("Product amount of " + retrievedProduct.getName() + " has been updated on the DB");
				}
				
				System.out.println("\n0. Go back to CART MENU");
				option = input.nextInt();
				input.nextLine();
				if (option == 0) break;
				else continue;
			}
			break;
		}		
		System.out.println();
	}

	public void printAllUsers() {
		userList.clear();
		userList.addAll(eCommerceUser.getAllUsers());
		for (User user : userList) {
			System.out.println(user);
		}
	}

	public void printAllProducts() {
		productList.clear();
		productList.addAll(eCommerceProduct.getAllProducts());
		for (Product product : productList) {
			System.out.println(product);
		}
	}
	
}
