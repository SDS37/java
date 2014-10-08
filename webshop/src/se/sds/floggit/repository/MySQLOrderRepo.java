package se.sds.floggit.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

import se.sds.floggit.model.Order;

public class MySQLOrderRepo implements OrderRepository {

	Scanner input = new Scanner(System.in);
	Collection<Order> orderCollection = new LinkedHashSet<Order>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	
	Order retrievedOrder;
	
	@Override
	public void add(Order order) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
							+ "INSERT INTO orders (user_id) VALUES (?);");
			preparedstatement.setInt(1, order.getUserId());			
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
	}
	
	@Override
	public Order getOrder(Order order) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM orders where id=?");
			preparedstatement.setInt(1, order.getOrderId());
			resultset = preparedstatement.executeQuery();
			
			resultset.next();
			int orderId = resultset.getInt("id");
			String date = resultset.getString("date");
			int userId = resultset.getInt("user_id");

			retrievedOrder = new Order(orderId, date, userId, null, null, null, null, null, -1);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			tryCatch();
		}
		return retrievedOrder;
	}

	@Override
	public Collection<Order> getAll() {
		
		try {
			
			orderCollection.clear();
			
			connectingDB();

			preparedstatement = connection.prepareStatement(""
					+ "SELECT "
					+ "orders.id, orders.date, orders.user_id, "
					+ "user.name, user.address, user.email, user.telephone_number, "
					+ "product.name, "
					+ "user_product.amount "
					+ "FROM orders "
					+ "LEFT JOIN user_product on orders.user_id = user_product.user_id "
					+ "LEFT JOIN product on user_product.product_id = product.id "
					+ "LEFT JOIN user on orders.user_id = user.id "
					+ "ORDER BY orders.date ASC;");
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int orderId = resultset.getInt("id");
				String date = resultset.getString("date");
				int userId = resultset.getInt("user_id");
				String name = resultset.getString("user.name");
				String address = resultset.getString("address");
				String email = resultset.getString("email");
				String telephoneNumber = resultset.getString("telephone_number");
				String productName = resultset.getString("product.name");
				int amount = resultset.getInt("amount");

				Order order = new Order(orderId, date, userId, name, address, email, telephoneNumber, productName, amount);
				
				orderCollection.add(order);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
		return orderCollection;
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
