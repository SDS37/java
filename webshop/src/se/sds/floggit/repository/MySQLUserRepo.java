package se.sds.floggit.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.sql.DriverManager;

import se.sds.floggit.model.User;
import se.sds.floggit.repository.UserRepository;

public class MySQLUserRepo implements UserRepository {

	Scanner input = new Scanner(System.in);
	Collection<User> userCollection = new LinkedHashSet<User>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	
	User user;

	@Override
	public void add(User user) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "INSERT INTO user (name,email,address,telephone_number,password) VALUES(?,?,?,?,?)");
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getEmail());
			preparedstatement.setString(3, user.getAddress());
			preparedstatement.setString(4, user.getPhonenumber());
			preparedstatement.setString(5, user.getPassword());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public void delete(User user) {
		
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "DELETE FROM user WHERE id = ?");
			preparedstatement.setInt(1, user.getUserId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public void update(User user) {

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "UPDATE user "
					+ "SET name = ?, email = ?, address = ?, telephone_number = ?, password = ? "
					+ "WHERE id = ?");
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getEmail());
			preparedstatement.setString(3, user.getAddress());
			preparedstatement.setString(4, user.getPhonenumber());
			preparedstatement.setString(5, user.getPassword());
			preparedstatement.setInt(6, user.getUserId());
			preparedstatement.executeUpdate();
			System.out.println("\nSUCCESS\n");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}

	}

	@Override
	public boolean userValidation(User user) {
		
		boolean validate = false;

		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM user where email = ? and password = ?");
			preparedstatement.setString(1, user.getEmail());
			preparedstatement.setString(2, user.getPassword());
			resultset = preparedstatement.executeQuery();
			if(resultset.next()){
				validate = true;
			}
	
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			validate = false;
		} finally {
			tryCatch();
		}		
		return validate;
	}
	
	@Override
	public User get(User user) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement("SELECT * FROM user where id = ? or email = ?;");
			preparedstatement.setInt(1, user.getUserId());
			preparedstatement.setString(2, user.getEmail());
			resultset = preparedstatement.executeQuery();

			resultset.next();
			int id = resultset.getInt("id");
			String name = resultset.getString("name");
			String email = resultset.getString("email");
			String address = resultset.getString("address");
			String telephoneNumber = resultset.getString("telephone_number");
			String password = resultset.getString("password");

			user = new User(id, name, email, address, telephoneNumber, password, null);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			user = null;
		} finally {
			tryCatch();
		}
		return user;
	}
	
	@Override
	public Collection<User> getAll() {
		
		try {
			
			userCollection.clear();
			
			connectingDB();

			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM user");
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String address = resultset.getString("address");
				String telephoneNumber = resultset.getString("telephone_number");
				String password = resultset.getString("password");

				User user = new User(id, name, email, address, telephoneNumber, password, null);

				userCollection.add(user);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
		return userCollection;
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
