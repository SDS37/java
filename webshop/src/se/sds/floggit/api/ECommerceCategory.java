package se.sds.floggit.api;

import java.util.Collection;

import se.sds.floggit.model.Category;
import se.sds.floggit.repository.MySQLCategoryRepo;

public final class ECommerceCategory {
	
	private MySQLCategoryRepo categoryRepository;
	
	public ECommerceCategory() {
		categoryRepository = new MySQLCategoryRepo();
	}

	public void addCategory(Category category) {
		categoryRepository.add(category);
	}

	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	} 

	public void updateCategory(Category category) {
		categoryRepository.update(category);
	}

	public Collection<Category> getAllCategories() {
		return categoryRepository.getAll();
	}
}
