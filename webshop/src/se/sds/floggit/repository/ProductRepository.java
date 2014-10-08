package se.sds.floggit.repository;

import java.util.Collection;

import se.sds.floggit.model.Category;
import se.sds.floggit.model.Product;

public interface ProductRepository {
	
	public void add(Product product);
	
	public void delete(Product product);

	public void update(Product product);
	
	public Collection<Product> getFromCategory(Category category);
	
	public void productsToCategories(Product product, Category category);
	
	public Product getProduct(Product product);
	
	public Collection<Product> getAll();

}