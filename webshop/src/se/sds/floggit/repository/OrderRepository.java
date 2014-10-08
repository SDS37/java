package se.sds.floggit.repository;

import java.util.Collection;

import se.sds.floggit.model.Order;

public interface OrderRepository {
	
	public void add(Order order);
	
	public Order getOrder(Order order);
	
	public Collection<Order> getAll();
	
}
