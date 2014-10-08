package se.sds.floggit.model;

public class Category {
	
	private final int categoryId;
	private final String name;
	private final int staffId;
	
	public Category(int categoryId, String name, int staffId) {
		this.categoryId = categoryId;
		this.name = name;
		this.staffId = staffId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getName() {
		return name;
	}

	public int getStaffId() {
		return staffId;
	}
	
	@Override
	public String toString() {
		return String.format("Id= %d \t %s \n", categoryId, name);
	}
}
