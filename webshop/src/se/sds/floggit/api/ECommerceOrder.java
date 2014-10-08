package se.sds.floggit.api;

import java.util.Collection;

import se.sds.floggit.model.Order;

import se.sds.floggit.repository.MySQLOrderRepo;

public class ECommerceOrder {

private MySQLOrderRepo orderRepository;
	
	public ECommerceOrder() {
		orderRepository = new MySQLOrderRepo();
	}
	
	public void addOrder(Order order) { 
		orderRepository.add(order);
	}

	public Order getOrder(Order order) {
		return orderRepository.getOrder(order);
	}	
	
	public Collection<Order> getAllOrders() {
		return orderRepository.getAll();
	}
}

