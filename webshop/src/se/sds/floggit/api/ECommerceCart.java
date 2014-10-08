package se.sds.floggit.api;

import java.util.Collection;

import se.sds.floggit.model.Product;
import se.sds.floggit.model.User;
import se.sds.floggit.repository.MySQLCartRepo;

public class ECommerceCart {

private MySQLCartRepo cartRepository;
	
	public ECommerceCart() {
		cartRepository = new MySQLCartRepo();
	}
	
	public void addProduct(User user, Product product) {
		cartRepository.add(user, product);
	}
	
	public void deleteProduct(User user, Product product) {
		cartRepository.delete(user, product);
	}
	
	public void updateCart(User user, Product product) {
		cartRepository.update(user, product);
	}
	
	public Collection<Product> getAllProducts(User user) {
		return cartRepository.getAll(user);
	}
}
