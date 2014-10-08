package se.sds.floggit.api;

import java.util.Collection;

import se.sds.floggit.model.User;
import se.sds.floggit.repository.MySQLUserRepo;

public final class ECommerceUser {

	private MySQLUserRepo userRepository;
	
	public ECommerceUser() {
		userRepository = new MySQLUserRepo();
	}

	public void addUser(User user) {
		userRepository.add(user);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public void updateUser(User user) {
		userRepository.update(user);
	}

	public boolean userValidation(User user) {
		return userRepository.userValidation(user);		
	}
	
	public User getUser(User user) {
		return userRepository.get(user);
	}
	
	public Collection<User> getAllUsers() {
		return userRepository.getAll();
	}
}
