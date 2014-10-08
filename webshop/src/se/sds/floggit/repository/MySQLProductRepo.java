package se.sds.floggit.repository;

import java.lang.Exception;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

import se.sds.floggit.model.Category;
import se.sds.floggit.model.Product;
import se.sds.floggit.repository.ProductRepository;

public class MySQLProductRepo implements ProductRepository {

	Scanner input = new Scanner(System.in);
	
	Collection<Product> productCollection = new LinkedHashSet<Product>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;

	private Product prod; //PRIVATE

	@Override
	public void add(Product product) {

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "INSERT INTO product (name, description, cost, rrp, quantity_stock) VALUES(?,?,?,?,?)");
			preparedstatement.setString(1, product.getName());
			preparedstatement.setString(2, product.getDescription());
			preparedstatement.setInt(3, product.getCost());
			preparedstatement.setInt(4, product.getRRP());
			preparedstatement.setInt(5, product.getQuantityInStock());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) { 
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
	}

	@Override
	public void delete(Product product) {

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "DELETE FROM product WHERE id = ?");
			preparedstatement.setInt(1, product.getProductId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public void update(Product product) {

		try {
			
			connectingDB();
			preparedstatement = connection.prepareStatement(
					"UPDATE product SET name = ?, description = ?, cost = ?, rrp = ?, quantity_stock = ? WHERE id = ?");			
			preparedstatement.setString(1, product.getName());
			preparedstatement.setString(2, product.getDescription());
			preparedstatement.setInt(3, product.getCost());
			preparedstatement.setInt(4, product.getRRP());
			preparedstatement.setInt(5, product.getQuantityInStock());
			preparedstatement.setInt(6, product.getProductId());
			preparedstatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			
		} finally {
			tryCatch();
		}
	}

	public void productsToCategories(Product product, Category category) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "INSERT INTO category_product (category_id, product_id) VALUES(?,?)");
			preparedstatement.setInt(1, category.getCategoryId());
			preparedstatement.setInt(2, product.getProductId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}
	
	@Override
	public Collection<Product> getFromCategory(Category category) {

		productCollection.clear();

		try {
			connectingDB();		
			preparedstatement = connection.prepareStatement(
					"SELECT product.id, product.name, description, cost, rrp, quantity_stock, category.name "
					+ "FROM product  "
					+ "LEFT  JOIN category_product on product.id = category_product.product_id  "
					+ "LEFT  JOIN category on category.id = category_product.category_id "
					+ "WHERE category.id = ?");
			preparedstatement.setInt(1, category.getCategoryId());
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int productId = resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				int cost = resultset.getInt("cost");
				int rrp = resultset.getInt("rrp");
				int stockAmount = resultset.getInt("quantity_stock");
				
				Product product = new Product(productId, name, description, cost, rrp, stockAmount, -1);
				productCollection.add(product);				
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			
		} finally {
			tryCatch();
		}
		return productCollection;
	}

	public Product getProduct(Product product) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM product where name=?");
			preparedstatement.setString(1, product.getName());
			resultset = preparedstatement.executeQuery();
			
			resultset.next();
			int id = resultset.getInt("id");
			String name = resultset.getString("name");
			String description = resultset.getString("description");
			int cost = resultset.getInt("cost");
			int rrp = resultset.getInt("rrp");
			int quantityStock = resultset.getInt("quantity_stock");

			prod = new Product(id, name, description, cost, rrp, quantityStock, -1);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
		return prod;
	}
	
	@Override
	public Collection<Product> getAll() {

		productCollection.clear();

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM product");
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int productId = resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				int cost = resultset.getInt("cost");
				int rrp = resultset.getInt("rrp");
				int quantityStock = resultset.getInt("quantity_stock");
				Product product = new Product(productId, name, description, cost, rrp, quantityStock, -1);
				productCollection.add(product);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
		return productCollection;
	}

	public void connectingDB() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/floggit", "floggit_service", "floggit");
	}
	
	public void tryCatch() {
		try {
			if (resultset != null) {
				resultset.close();
			}
			if (preparedstatement != null) {
				preparedstatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
