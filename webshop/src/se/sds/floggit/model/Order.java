package se.sds.floggit.model;

public class Order {
	
	private final int orderId;
	private final String date;
	private final int userId;
	private final String userName;
	private final String userAddress;
	private final String userEmail;
	private final String userTelephone;
	private final String productName;
	private final int productAmount;

	public Order(
			int orderId, String date, 
			int userId,
			String userName, String userAddress, String userEmail, String userTelephone, 
			String productName, int productAmount) {
		this.orderId = orderId;
		this.date = date;
		this.userId = userId;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userTelephone = userTelephone;
		this.productName = productName;
		this.productAmount = productAmount;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getUserId() { //FROM user_product table
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public String getUserTelephone() {
		return userTelephone;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getProductAmount() {
		return productAmount;
	}
	
	@Override
	public String toString() {
		return String.format(""
				+ "Order Id: %d, Date: %s \n"
				+ "User Id: %d, User name: %s, Address: %s, Email: %s, Telephone: %s \n"
				+ "Product name: %s, Amount: %d \n", 
				orderId, date, userId, userName, userAddress, userEmail, userTelephone, productName, productAmount);
	}
	
	public String toStringDate() {
		return String.format(""
				+ "Date: %s \n", date);
	}
}
