package se.sds.floggit.api;

import java.util.Collection;
import java.util.Scanner;

import se.sds.floggit.model.Category;
import se.sds.floggit.model.Product;
import se.sds.floggit.repository.MySQLProductRepo;

public final class ECommerceProduct {

	Scanner input = new Scanner(System.in);
	private MySQLProductRepo productRepository;

	public ECommerceProduct() {
		productRepository = new MySQLProductRepo();
	}

	public void addProduct(Product product) {
		productRepository.add(product);
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	public void updateProduct(Product product) {
		productRepository.update(product);
	}

	public Collection<Product> productsFromCategory(Category category) {
		return productRepository.getFromCategory(category);
	}
	
	public void addProductsToCategories(Product product, Category category) {
		productRepository.productsToCategories(product, category);	
	}

	public Product getProduct(Product product) {
		return productRepository.getProduct(product);
	}

	public Collection<Product> getAllProducts() {
		return productRepository.getAll();
	}
}