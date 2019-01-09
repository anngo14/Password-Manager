package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	List<User> getAllUsers();
	void registerUser(User userToSave);
	void resetUser(User userToReset);
}
