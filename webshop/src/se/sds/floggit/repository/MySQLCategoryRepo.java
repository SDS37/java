package se.sds.floggit.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

import se.sds.floggit.model.Category;
import se.sds.floggit.repository.CategoryRepository;

public class MySQLCategoryRepo implements CategoryRepository {

	Scanner input = new Scanner(System.in);

	Collection<Category> categoryCollection = new LinkedHashSet<Category>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;

	@Override
	public void add(Category category) {
		
		try {
			
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "INSERT INTO category (name, staff_id) VALUES(?,?)");
			preparedstatement.setString(1, category.getName());
			preparedstatement.setInt(2, category.getStaffId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public void delete(Category category) {

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "DELETE FROM category WHERE id = ?");
			preparedstatement.setInt(1, category.getCategoryId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public void update(Category category) {

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "UPDATE category "
					+ "SET name = ?, staff_id = ? "
					+ "WHERE id=?");
			preparedstatement.setString(1, category.getName());
			preparedstatement.setInt(2, category.getStaffId());
			preparedstatement.setInt(3, category.getCategoryId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			
		} finally {
			tryCatch();
		}
	}

	@Override
	public Collection<Category> getAll() {

		categoryCollection.clear();

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM category");
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int id = resultset.getInt("id");
				String categoryName = resultset.getString("name");
				int staffId = resultset.getInt("staff_id");
				Category category = new Category(id, categoryName, staffId);
				categoryCollection.add(category);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
		return categoryCollection;
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
