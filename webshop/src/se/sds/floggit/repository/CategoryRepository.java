package se.sds.floggit.repository;

import java.util.Collection;

import se.sds.floggit.model.Category;

public interface CategoryRepository {

	public void add(Category category);
	
	public void delete(Category category);

	public void update(Category category);
	
	public Collection<Category> getAll();
	
}