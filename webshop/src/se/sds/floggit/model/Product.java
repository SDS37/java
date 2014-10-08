package se.sds.floggit.model;

public final class Product {
	
	private final int productId;
	private final String name;
	private final String description;
	private final int cost;
	private final int RRP;
	private final int quantityInStock;
	private	final int quantityInCart;
	
	public Product(int productId, String name, String description, int cost, int RRP, int quantityInStock, int quantityInCart) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.RRP = RRP;	
		this.quantityInStock = quantityInStock;
		this.quantityInCart = quantityInCart;
	}

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getRRP() {
		return RRP;
	}
	
	public int getQuantityInStock() {
		return quantityInStock;
	}
	
	public int getQuantityInCart() {
		return quantityInCart;
	}

	@Override
	public String toString() {
		return String.format(
				"Id= %d \t Name: %s, Decription: %s, Cost: %d, RRP: %d, Quantity in stock: %d \n", 
				productId, name, description, cost, RRP, quantityInStock);
	}

	public String toStringCart() {
		return String.format(
				"Product Id= %d \t Name: %s, amount: %d, cost: %d, RRP: %d \n", 
				productId, name, quantityInCart, cost, RRP);
	}
	
	public String toStringOrder() {
		return String.format(
				"Id= %d \t Name: %s, amount: %d, Cost: %d, RRP: %d \n", 
				productId, name, quantityInStock, cost, RRP);
	}
}

