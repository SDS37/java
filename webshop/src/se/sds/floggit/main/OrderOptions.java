package se.sds.floggit.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import se.sds.floggit.api.ECommerceOrder;
import se.sds.floggit.api.ECommerceUser;
import se.sds.floggit.api.ECommerceCart;
import se.sds.floggit.model.Order;
import se.sds.floggit.model.Product;
import se.sds.floggit.model.User;

public class OrderOptions {
	
	Scanner input = new Scanner(System.in);
	
	ECommerceOrder eCommerceOrder = new ECommerceOrder();
	ECommerceUser eCommerceUser = new ECommerceUser();
	ECommerceCart eCommerceCart = new ECommerceCart();

	Collection<Order> orderList = new ArrayList<Order>();
	Collection<Product> productList = new ArrayList<Product>();
	
	Order retrievedOrder;	
	User retrievedUser;
	
	public void orderMenu() {
		System.out.println("ORDER MENU\n");
		System.out.println("1. Get an order.");
		System.out.println("2. Get all orders from DB.");
		System.out.println("\n0. To quit ORDER MENU.\n");
		System.out.println("Option: \n");
	}
	
	public void options(int option) {
		switch (option) {
		case 1: // Getting an order 
			for (;;) {
				
				System.out.println();
				printAll();
				
				System.out.println("GETTING AN SPECIFIC ORDER\n");
				System.out.println("Order id: ");
				int orderId = input.nextInt();
				input.nextLine();					
				
				retrievedOrder = eCommerceOrder.getOrder(new Order(orderId, null, -1, null, null, null, null, null, -1));
				retrievedUser = eCommerceUser.getUser(new User(retrievedOrder.getUserId(), null, null, null, null, null, null));
				
				System.out.println(retrievedOrder.toStringDate());
				System.out.println(retrievedUser.toStringOrder() + "\n");
				
				int total = 0, profit, cost, rrp;
				
				productList.clear();
				productList.addAll(eCommerceCart.getAllProducts(retrievedUser));
				for (Product product : productList) {
					
					cost = product.getCost();
					rrp = product.getRRP();
					
					profit = rrp - cost;
					
					System.out.println(product.toStringCart() + ", Profit: " + profit);	
					
					total = total + cost;
				}
				
				System.out.println("\nTOTAL COST OF THE ORDER: " + total);
				
				System.out.println("\n1. Get another order");
				System.out.println("0. Go back to ORDER MENU");
				option = input.nextInt();
				input.nextLine();	
				
				if (option == 0) break;
				else continue;
			}
			break;
		case 2: // Get all orders
			for (;;) {
				System.out.println("GETTING ALL THE ORDERS\n");				
				
				printAll();
				
				System.out.println("\n0. Go back to ORDER MENU");
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
		orderList.clear();
		orderList.addAll(eCommerceOrder.getAllOrders());
		for (Order order : orderList) {
			System.out.println(order);
		} 
	}
}
