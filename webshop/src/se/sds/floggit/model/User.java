package se.sds.floggit.model;

public class User {
	
	private final int userId;
	private final String name;
	private final String email;
	private final String address;
	private final String phonenumber;	
	private final String password;
	
	private final Order cart;

	public User(int userId, String name, String email, String address, String phone_number, String password, Order cart) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phonenumber = phone_number;
		this.password = password;
		this.cart = cart;
	}

	public int getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Order getCart() {
		return cart;
	}

	@Override
	public String toString() {
		return String.format("User Id= %d \t Name: %s, Email: %s, Address: %s, Phone Number: %s, Password: %s \n", 
				userId, name, email, address, phonenumber, password);
	}
	
	public String toStringOrder() {
		return String.format("User Id= %d \t Name: %s, Email: %s, Address: %s, Phone Number: %s \n", 
				userId, name, email, address, phonenumber);
	}
}
