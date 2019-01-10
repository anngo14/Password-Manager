package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	List<User> getAllUsers();
	boolean checkUser(User userToCheck);
	void registerUser(User userToSave);
	void resetUser(User userToReset);
	
}
