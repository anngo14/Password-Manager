package dao;

import java.util.ArrayList;
import java.util.List;

import model.User;

public interface UserDAO {

	ArrayList<User> getAllUsers();
	boolean checkUser(User userToCheck);
	void registerUser(User userToSave);
	void resetUser(User userToReset);
	
}
