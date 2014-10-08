package se.sds.floggit.server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.Date;

import se.sds.floggit.model.User;
import se.sds.floggit.model.Product;
import se.sds.floggit.model.Category;

public class WebServer {

	int portNumber = 8080;

	ServerSocket serverSocket;
	DataOutputStream dataOutputStream;

	public void printUsers(Collection<User> userList) throws Exception {

		System.out.println("\nStarting the server...");

		serverSocket = new ServerSocket(portNumber);
		dataOutputStream = new DataOutputStream(serverSocket.accept()
				.getOutputStream());

		for (User user : userList) {
			dataOutputStream.writeBytes(user.toString() + "Date: "
					+ new Date().toString() + "\n");
		}

		dataOutputStream.close();
	}

	public void printProducts(Collection<Product> productList) throws Exception {

		serverSocket = new ServerSocket(portNumber);
		dataOutputStream = new DataOutputStream(serverSocket.accept()
				.getOutputStream());

		for (Product product : productList) {
			dataOutputStream.writeBytes(product.toString());
		}

		dataOutputStream.close();
	}

	public void printCategories(Collection<Category> categoryList)
			throws Exception {

		serverSocket = new ServerSocket(portNumber);
		dataOutputStream = new DataOutputStream(serverSocket.accept()
				.getOutputStream());

		for (Category category : categoryList) {
			dataOutputStream.writeBytes(category.toString());
		}

		dataOutputStream.close();
	}
}

