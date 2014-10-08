package se.sds.floggit.repository;

import java.util.Collection;

import se.sds.floggit.model.Product;
import se.sds.floggit.model.User;

public interface CartRepository {
	
	public void add(User user, Product product); 
	
	public void delete(User user, Product product); 
	
	public void update(User user, Product product);
		
	public Collection<Product> getAll(User user);
	
}