package se.sds.floggit.repository;

import java.util.Collection;

import se.sds.floggit.model.User;

public interface UserRepository {
	
	public void add(User user);
	
	public void delete(User user);

	public void update(User user);
	
	public boolean userValidation(User user);
		
	public User get(User user);
	
	public Collection<User> getAll();

}
