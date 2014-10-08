package se.sds.floggit.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

import se.sds.floggit.model.Product;
import se.sds.floggit.model.User;

public class MySQLCartRepo implements CartRepository {

	Scanner input = new Scanner(System.in);

	Collection<Product> productCollection = new LinkedHashSet<Product>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;

	Product prod;

	@Override
	public void add(User user, Product product) {
		
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
							+ "INSERT INTO floggit.user_product (user_id, product_id, amount) VALUES (?,?,?);");
			preparedstatement.setInt(1, user.getUserId());
			preparedstatement.setInt(2, product.getProductId());
			preparedstatement.setInt(3, product.getQuantityInCart());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
	}

	@Override
	public void delete(User user, Product product) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "DELETE FROM floggit.user_product WHERE user_id = ? and product_id = ?;");
			preparedstatement.setInt(1, user.getUserId());
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
	public void update(User user, Product product) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "UPDATE user_product "
					+ "SET amount = ? "
					+ "WHERE user_id = ? and product_id = ?;");
			preparedstatement.setInt(1, product.getQuantityInCart());
			preparedstatement.setInt(2, user.getUserId());
			preparedstatement.setInt(3, product.getProductId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public Collection<Product> getAll(User user) {

		productCollection.clear();
		
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
							+ "SELECT user.name, product.id, product.name, user_product.amount, product.cost, product.rrp "
							+ "FROM user "
							+ "LEFT JOIN user_product ON user_product.user_id = user.id "
							+ "LEFT JOIN product ON user_product.product_id = product.id "
							+ "WHERE user.email = ? ORDER BY user.name;");
			preparedstatement.setString(1, user.getEmail());
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int id = resultset.getInt("id");
				int amount = resultset.getInt("amount");
				String name = resultset.getString("product.name");
				int cost = resultset.getInt("cost");
				int rrp = resultset.getInt("rrp");
				Product product = new Product(id, name, null, cost, rrp, -1, amount);
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
